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
//@Roleback(false)을 하면 트랜잭션 롤백이 안됨
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getMemberId()).get();

        assertThat(findMember.getMemberId()).isEqualTo(member.getMemberId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        assertThat(findMember).isEqualTo(member);
    }
}
