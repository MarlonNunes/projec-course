package com.projectcourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@SuperBuilder
@Data
public class Teacher extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTeacher;

    @JsonIgnore()
    @OneToMany(mappedBy = ("teacher"))
    private List<Course> courses;

    public Teacher (){
        super();

    }



}
