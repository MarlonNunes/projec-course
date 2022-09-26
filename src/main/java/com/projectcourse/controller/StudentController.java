package com.projectcourse.controller;

import com.projectcourse.dto.put.StudentPutDTO;
import com.projectcourse.model.Course;
import com.projectcourse.model.Student;
import com.projectcourse.service.CourseService;
import com.projectcourse.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private final CourseService courseService;

    @GetMapping(value = "/student")
    public ResponseEntity<List<Student>> findAll(){
        return ResponseEntity.ok(studentService.findAll());
    }
}
