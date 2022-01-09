package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(false)
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

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
}
