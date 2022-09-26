package com.projectcourse.dto.response;

import com.projectcourse.dto.post.TeacherSaveDTO;
import com.projectcourse.model.Course;
import com.projectcourse.model.Module;
import com.projectcourse.model.Teacher;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeacherSaveResponse {

    private Integer id;

    private String name;

    private List<CourseSaveResponse> courses;

    private List<Module> modules;


    public TeacherSaveResponse(Teacher teacher){
        this.id = teacher.getId();
        this.name = teacher.getUser().getName();
        this.courses = teacher.getCourses().stream().map((r) -> new CourseSaveResponse(r)).collect(Collectors.toList());
        this.modules = teacher.getModules();
    }
}
