package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;

    @Test
    public void testMember(){
        System.out.println("memberRepository.getClass() = " + memberRepository.getClass()); // 이를 통해 스프링 datajpa가 구현체를 만들어서 주입했음을 알 수 있음.
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD(){

            // 정석적인 테스트코드 스타일은 아니라고 함. 공부를 위한 간단한 테스트 코드임

            Member member1 = new Member("member1");
            Member member2 = new Member("member2");

            memberRepository.save(member1);
            memberRepository.save(member2);

            // 단건 조회
            // 실제로 테스트코드를 작성할 때는 get을 쓰는게 썩 좋은 방법이 아닌듯?
            Member findMember1 = memberRepository.findById(member1.getId()).get();
            Member findMember2 = memberRepository.findById(member2.getId()).get();

            assertThat(findMember1).isEqualTo(member1);
            assertThat(findMember2).isEqualTo(member2);

//        findMember1.setUsername("Hi Hi new Member");

            //리스트 조회
            List<Member> all = memberRepository.findAll();
            assertThat(all.size()).isEqualTo(2);

            //카운트 검증
            long count = memberRepository.count();
            assertThat(count).isEqualTo(2);

            //삭제 검증
            memberRepository.delete(member1);
            memberRepository.delete(member2);

            List<Member> deltedCount = memberRepository.findAll();
            assertThat(deltedCount.size()).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThan(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);
        m1.setUsername("hihihi");

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);

    }

    // 쿼리 메소드 기능 - JPA NamedQuery
    @Test
    public void findByUserName(){

        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);


        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    // 쿼리 메소드 기능 - 레포지토리 메소드에 직접 쿼리 정의하기
    @Test
    public void findUser(){

        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);


        List<Member> result = memberRepository.findUser("AAA",10);
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    // 쿼리 메소드 기능 - 값, DTO 조회하기
    @Test
    public void findUsernameList(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("\n\n s = " + s + "\n\n");
        }
    }

    @Test
    public void findMemberDto(){
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(teamA);
        memberRepository.save(m1);


        List<MemberDto> memberList = memberRepository.findMemberDto();
        for (MemberDto memberDto : memberList) {
            System.out.println("\n\n memberDto = " + memberDto);
        }
    }

    // 쿼리 메소드 기능 - 파라미터 바인딩(Collection을 파라미터로 받는 예시)
    @Test
    public void findByNames(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {

            System.out.println("\n\nmember = " + member + "\n\n");
        }
    }

    // 쿼리 메소드 기능 - 반환 타입
    @Test
    public void returnType(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> findMemberList = memberRepository.findMemberListByUsername("AAA");
        Member findMember = memberRepository.findMemberByUsername("AAA");
        Optional<Member> findOptionalMember = memberRepository.findOptionalMemberByUsername("AAA");

        // 반환타입에 대한 설명
        // list일 떄는 절대로 null이 아니다. 따라서 if(result==null)->이런거 할 필요 없음. 좋은 코드도 아님
        // optional일 때랑, optional이 아닐 때 null을 처리하는 방식이 다르다.

        //  <<<<Optional을 쓰는 것이 국룰!!!!>>>>

    }
}
