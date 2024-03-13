package com.koboolean.test.springtest.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestVOTest {

    @Test
    public void testDatTest(){
        TestVO testData = new TestVO();

        testData.setName("koboolean");

        Assertions.assertEquals("koboolean", testData.getName());
    }

}