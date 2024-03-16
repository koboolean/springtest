package com.koboolean.test.springtest.model;

/** textFixture 사용하기 */
public class StudentScoreFixture {
    public static StudentScore passed(){
        return StudentScore.builder()
                .studentName("koboolean")
                .exam("examTest")
                .korScore(80)
                .englishScore(100)
                .mathScroe(70)
                .build();
    }

    public static StudentScore faild(){
        return StudentScore.builder()
                .studentName("koboolean")
                .exam("examTest")
                .korScore(50)
                .englishScore(60)
                .mathScroe(30)
                .build();
    }
}
