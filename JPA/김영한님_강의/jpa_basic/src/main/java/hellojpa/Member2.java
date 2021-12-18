package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member2 extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //기간
    @Embedded // @Embeddable이 적혀있는 클래스를 필드로 쓸 수 있게함
    private Period workPeriod;

    //주소
    @Embedded // @Embeddable이 적혀있는 클래스를 필드로 쓸 수 있게함
    private Address homeAddress;

    //주소(Address가 중복됨) -> 이 때 AttributeOverrides를 사용하여 해결한다.
    @Embedded // @Embeddable이 적혀있는 클래스를 필드로 쓸 수 있게함
    @AttributeOverrides({
            @AttributeOverride(name="city",
            column = @Column(name="work_city")),
            @AttributeOverride(name="street",
            column = @Column(name="city_street")),
            @AttributeOverride(name="zipcode",
            column=@Column(name="work_zipcode"))
    })
    private Address workAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
