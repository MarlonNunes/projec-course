package com.projectcourse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Teacher extends User{

    @Id
    private Integer idTeacher;

    private List<Course> course;
}
