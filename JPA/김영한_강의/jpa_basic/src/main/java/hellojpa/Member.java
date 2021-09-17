//package hellojpa;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
////enum, final, inner, intereface 사용하면 안됨
//@Entity() //(name = "다른 엔티티 이름 사용 가능")
////@Table(name = "다른 테이블 이름 사용가능")
//public class Member {
//
//    @Id
//    private Long id;
//
//    @Column(unique = true, length = 10)
//    private String name;
//
//    //ENTITY는 무조건 기본생성자가 존재하여야 한다.
//    public Member(){}
//
//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}

package hellojpa;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
// @Table => 여기에서 unique 제약조건을 주는 것이 바람직하다
public class Member {
    @Id
    private Long id;

    @Column(name = "name", nullable = true)// columnDefinition = "varchar(100) default 'EMPTY'"
    private String username;

    private int age; // Integer로 해도 됨

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL은 가급적 사용하지 않는다. 해결할 수 없는 버그가 생길지도...
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    @Transient
    private int temp;

    public Member(){}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getTemp() {
        return temp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
//Getter, Setter…
}
