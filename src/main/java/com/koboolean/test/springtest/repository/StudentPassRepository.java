package com.koboolean.test.springtest.repository;

import com.koboolean.test.springtest.model.StudentPass;
import com.koboolean.test.springtest.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudentPassRepository extends JpaRepository<StudentPass, Long> {
}
