package com.projectcourse.repository;

import com.projectcourse.model.Course;
import com.projectcourse.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
