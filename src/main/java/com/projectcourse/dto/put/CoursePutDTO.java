package com.projectcourse.dto.put;

import com.projectcourse.model.Student;
import com.projectcourse.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class CoursePutDTO {

    private Integer idCourse;

    private String name;

    private Teacher teacher;

    private List<Student> students = new ArrayList<>();

}
