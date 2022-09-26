package com.projectcourse.dto.post;

import com.projectcourse.model.Course;
import com.projectcourse.model.Teacher;
import lombok.Data;

@Data
public class ModuleSaveDTO {

    private Integer id;

    private String name;

    private Integer teacherId;

    private Integer courseId;
}
