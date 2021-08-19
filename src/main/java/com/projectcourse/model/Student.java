package com.projectcourse.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Student extends User{

    @Id
    private Integer idStudent;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();


}
