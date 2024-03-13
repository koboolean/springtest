package com.koboolean.test.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorJunitTest {

    @Test
    void addTest() {
        // 다음 형식의 패턴을 AAA패턴 혹은 GWT 패턴이라고 한다.

        // Arrange(AAA) || Given(GWT) 준비 단계
        MyCalculator myCalculator = new MyCalculator();
        
        // Act(AAA) || When(GWT) 행동/연산 단계
        myCalculator.add(10.0);

        // Assert(AAA) || Then(GWT) 검증/비교/단언 단계
        Assertions.assertEquals(10.0, myCalculator.getResult());
    }

    @Test
    void minusTest() {

        // given
        MyCalculator myCalculator = new MyCalculator(10.0);

        // when
        myCalculator.minus(5.0);

        // then
        Assertions.assertEquals(5.0, myCalculator.getResult());
    }

    @Test
    void multiplyTest() {
        MyCalculator myCalculator = new MyCalculator(10.0);

        myCalculator.multiply(2.0);

        Assertions.assertEquals(20.0, myCalculator.getResult());
    }

    @Test
    void divideTest() {
        MyCalculator myCalculator = new MyCalculator(10.0);

        myCalculator.divide(5.0);

        Assertions.assertEquals(2.0, myCalculator.getResult());
    }


    @Test
    void complicateCalculateTest(){
        MyCalculator myCalculator = new MyCalculator();

        Double result = myCalculator.add(10.0)
                .minus(4.0)
                .multiply(2.0)
                .divide(3.0)
                .getResult();

        Assertions.assertEquals(4.0, result);
    }

    @Test
    void divideZeroTest(){
        MyCalculator myCalculator = new MyCalculator(10.0);

        // WHEN & THEN
        // Exception이 발생하는지 확인한다.
        Assertions.assertThrows(MyCalculator.ZeroDivisionException.class, () ->{
            myCalculator.divide(0.0);
        });


    }

}