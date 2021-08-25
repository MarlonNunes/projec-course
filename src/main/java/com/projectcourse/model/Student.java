package com.projectcourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Data
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idStudent;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    public Student(){
        super();
    }

}






