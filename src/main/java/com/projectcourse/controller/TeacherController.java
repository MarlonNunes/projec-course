package com.projectcourse.controller;

import com.projectcourse.dto.put.TeacherPutDTO;
import com.projectcourse.model.Course;
import com.projectcourse.model.Teacher;
import com.projectcourse.service.CourseService;
import com.projectcourse.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    private final CourseService courseService;

    @GetMapping(value = "/teacher")
    public ResponseEntity<List<Teacher>> findAllTeachers(){
        return ResponseEntity.ok(teacherService.findAll());
    }


}
