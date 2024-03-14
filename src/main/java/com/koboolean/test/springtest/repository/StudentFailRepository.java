package com.koboolean.test.springtest.repository;

import com.koboolean.test.springtest.model.StudentFail;
import com.koboolean.test.springtest.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFailRepository extends JpaRepository<StudentFail, Long> {
}
