package com.projectcourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCourse;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTeacher")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "InformationStudent",
            joinColumns = @JoinColumn(name = "idCourse"),
            inverseJoinColumns = @JoinColumn( name = "idStudent"))
    private List<Student> students = new ArrayList<>();

    private LocalDate startDate;

    @JsonIgnore
    private LocalDate endDate;

}
