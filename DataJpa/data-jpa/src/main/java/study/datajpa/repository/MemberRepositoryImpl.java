package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
//@RequiredArgsConstructor -> 이거 사용해도 됨
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    @Autowired
    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
