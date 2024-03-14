package com.koboolean.test.springtest.repository;

import com.koboolean.test.springtest.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {
}
