package study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.*;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    @BeforeEach
    public void before(){
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);

        System.out.println("\n\n\n "+member1.getId());
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // 초기화
        em.flush();
        em.clear();
    }

    @Test
    public void startJPQL(){
        //member1을 찾아라
        Member findMember = em.createQuery("select m from Member m where m.username =:username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl(){

//        QMember m = new QMember("m"); // 이 방법도 맞는 방법임

//        QMember m = QMember.member;

//        Member findMember = queryFactory // queryFactory는 전역변수로 선언되어있음음
//                .select(m)
//                .from(m)
//                .where(m.username.eq("member1")) //파라미터 바인딩 처리
//                .fetchOne();

//                기본 문법 - 기본 Q Type 활용
        Member findMember = queryFactory // queryFactory는 전역변수로 선언되어있음음
                .select(member) // 여기서 member는 QMember.member임, QMember는 전역변수로 선언되어 있음.
                .from(member)
                .where(member.username.eq("member1")) //파라미터 바인딩 처리
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    //기본 문법 - 검색 조건 쿼리
    @Test
    public void search(){
        Member findMember = queryFactory
                .selectFrom(member) //여기서 member는 QMember.member임(전역변수로 QMember가 있음)
                .where(member.username.eq("member1")
                        .and(member.age.between(10,30)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");

        // 조건 검색 예시
//        member.username.eq("member1") // username = 'member1'
//        member.username.ne("member1") //username != 'member1'
//        member.username.eq("member1").not() // username != 'member1'
//        member.username.isNotNull() //이름이 is not null
//        member.age.in(10, 20) // age in (10,20)
//        member.age.notIn(10, 20) // age not in (10, 20)
//        member.age.between(10,30) //between 10, 30
//        member.age.goe(30) // age >= 30
//        member.age.gt(30) // age > 30
//        member.age.loe(30) // age <= 30
//        member.age.lt(30) // age < 30
//        member.username.like("member%") //like 검색
//        member.username.contains("member") // like ‘%member%’ 검색
//        member.username.startsWith("member") //like ‘member%’ 검색
    }

    @Test
    public void searchAndParam(){
        Member findMember = queryFactory
                .selectFrom(member) //여기서 member는 QMember.member임(전역변수로 QMember가 있음)
                .where(
                    member.username.eq("member1"),
                    member.age.between(10, 30)
                )
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // 기본 문법 - 결과 조회
    @Test
    public void resultFetchTest(){
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        // 여기서 예외발생됨. 결과가 1개가 아니기 때문
        Member fetchOne = queryFactory
                .selectFrom(QMember.member)
                .fetchOne();

        System.out.println(fetchOne);

        Member fetchFirst = queryFactory
                .selectFrom(QMember.member)
                .fetchFirst();

        //공식문서에서는 fetch()를 권장함
        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        long total = results.getTotal();
        System.out.println("total =" + total);
        List<Member> content = results.getResults();

        //여기서는 쿼리가 count 쿼리가 나감
        long countTotal = queryFactory
                .selectFrom(member)
                .fetchCount();
    }

    //기본 문법 - 정렬
    /**
    * 회원 정렬 순서
     * 1. 회원 나이 내림차순(desc)
     * 2. 회원 이름 올림차순(asc)
     * 단 2에서 회원 이름이 없으면 마지막에 출력(null last)
     */
    @Test
    public void sort(){
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }

    //기본 문법 - 페이징
    @Test
    public void paging(){
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) // 0부터 시작임
                .limit(2)
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void paging2(){
        QueryResults<Member> queryResults = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) // 0부터 시작임
                .limit(2)
                .fetchResults();

        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults()).isEqualTo(2);
    }

    //기본 문법 - 집합
    @Test
    public void aggregation(){
       List<Tuple> result = queryFactory
               .select(
                       member.count(),
                       member.age.sum(),
                       member.age.avg(),
                       member.age.max(),
                       member.age.min()
               )
               .from(member)
               .fetch();

       Tuple tuple = result.get(0);

       assertThat(tuple.get(member.count())).isEqualTo(4);
       assertThat(tuple.get(member.age.sum())).isEqualTo(100);
       assertThat(tuple.get(member.age.avg())).isEqualTo(25);
       assertThat(tuple.get(member.age.max())).isEqualTo(40);
       assertThat(tuple.get(member.age.min())).isEqualTo(10);

    }

    /**
     * 팀의 이름과 각 팀의 평균 연령을 구해라
     *
     */


    @Test
    public void group() throws Exception{
        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }
}
