package com.projectcourse.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectcourse.model.Course;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseSaveResponse {

    private Integer id;

    private String name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

    private LocalDate endDate;

    public CourseSaveResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
    }
}
