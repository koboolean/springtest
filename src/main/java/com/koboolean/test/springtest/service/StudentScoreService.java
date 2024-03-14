package com.koboolean.test.springtest.service;

import com.koboolean.test.springtest.MyCalculator;
import com.koboolean.test.springtest.model.StudentFail;
import com.koboolean.test.springtest.model.StudentPass;
import com.koboolean.test.springtest.model.StudentScore;
import com.koboolean.test.springtest.repository.StudentFailRepository;
import com.koboolean.test.springtest.repository.StudentPassRepository;
import com.koboolean.test.springtest.repository.StudentScoreRepository;
import com.koboolean.test.springtest.web.response.ExamFailStudentResponse;
import com.koboolean.test.springtest.web.response.ExamPassStudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentScoreService {

    private final StudentScoreRepository studentScoreRepository;
    private final StudentPassRepository studentPassRepository;
    private final StudentFailRepository studentFailRepository;


    public void saveScore(String studentName, String exam, Integer korScore, Integer englishScore, Integer mathScore){
        StudentScore studntScore = StudentScore.builder()
                .exam(exam)
                .studentName(studentName)
                .korScore(korScore)
                .englishScore(englishScore)
                .mathScroe(mathScore)
                .build();

        studentScoreRepository.save(studntScore);

        MyCalculator myCalculator = new MyCalculator();
        Double avgScore = myCalculator.add(korScore.doubleValue())
                .add(englishScore.doubleValue())
                .add(mathScore.doubleValue())
                .divide(3.0)
                .getResult();

        if(avgScore >= 60){
            StudentPass studentPass = StudentPass.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build();

            studentPassRepository.save(studentPass);
        }else{
            StudentFail studentFail = StudentFail.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build();

            studentFailRepository.save(studentFail);
        }
    }

    public List<ExamPassStudentResponse> getPassStudentList(String exam){
        List<StudentPass> studentPasses = studentPassRepository.findAll();

        return studentPasses.stream().filter((pass) -> pass.getExam().equals(exam))
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
                .toList();
    }

    public List<ExamFailStudentResponse> getFailStudentList(String exam){
        List<StudentFail> studentFails = studentFailRepository.findAll();

        return studentFails.stream().filter((fail) -> fail.getExam().equals(exam))
                .map((fail) -> new ExamFailStudentResponse(fail.getStudentName(), fail.getAvgScore()))
                .toList();
    }
}
