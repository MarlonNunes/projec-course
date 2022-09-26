package com.projectcourse.dto.response;

import com.projectcourse.model.Module;
import com.projectcourse.model.Teacher;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ModuleSaveResponse {

    private Integer id;

    private String name;

    private TeacherModuleResponse teacher;

    public ModuleSaveResponse(Module module){
        this.id = module.getId();
        this.name = module.getName();
        this.teacher = new TeacherModuleResponse(module.getTeacher());
    }

    @Data
    public static class TeacherModuleResponse{
        private Integer id;
        private String name;
        private List<CourseSaveResponse> courses;

        public TeacherModuleResponse(Teacher teacher){
            this.id = teacher.getId();
            this.name = teacher.getUser().getName();
            this.courses = teacher.getCourses().stream().map((c) -> new CourseSaveResponse(c)).collect(Collectors.toList());
        }
    }
}
