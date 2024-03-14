package com.koboolean.test.springtest.web;

import com.koboolean.test.springtest.service.StudentScoreService;
import com.koboolean.test.springtest.web.request.SaveExamScoreRequest;
import com.koboolean.test.springtest.web.response.ExamFailStudentResponse;
import com.koboolean.test.springtest.web.response.ExamPassStudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScoreApiController {

    private final StudentScoreService service;

    @PutMapping("/exam/{exam}/score")
    public void save(@PathVariable("exam") String exam, @RequestBody SaveExamScoreRequest request){

        String studentName = request.getStudentName();
        Integer korScore = request.getKorScore();
        Integer englishScore = request.getEnglishScore();
        Integer mathScore = request.getMathScore();

        service.saveScore(studentName, exam, korScore, englishScore, mathScore);
    }

    @GetMapping("exam/{exam}/pass")
    public List<ExamPassStudentResponse> pass(@PathVariable("exam") String exam){
        return service.getPassStudentList(exam);
    }

    @GetMapping("exam/{exam}/fail")
    public List<ExamFailStudentResponse> fail(@PathVariable("exam") String exam){
        return service.getFailStudentList(exam);
    }

}
