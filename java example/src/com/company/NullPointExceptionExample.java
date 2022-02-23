package com.company;

public class NullPointExceptionExample {

    //헷갈렸던 부분. 어디서 NPE가 나고 어디서 안나는지 확인하자.
    // nullpointexception은 null을 값으로 가지고 있는 변수를 print 하는 등 그 자체만 사용할 때 나지 않음.
    // 예를 들어 아래처럼 example자체를 sout하는 경우 NPE가 나지 않는다.
    // example이 가리키고 있는 값을 사용하려고 할 때 NPE가 발생한다. 왜냐하면 가리키고 있는 값이 없는데 그 값을 사용하려고 하기 때문.

    public static void main(String[] args) {

        //NPE 발생 x
        int example[] = null;
        System.out.println(example);

        //NPE 발생 o
        System.out.println(example.length);
    }
}
