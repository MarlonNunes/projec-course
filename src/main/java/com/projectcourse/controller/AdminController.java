package com.projectcourse.controller;

import com.projectcourse.dto.post.CoursePostDTO;
import com.projectcourse.dto.post.StudentPostDTO;
import com.projectcourse.dto.post.TeacherPostDTO;
import com.projectcourse.model.Administrator;
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


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdministratorService administratorService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final AdministratorService adminService;

    @PostMapping(value = "/createCourse")
    public ResponseEntity<Course> createCourse(@RequestBody CoursePostDTO coursePostDTO){
       if(! teacherService.verifyExists(coursePostDTO.getTeacher().getIdTeacher())){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(courseService.save(coursePostDTO));
    }

    @PostMapping(value = "/createTeacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherPostDTO teacherDTO){
        return ResponseEntity.ok(teacherService.save(teacherDTO));
    }

    @PostMapping (value = "/createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody StudentPostDTO studentDTO){
        return ResponseEntity.ok(studentService.save(studentDTO));
    }

    @PostMapping (value = "/createAdmin")
    public ResponseEntity<Administrator> createAdmin(@RequestBody Administrator administrator){
        return ResponseEntity.ok(adminService.save(administrator));
    }

    @DeleteMapping(value = "/course/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer idCourse){

        if(!courseService.verifyExists(idCourse)){
            return ResponseEntity.notFound().build();
        }

        courseService.delete(idCourse);

        return ResponseEntity.accepted().build();

    }


    @DeleteMapping(value = "/teacher/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer idTeacher){
        if(!teacherService.verifyExists(idTeacher)){
            return ResponseEntity.notFound().build();
        }

        teacherService.delete(idTeacher);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer idStudent){
        if(!studentService.verifyExists(idStudent)){
            return ResponseEntity.notFound().build();
        }

        studentService.delete(idStudent);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/administrator/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer idAdmin){
        if(!adminService.verifyExists(idAdmin)){
            return ResponseEntity.notFound().build();
        }

        adminService.delete(idAdmin);

        return ResponseEntity.accepted().build();
    }
}