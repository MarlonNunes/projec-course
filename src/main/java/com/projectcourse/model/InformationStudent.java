package com.projectcourse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class InformationStudent {

    @Id
    private Integer idInfoStudent;

    private double grade;

    private Integer missedClass;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

}
