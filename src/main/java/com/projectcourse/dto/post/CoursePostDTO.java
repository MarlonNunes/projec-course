package com.projectcourse.dto.post;


import com.projectcourse.model.Student;
import com.projectcourse.model.Teacher;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CoursePostDTO {

    private String name;

    private Teacher teacher;

    private List<Student> students = new ArrayList<>();


}
