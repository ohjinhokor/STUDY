package jpql;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

////            // 객체지향 쿼리 언어1 - 기본 문법 - 기본 문법과 쿼리 API
//            Member member = new Member();
//            member.setUsername("memberName");
//            member.setAge(10);
//            em.persist(member);
//
//            // typedquery와 query의 사용 예시(엄청 중요한 내용은 아님)
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//            Query query3 = em.createQuery("select m.username from Member m");
//
//            // 결과 조회 API
//            TypedQuery<Member> queryExample = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = queryExample.getResultList();// 결과가 없으면 빈 값 반환
//            String singleResult = query2.getSingleResult(); // 결과가 없거나 2개 이상이면 에러가 나옴
//
//            // 파라미터 바인딩
//            TypedQuery<Member> queryExample2 = em.createQuery(
//                    "select m from Member m where m.username = :username", Member.class);
//            queryExample2.setParameter("username", member.getUsername());
//            Member findMember = queryExample2.getSingleResult();
//            System.out.println("singResult = "+ findMember.getUsername());
//
//            // 실제로 사용하는 예시
//            Member findMember2 = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", member.getUsername())
//                    .getSingleResult();
//            System.out.println("findMember2 = "+ findMember2.getUsername());



//            // 객체지향 쿼리 언어1 - 기본 문법 - 프로젝션
//            // 엔티티 프로젝션
//            // createQuery를 통해 꺼내온 엔티티는 영속성 컨텍스트에 의해서 관리됨
//            List<Team> result = em.createQuery("select m.team from Member m join m.team t", Team.class)
//                    .getResultList();
//
//            // 임베디드 타입 프로젝션
//            List<Address> addressResult = em.createQuery("select o.address from Order o", Address.class)
//                    .getResultList();
//
//            // 스칼라 타입 프로젝션
//            // 값을 빼올 때 DTO를 사용하여 값을 가져오는 것이 가장 현명한 방법이다.
//
//            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) FROM Member m", MemberDTO.class)
//                    .getResultList();
//
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("username = " + memberDTO.getUsername());
//            System.out.println("age ="+ memberDTO.getAge());


//            // 객체지향 쿼리 언어1 - 기본 문법 - 페이징
//            for(int i=0; i<100; i++){
//                Member member = new Member();
//                member.setUsername("memberName"+i);
//                member.setAge(i);
//                em.persist(member);
//            }
//            em.flush();
//            em.clear();
//
//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("result.size = "+ result.size());
//            for (Member member : result) {
//                System.out.println("member.age = "+member.getAge());
//            }


            // 객체지향 쿼리 언어1 - 기본 문법 - 조인

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("memberName");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);


            Team team2 = new Team();
            team.setName("example");
            em.persist(team);

            Member member2 = new Member();
            member.setUsername("example");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            // inner join
            String query = "select m from Member m inner join m.team t";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            // outer join
            String query2 = "select m from Member m left outer join m.team t";
            List<Member> result2 = em.createQuery(query, Member.class)
                    .getResultList();


            // 세타 join
            String query3 = "select m from Member m, Team t where m.username =t.name";
            List<Member> result3 = em.createQuery(query, Member.class)
                    .getResultList();
            System.out.println("result3 = "+result3.size());

            // 조인 대상 필터링
            String query4 = "select m from Member m left join m.team t on t.name = 'teamA'";
            List<Member> result4 = em.createQuery(query4, Member.class)
                    .getResultList();

            System.out.println("result4 = "+ result.size());


            // 연관관계 없는 엔티티 외부 조인
            String query5 = "select m from Member m left join Team t on m.username = t.name";
            List<Member> result5 = em.createQuery(query4, Member.class)
                    .getResultList();

            tx.commit();
        } catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
