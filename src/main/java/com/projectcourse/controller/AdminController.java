package com.projectcourse.controller;

import com.projectcourse.dto.post.*;
import com.projectcourse.dto.response.CourseSaveResponse;
import com.projectcourse.dto.response.ModuleSaveResponse;
import com.projectcourse.dto.response.TeacherSaveResponse;
import com.projectcourse.dto.response.UserResponse;
import com.projectcourse.model.Course;
import com.projectcourse.model.Student;
import com.projectcourse.model.Teacher;
import com.projectcourse.model.User;
import com.projectcourse.service.UserService;
import com.projectcourse.service.CourseService;
import com.projectcourse.service.StudentService;
import com.projectcourse.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @PostMapping(value = "/createCourse")
    public ResponseEntity<CourseSaveResponse> createCourse(@RequestBody CourseSaveDTO courseSaveDTO){
       return ResponseEntity.ok(courseService.save(courseSaveDTO));
    }

    @PostMapping(value = "/createModule")
    public ResponseEntity<ModuleSaveResponse> createModule(@RequestBody ModuleSaveDTO moduleSaveDTO){
        return ResponseEntity.ok(this.courseService.saveModule(moduleSaveDTO));
    }

    @PostMapping(value = "/createTeacher")
    public ResponseEntity<TeacherSaveResponse> createTeacher(@RequestBody TeacherSaveDTO teacherDTO, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(teacherService.save(teacherDTO, user));
    }

    @PostMapping (value = "/createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody StudentSaveDTO studentDTO, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(studentService.save(studentDTO));
    }

    @PostMapping (value = "/createAdmin")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody UserSaveDTO user, @AuthenticationPrincipal User userAuth){
        return ResponseEntity.ok(userService.save(user, userAuth));
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

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer idAdmin){
        if(!userService.verifyExists(idAdmin)){
            return ResponseEntity.notFound().build();
        }

        userService.delete(idAdmin);

        return ResponseEntity.accepted().build();
    }
}