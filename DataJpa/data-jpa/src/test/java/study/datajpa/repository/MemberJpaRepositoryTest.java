package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
// @Rollback(false)로 하면 트랜잭션 롤백이 안됨
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(savedMember.getMemberId());

        assertThat(findMember.getMemberId()).isEqualTo(member.getMemberId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);

    }
}