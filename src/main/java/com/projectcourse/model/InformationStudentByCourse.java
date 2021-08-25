package com.projectcourse.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
public class InformationStudentByCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idInfoStudent;

    private double grade;

    private Integer missedClass;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idStudent")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idCourse")
    private Course course;

}
