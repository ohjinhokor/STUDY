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

    //NamedQuery는 실무에서 잘 안씀. 이 다음 배우는 레포지토리에 직접 @Query를 하는 기능을 많이 사용
    @Query(name="Member.findByUsername") // @Query가 없어도 인지해서 Member.findByUsername을 찾아보게됨
    //Named쿼리의 가장 큰 장점은 컴파일 시점에 오류를 잡아낸다는 점이다. 굉장히 중요함
    List<Member> findByUsername(@Param("username") String username);

    // 쿼리 메소드 기능 - 리포지토리 메소드에 쿼리 정의하기
    // 이름이 없는 namedQuery와 같아서 실행될 때 문법오류가 바로 잡힘.
    @Query("select m from Member m where m.username = :username and m.age =:age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

}
