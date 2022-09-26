package com.projectcourse.dto.post;


import com.projectcourse.model.Student;
import com.projectcourse.model.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CourseSaveDTO {

    private Integer id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

}
