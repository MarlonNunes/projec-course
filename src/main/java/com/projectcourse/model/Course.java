package com.projectcourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCourse;

    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
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
