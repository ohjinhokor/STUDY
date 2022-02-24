package com.company;

public class StaticExample {

    static String name;

    // 이런식으로 static에 final이 붙으면 모든 객체가 공유하는 값이자 불변의 값이기 때문에 상수화라고 볼 수 있다.
    static final String nation;

    static{
        name = "jinho";
    }

    //정적메서드 블록 : 클래스가 메모리에 로딩될 때 실행
    // 이런식으로 정적메서드 블록을 선언하면, static(final이 붙었어도)에 값을 넣어줄 수 있다.
    static{

        nation= "korea";
    }

    public static void main(String[] args) {

        //final로 선언되지 않은 변수는 값을 변경할 수 있지만, final로 선언된 변수는 값을 변경할 수 없다.
        name = "jinho";
//        nation = "korea"; -> 컴파일 에러
        System.out.println(name);
    }
}
