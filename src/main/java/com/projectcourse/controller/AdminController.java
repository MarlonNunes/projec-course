package com.projectcourse.controller;

import com.projectcourse.model.Course;
import com.projectcourse.model.Student;
import com.projectcourse.model.Teacher;
import com.projectcourse.service.AdministratorService;
import com.projectcourse.service.CourseService;
import com.projectcourse.service.StudentService;
import com.projectcourse.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdministratorService administratorService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @PostMapping(value = "/createCourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
       return ResponseEntity.ok(courseService.save(course));
    }

    @PostMapping(value = "/createTeacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        return ResponseEntity.ok(teacherService.save(teacher));
    }

    @PostMapping (value = "/createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

        return ResponseEntity.ok(studentService.save(student));
    }
}
