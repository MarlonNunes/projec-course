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

    @PutMapping(value = "/teacher/replaceTeacher")
    public ResponseEntity<Teacher> replaceTeacher(@RequestBody TeacherPutDTO teacherPutDTO){

        if(!teacherService.verifyExists(teacherPutDTO.getIdTeacher())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(teacherService.replace(teacherPutDTO));
    }

    @GetMapping(value = "/teacher")
    public ResponseEntity<List<Teacher>> findAllTeachers(){
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping(value = "/teacher/{idTeacher}/courses" )
    public ResponseEntity<List<Course>> findAllCoursesByTeacher(@PathVariable Integer idTeacher){

        if(!teacherService.verifyExists(idTeacher)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(courseService.findAllCoursesByTeacher(idTeacher));
    }


}
