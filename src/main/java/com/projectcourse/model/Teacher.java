package com.projectcourse.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@SuperBuilder
public class Teacher extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTeacher;

    @OneToMany(mappedBy = ("teacher"))
    private List<Course> courses;

}
