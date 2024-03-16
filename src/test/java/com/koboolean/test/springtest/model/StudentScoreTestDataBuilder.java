package com.koboolean.test.springtest.model;


/** 테스트 데이터 빌더 패턴 */
public class StudentScoreTestDataBuilder {

    public static StudentScore.StudentScoreBuilder passed(){
        return StudentScore.builder()
                .studentName("koboolean")
                .exam("examTest")
                .korScore(80)
                .englishScore(100)
                .mathScroe(70);
    }

    public static StudentScore.StudentScoreBuilder faild(){
        return StudentScore.builder()
                .studentName("koboolean")
                .exam("examTest")
                .korScore(50)
                .englishScore(60)
                .mathScroe(30);
    }
}
