package hellojpa;

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

        //고급 매핑
//        Movie movie2 = new Movie();
//        movie2.setActor("영화배우2");
//        movie2.setDirector("감독2");
//        movie2.setPrice(100002);
//        movie2.setName("영화제목2");
//
//        em.persist(movie2);
//
//        Book book = new Book();
//        book.setName("책 제목");
//        book.setPrice(100000);
//        book.setIsbn("ISBN");
//        book.setAuthor("책 저자");
//
//        em.persist(book);
//
//        //MappedSuperClass의 함수와 변수 사용 예시
//        Member member = new Member();
//        member.setUsername("유저 이름");
//        member.setCreatedDate(LocalDateTime.now());
//
//        em.flush();
//        em.clear();


        //프록시와 연관관계 관리 - 프록시
//        Member member1 = new Member();
//        member1.setUsername("멤버 이름");
//
//        em.persist(member1);
//        em.flush();
//        em.clear();
//
//
////        Member member = em.find(Member.class, 1L);
//
//        // select 쿼리를 날리지 않고 member객체를 받아옴, 가짜 엔티티라고 생각하면 됨.(프록시)
//        Member member = em.getReference(Member.class, member1.getId());
//
//
//        //이 때 쿼리를 날림. 기존에 객체만 가져올 때는 쿼리를 날리지 않다가 username처럼 db를 이용해서 알 수 있는 데이터가 필요할 때 쿼리를 날림
//        System.out.println("select 쿼리 시작");
//        System.out.println("findmember = " + member.getUsername());
//        // 쿼리를 날린 이후 프록시는 엔티티를 targeting한다.
//
//
////        printMemberAndTeam(member);
//
//        printMember(member);

//         프록시와 연관관계 관리 - 즉시 로딩과 지연 로딩
//        (fetchType.Lazy)
        Team team = new Team();
        team.setName("teamA");
        em.persist(team);

        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setTeam(team);
        em.persist(member1);


        em.flush();
        em.clear();

        Member m = em.find(Member.class, member1.getId());
        System.out.println("여기까지는 member만 select함 team은 프록시임");

        // 여기서 team에 select 쿼리를 날림
        m.getTeam().getName();


        // 이런식으로 페치 조인을 사용하면 team까지 한 번에 select 할 수 있다
        List<Member> resultList = em.createQuery("select m from Member m join fetch m.team", Member.class)
                .getResultList();

        tx.commit();


//        //(fetchType.EAGER) 그러나 실무에서 즉시 로딩은 사용하지 않는다.
//        // 실무에서는 fetchType.LAZY 즉 지연 로딩만 사용한다.
//        // 즉시로딩 사용하지 않는 이유
//        // 1. 성능 저하
//        // 2. N+1 문제를 일으킴
//
//        Team team = new Team();
//        team.setName("teamA");
//        em.persist(team);
//
//        Member member1 = new Member();
//        member1.setUsername("member1");
//        member1.setTeam(team);
//        em.persist(member1);
//
//
//        em.flush();
//        em.clear();
//
//        // 한 번에 member와 team을 다 select 함
//        Member m = em.find(Member.class, member1.getId());
//        System.out.println("Team과 Member를 한 번에 select함");
//        m.getTeam().getName();
//
//        tx.commit();

    } catch(Exception e) {
        System.out.println("여기로");
        tx.rollback();
        }
    finally{
            em.close();
        }
        emf.close();
    }

//    private static void printMember(Member member) {
//        System.out.println("member = " + member.getUsername());
//    }
//
//    private static void printMemberAndTeam(Member member){
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
////        System.out.println("team = " + team.getName());
//
//
//    }
}
