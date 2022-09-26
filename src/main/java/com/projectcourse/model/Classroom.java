package com.projectcourse.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //private List<String> files;

    @ManyToMany(mappedBy = "classes")
    private List<Module> modules;

    private LocalDate date;

}
