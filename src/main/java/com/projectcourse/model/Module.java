package com.projectcourse.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "module_students",
            joinColumns = @JoinColumn(name = "modules"),
            inverseJoinColumns = @JoinColumn( name = "students"))
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "module_classroom",
            joinColumns = @JoinColumn(name = "id_classes"),
            inverseJoinColumns = @JoinColumn( name = "id_modules"))
    private List<Classroom> classes;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Course course;
}
