package com.projectcourse.dto.put;

import com.projectcourse.model.Course;
import lombok.Data;

import java.util.List;

@Data
public class StudentPutDTO {

    private Integer idStudent;

    private String name;

    private String username;

    private String password;

    private String email;

    private List<Course> course;
}
