package com.koboolean.test.springtest;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {

    /** Main을 이용한 테스트 예제 */
    public static void main(String[] args) {
        MyCalculator myCalculator = new MyCalculator();

        myCalculator.add(10.0);
        myCalculator.minus(2.0);
        myCalculator.multiply(2.0);

        myCalculator.divide(2.0);

        System.out.println(myCalculator.getResult());
    }

}