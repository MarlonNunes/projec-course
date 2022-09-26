package com.projectcourse.service;

import com.projectcourse.dto.post.TeacherSaveDTO;
import com.projectcourse.dto.post.UserSaveDTO;
import com.projectcourse.dto.put.TeacherPutDTO;
import com.projectcourse.dto.response.TeacherSaveResponse;
import com.projectcourse.dto.response.UserResponse;
import com.projectcourse.model.Course;
import com.projectcourse.model.Teacher;
import com.projectcourse.model.User;
import com.projectcourse.repository.CourseRepository;
import com.projectcourse.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TeacherService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final UserService userService;

    public List<Teacher> listAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Integer id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not Found"));
    }

    public TeacherSaveResponse save(TeacherSaveDTO teacherDTO, User user) {

        Course course = this.courseRepository.findById(teacherDTO.getCourse().getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        Teacher teacher = null;

        if(teacherDTO.getId() != null){
            teacher = this.teacherRepository.findById(teacherDTO.getId()).orElse(new Teacher());
        }else{
            teacher = new Teacher();
        }

        teacherDTO.setAuthorities("ROLE_TEACHER");
        UserResponse userSaved = this.userService.save(teacherDTO, user);
        teacher.setUser(teacher.getUser() == null ? this.userService.findById(userSaved.getId()) : teacher.getUser());

        if(teacher.getCourses() == null) {
            teacher.setCourses(new ArrayList<>(Arrays.asList(course)));
        }else{
            teacher.getCourses().add(course);
        }

        teacher = teacherRepository.save(teacher);


        if(course.getTeachers() == null){
            course.setTeachers(new ArrayList<>(Arrays.asList(teacher)));
        }else{
            course.getTeachers().add(teacher);
        }
        this.courseRepository.save(course);

        return new TeacherSaveResponse(teacher);
    }

    public void delete(Integer id) {
        teacherRepository.deleteById(id);
    }

    public boolean verifyExists(Integer id) {
        return teacherRepository.existsById(id);
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
