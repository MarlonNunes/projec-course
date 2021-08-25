package com.projectcourse.repository;

import com.projectcourse.model.InformationStudentByCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationStudentRepository extends JpaRepository<InformationStudentByCourse, Integer> {
}
