package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {


    //DATAJPA가 자동으로 함수를 만들어줌.. 이거는 진짜 혁명적인데....?
    // 엔티티의 필드 값과 함수의 이름이 같아야 함
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

}
