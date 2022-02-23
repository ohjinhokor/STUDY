package com.company;

public class ArrayExample {

    public static void main(String[] args) {

        //배열 기본 생성
        int [] intArray;
        int intArray2[];


        //배열 생성하면서 값 넣기
        int [] intArray3 = {1,2,3,4,5};
//        intArray3 = {4,5,6,7,8};  -> 컴파일 에러
        intArray3 = new int[]{1,2,3,4,5,6,7,8}; // -> 가능


        //배열을 나중에 넣는 법
        int[] arrayExample;
        arrayExample = new int[] {1,2,3,4,5};
    }
}
