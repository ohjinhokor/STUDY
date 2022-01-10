package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {


    //DATAJPA가 자동으로 함수를 만들어줌.. 이거는 진짜 혁명적인데....?
    // 엔티티의 필드 값과 함수의 이름이 같아야 함
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //Top3 예시, 여기서 Hello는 크게 의미가 없는 단어임
    List<Member> findTop3HelloBy();

    @Query(name="Member.findByUsername") // @Query가 없어도 인지해서 Member.findByUsername을 찾아보게됨
    //Named쿼리의 가장 큰 장점은 컴파일 시점에 오류를 잡아낸다는 점이다. 굉장히 중요함
    List<Member> findByUsername(@Param("username") String username);

}
