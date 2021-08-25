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

    @GetMapping(value = "/student/{idStudent}/courses")
    public ResponseEntity<List<Course>> findAllCoursesByStudent(@PathVariable Integer idStudent){
        if(!studentService.verifyExists(idStudent)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(courseService.findAllCoursesByStudent(idStudent));
    }

    @PutMapping(value = "student/replaceStudent")
    public ResponseEntity<Student> replaceStudent(StudentPutDTO studentPutDTO){
        if(!studentService.verifyExists(studentPutDTO.getIdStudent())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentService.Replace(studentPutDTO));
    }
}
