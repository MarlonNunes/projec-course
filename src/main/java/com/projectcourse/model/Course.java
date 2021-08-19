package com.projectcourse.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    private Integer idCourse;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL    )
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    private LocalDate startDate;

    private LocalDate endDate;

}
