package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String username;

    // 엔티티의 기본생성자는 private이 아니라 protected로 열어놔야 함
    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public void changeUsername(String username){
        this.username = username;
    }
}
