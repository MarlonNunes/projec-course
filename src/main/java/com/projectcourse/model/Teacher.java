package com.projectcourse.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Teacher extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTeacher;

    @OneToMany(mappedBy = ("teacher"))
    private List<Course> course;

}
