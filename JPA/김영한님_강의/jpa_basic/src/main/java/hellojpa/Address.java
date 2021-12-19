package hellojpa;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //@Embeddable이 붙어있는 클래스는 반드시 기본 생성자가 존재해야함
    public Address(){}
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


    // 공유참조를 피하기 위해서 불변 객체로 만들었음. 모든 set함수를 private으로 만들어서 외부에서 변경할 수 없게 함
    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
