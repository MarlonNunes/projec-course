package com.projectcourse.service;

import com.projectcourse.dto.post.CoursePostDTO;
import com.projectcourse.model.Course;
import com.projectcourse.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAll(){

        return courseRepository.findAll();
    }

    public Course findById(Integer id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public Course save(CoursePostDTO coursePostDTO){
        Course course = Course.builder().name(coursePostDTO.getName()).teacher(coursePostDTO.getTeacher())
            .students(coursePostDTO.getStudents()).startDate(LocalDate.now()).build();

        return courseRepository.save(course);
    }

    public void delete(Integer id){
        courseRepository.deleteById(id);
    }

    public boolean verifyExists(Integer id){
        return courseRepository.existsById(id);
    }

    public Course Replace (Course course){

        return courseRepository.save(course);
    }
}

