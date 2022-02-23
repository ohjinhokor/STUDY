package com.company;

public class SingletonExample {

    public static void main(String[] args) {
        Singleton example1 = Singleton.getInstance();
        Singleton example2 = Singleton.getInstance();

        // 같은 객체를 가리키는 변수이므로 저장하고 있는 주소값이 같기 때문에 true가 나온다.
        System.out.println(example1 == example2);

        // 싱글톤 객체로 메서드 사용
        example1.print();
    }
}

// 싱글톤 클래스
class Singleton{

    // static으로 singleton객체를 하나 생성한다
    private static Singleton singleton = new Singleton();

    // 생성자를 private으로 하여 다른 곳에서 해당 클래스의 객체를 생성할 수 없게 한다.
    private Singleton(){}

    // 객체가 필요한 경우 static으로 선언되어있는 변수(여기서는 싱글톤 객체)를 반환해준다.
    public static Singleton getInstance(){
        return singleton;
    }

    // 싱글톤 객체가 사용할 수 있는 메서드
    public void print(){
        System.out.println("싱글톤 객체의 메서드");
    }
}
