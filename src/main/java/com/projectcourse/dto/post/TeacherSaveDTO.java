package com.projectcourse.dto.post;

import com.projectcourse.model.Course;
import lombok.Data;

import java.util.List;

@Data
public class TeacherSaveDTO extends UserSaveDTO {

    private Integer id;
    private Course course;

}
