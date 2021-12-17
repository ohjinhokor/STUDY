package hellojpa;

import jdk.vm.ci.meta.Local;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

    try{
//        Member member = new Member();
//        member.setId(2L);
//        member.setName("HelloB");
//        em.persist(member);
//        Member findMember = em.find(Member.class, 1L);
//        System.out.println("findMember.getId() = " + findMember.getId());
//        System.out.println("findMember.getName() = " + findMember.getName());
//
////        em.remove(findMember);
//        findMember.setName("helloJPA");
//
//        tx.commit();
//
//        List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                .setFirstResult(5)
//                .setMaxResults(8)
//                .getResultList();
//
//        for (Member member : result) {
//            System.out.println("member.getName() = " + member.getName());
//        }

        //비영속
//        Member member = new Member();
//        member.setId(101L);
//        member.setName("HelloJPAAA");
//
//        //영속
//        System.out.println("=== BEFORE ===");
//        em.persist(member);
//        System.out.println("=== AFTER ===");

//        Member findMember1 = em.find(Member.class, 101L);
//        Member findMember2 = em.find(Member.class, 101L);
//        // 이처럼 조회를 2번할 시에는, 1번은 1차캐시에서 데이터를 가지고 온다.
//
//        System.out.println(findMember1 == findMember2);
//        //영속성 콘텍스트
//
//        Member member1 = new Member(150L, "A");
//        Member member2 = new Member(151L, "B");
//
//        em.persist(member1);
//        em.persist(member2);
////        System.out.println("findMember.id = " + findMember.getId());
////        System.out.println("findMember.getName() = " + findMember.getName());

//        Member member = em.find(Member.class, 150L);
//        member.setName("ABCD");
//
//        //  em.persist(member) 할 필요 없음
//
//        System.out.println("====================");
//        Member member = new Member(200L, "member200");
//        em.persist(member);
//
//        em.flush();
//
//        System.out.println("=============아래 확인========================");
//        Member member = em.find(Member.class, 150L);
//        member.setName("ABCD");
//        System.out.println("=========================위에 확인======================");
//        //em.detach(member);
//        //em.clear();
//        //em.close();
//
//        System.out.println("====================");
//
//        Member member = new Member();
//        //member.setId("ID_A");
//        member.setUsername("B");
//        //member.setRoleType(RoleType.USER);
//
//        em.persist(member);
//
//        System.out.println("===============================");

        // 저장


//        Team team = new Team();
//        team.setName("TeamA");
//        //team.getMembers().add(member);
//        em.persist(team);
//
//        Member member = new Member();
//        member.setUsername("member1");
////        member.changeTeam(team);
//        em.persist(member);
//
////        team.getMembers().add(member);  -->> 이를 연관관계 편의 메서드로 바꿔주자
//
//        em.flush();
//        em.clear();
//

//        // 찾기
//        Member findmember = em.find(Member.class, member.getId());
//        Team findTeam = findmember.getTeam();
//        List<Member> members = findTeam.getMembers();
//
//        System.out.println("===============================================");
//        for (Member m : members) {
//            System.out.println("m.getUsername() = " + m.getUsername());
//        }
//
//        Movie movie = new Movie();
//        movie.setActor("영화배우");
//        movie.setDirector("감독");
//        movie.setPrice(10000);
//        movie.setName("영화제목");
//        em.persist(movie);

//        Movie findMovie = em.find(Movie.class, movie.getId());


//        Member member = new Member();
//        member.setUsername("user");
//        member.setCreatedBy("kim");
//        member.setCreatedDate(LocalDateTime.now());
//
//        em.persist(member);
//
//        em.flush();
//        em.clear();

//        System.out.println("---------------------------------------------");
//        System.out.println("findMovie = " + findMovie);;
//        Book book = new Book();
//        em.persist(book);
//
        Movie movie2 = new Movie();
        movie2.setActor("영화배우2");
        movie2.setDirector("감독2");
        movie2.setPrice(100002);
        movie2.setName("영화제목2");

        em.persist(movie2);

        Book book = new Book();
        book.setName("책 제목");
        book.setPrice(100000);
        book.setIsbn("ISBN");
        book.setAuthor("책 저자");

        em.persist(book);

        //MappedSuperClass의 함수와 변수 사용 예시
        Member member = new Member();
        member.setUsername("유저 이름");
        member.setCreatedDate(LocalDateTime.now());

        em.flush();
        em.clear();


        tx.commit();

    } catch(Exception e) {
        System.out.println("여기로");
        tx.rollback();
        }
    finally{
            em.close();
        }
        emf.close();
    }
}
