package com.projectcourse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    private Integer idCourse;

    private String name;

    private Teacher teacher;

    private List<Student> students;

    private LocalDate startDate;

    private LocalDate endDate;

}
