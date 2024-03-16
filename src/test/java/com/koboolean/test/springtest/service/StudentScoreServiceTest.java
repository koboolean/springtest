package com.koboolean.test.springtest.service;

import com.koboolean.test.springtest.model.StudentPass;
import com.koboolean.test.springtest.repository.StudentFailRepository;
import com.koboolean.test.springtest.repository.StudentPassRepository;
import com.koboolean.test.springtest.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("성적 저장 로직 검증")
    public void saveScoreMockTest(){
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService service = new StudentScoreService(
                studentScoreRepository, studentPassRepository, studentFailRepository
        );

        String givenStudentName = "koboolean";
        String givenExam = "testExam";

        Integer korScr = 80;
        Integer engScr = 70;
        Integer mathScr = 60;

        service.saveScore(
                givenStudentName,
                givenExam,
                korScr,
                engScr,
                mathScr
        );

        // 60점 이상일 경우 Fail은 일어나지 않고, Score 및 Pass는 실행되어야 한다.
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
    }
}
