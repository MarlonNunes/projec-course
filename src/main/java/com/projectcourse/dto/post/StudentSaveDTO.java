package com.projectcourse.dto.post;

import com.projectcourse.model.Course;
import lombok.Data;

import java.util.List;

@Data
public class StudentSaveDTO extends UserSaveDTO{

    private Course course;
}
