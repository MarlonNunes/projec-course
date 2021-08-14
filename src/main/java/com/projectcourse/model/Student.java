package com.projectcourse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Student extends User{

    @Id
    private Integer idStudent;

    private List<Course> courses;



}
