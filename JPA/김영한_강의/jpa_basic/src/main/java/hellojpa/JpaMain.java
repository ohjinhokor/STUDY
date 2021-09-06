package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
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

        List<Member> result = em.createQuery("select m from Member as m", Member.class)
                .setFirstResult(5)
                .setMaxResults(8)
                .getResultList();

        for (Member member : result) {
            System.out.println("member.getName() = " + member.getName());
        }

    } catch(Exception e) {
        tx.rollback();
        }
    finally{
            em.close();
        }
        emf.close();
    }
}
