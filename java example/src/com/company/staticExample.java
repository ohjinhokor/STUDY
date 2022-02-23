package com.company;

public class staticExample {

    static final String name;

    //정적메서드 블록 : 클래스가 메모리에 로딩될 때 실행
    // 이런식으로 정적메서드 블록을 선언하면, static(final이 붙었어도)에 값을 넣어줄 수 있다.
    static{
        name = "name : "+"jinho";
    }

    public static void main(String[] args) {
//        name = "jinho"; -> 컴파일 에러, final로 선언된 변수에는 이후에 넣어줄 수 없음
        System.out.println(name);
    }
}
