package com.projectcourse.service;

import com.projectcourse.dto.post.CourseSaveDTO;
import com.projectcourse.dto.post.ModuleSaveDTO;
import com.projectcourse.dto.response.CourseSaveResponse;
import com.projectcourse.dto.response.ModuleSaveResponse;
import com.projectcourse.model.Course;
import com.projectcourse.model.Module;
import com.projectcourse.model.Teacher;
import com.projectcourse.repository.CourseRepository;
import com.projectcourse.repository.ModuleRepository;
import com.projectcourse.repository.StudentRepository;
import com.projectcourse.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;

    public List<Course> findAll() {

        return courseRepository.findAll();
    }

    public Course findById(Integer id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public CourseSaveResponse save(CourseSaveDTO courseSaveDTO){
        Course course = courseSaveDTO.getId() != null ? this.courseRepository.findById(courseSaveDTO.getId()).orElse(new Course()) : new Course();

        course.setName(courseSaveDTO.getName());
        course.setStartDate(courseSaveDTO.getStartDate());
        course.setEndDate(courseSaveDTO.getEndDate());

        return new CourseSaveResponse(courseRepository.save(course));
    }

    public ModuleSaveResponse saveModule(ModuleSaveDTO moduleSaveDTO){
        Course course = this.courseRepository.findById(moduleSaveDTO.getCourseId())
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        Teacher teacher = this.teacherRepository.findById(moduleSaveDTO.getTeacherId())
                            .orElseThrow(() ->
                                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        if(!course.getTeachers().contains(teacher)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This teacher is not suitable for this course");
        }
        Module module = new Module();

        module.setName(moduleSaveDTO.getName());
        module.setCourse(course);
        module.setTeacher(teacher);
        module = moduleRepository.save(module);

        if(course.getModules() == null){
            course.setModules(new ArrayList<>());
        }

        course.getModules().add(module);

        courseRepository.save(course);
        return new ModuleSaveResponse(module);
    }

    public CourseSaveResponse addModule(ModuleSaveDTO moduleSaveDTO){
        Course course = this.courseRepository.findById(moduleSaveDTO.getCourseId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        Module module = this.moduleRepository.findById(moduleSaveDTO.getId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found"));


        if(!course.getTeachers().contains(module.getTeacher())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This teacher is not suitable for this course");
        }

        if(course.getModules() == null){
            course.setModules(new ArrayList<>());
        }

        course.getModules().add(module);

        return new CourseSaveResponse(this.courseRepository.save(course));

    }
}

