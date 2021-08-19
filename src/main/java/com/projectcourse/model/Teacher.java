package com.projectcourse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Teacher extends User{

    @Id
    private Integer idTeacher;

    @OneToMany()
    private List<Course> course;

}
