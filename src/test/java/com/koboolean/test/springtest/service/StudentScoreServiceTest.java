package com.koboolean.test.springtest.service;

import com.koboolean.test.springtest.model.StudentPass;
import com.koboolean.test.springtest.repository.StudentFailRepository;
import com.koboolean.test.springtest.repository.StudentPassRepository;
import com.koboolean.test.springtest.repository.StudentScoreRepository;
import com.koboolean.test.springtest.web.response.ExamPassStudentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

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

    @Test
    @DisplayName("합격자 명단 가져오기 검증")
    public void getPassListTest(){
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        String testExam = "testExam";

        StudentPass pass1 = StudentPass.builder().studentPassId(1L).studentName("koboolean").exam(testExam).avgScore(70.0).build();
        StudentPass pass2 = StudentPass.builder().studentPassId(2L).studentName("test").exam(testExam).avgScore(80.0).build();
        StudentPass pass3 = StudentPass.builder().studentPassId(3L).studentName("koboolean").exam("secondTestExam").avgScore(90.0).build();

        // findAll 값을 보정할 수 있다. service.getPassStudentList의 findAll을 보정하여 하단의 세개의 값이 결과로 나오도록 하는것이다.
        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
                pass1, pass2, pass3
        ));

        StudentScoreService service = new StudentScoreService(
                studentScoreRepository, studentPassRepository, studentFailRepository
        );

        // when
        var expectResponse = List.of(pass1, pass2)
                .stream()
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
                .toList();

        List<ExamPassStudentResponse> responses = service.getPassStudentList("testExam");

        // 같은 값이 나오는지 검증
        Assertions.assertEquals(expectResponse, responses);

        responses.forEach((r) -> System.out.println(r.getStudentName()+ " : " + r.getAvgScore()));
    }
}
