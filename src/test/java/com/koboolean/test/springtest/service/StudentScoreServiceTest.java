package com.koboolean.test.springtest.service;

import com.koboolean.test.springtest.repository.StudentFailRepository;
import com.koboolean.test.springtest.repository.StudentPassRepository;
import com.koboolean.test.springtest.repository.StudentScoreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class StudentScoreServiceTest {

    @Test
    public void firstSaveScoreMockTest(){
        StudentScoreService studentScoreService = new StudentScoreService(
                Mockito.mock(StudentScoreRepository.class),
                Mockito.mock(StudentPassRepository.class),
                Mockito.mock(StudentFailRepository.class)
        );

        String givenStudentName = "koboolean";

        String givenExam = "testExam";

        Integer korScr = 80;
        Integer engScr = 70;
        Integer mathScr = 60;

        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                korScr,
                engScr,
                mathScr
        );
    }
}
