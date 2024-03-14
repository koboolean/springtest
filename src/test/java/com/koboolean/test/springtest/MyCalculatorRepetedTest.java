package com.koboolean.test.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MyCalculatorRepetedTest {

    @DisplayName("5번 반복하여 테스트")
    @RepeatedTest(5)
    public void repetedAddTest(){
        MyCalculator myCalculator = new MyCalculator();
        myCalculator.add(10.0);

        Assertions.assertEquals(10.0, myCalculator.getResult());
    }

    @DisplayName("5번 인자를 대입하여 테스트")
    @ParameterizedTest
    @MethodSource
    public void parameterizedAddTest(Double addValue, Double expectValue){
        MyCalculator myCalculator = new MyCalculator();
        myCalculator.add(addValue);

        Assertions.assertEquals(expectValue, myCalculator.getResult());
    }

    public static Stream<Arguments> parameterizedAddTest() {
        return Stream.of(
                Arguments.of(10.0, 10.0),
                Arguments.of(8.8, 8.8)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void parameterizedComplicatedCalculateTest(Double addValue, Double minusValue, Double multiplyValue, Double divideValue, Double resultValue){
        MyCalculator myCalculator = new MyCalculator();

        Double result = myCalculator.add(addValue)
                .minus(minusValue)
                .multiply(multiplyValue)
                .divide(divideValue)
                .getResult();

        Assertions.assertEquals(resultValue, result);
    }

    public Stream<Arguments> parameterizedComplicatedCalculateTest() {
        return Stream.of(
                Arguments.of(10.0, 4.0, 2.0, 3.0, 4.0)
        );
    }
}
