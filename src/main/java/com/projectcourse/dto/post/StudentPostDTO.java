package com.projectcourse.dto.post;

import com.projectcourse.model.Course;
import lombok.Data;

import java.util.List;

@Data
public class StudentPostDTO {

    private String name;

    private String username;

    private String password;

    private String email;

    private List<Course> course;
}
