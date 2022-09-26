package com.projectcourse.service;

import com.projectcourse.dto.post.StudentSaveDTO;
import com.projectcourse.dto.put.StudentPutDTO;
import com.projectcourse.model.Student;
import com.projectcourse.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findById(Integer id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Student save(StudentSaveDTO studentDTO){

        Student student = new Student();

        if(student.getCourses() == null){
            student.setCourses(new ArrayList<>(Arrays.asList(studentDTO.getCourse())));
        }else{
            student.getCourses().add(studentDTO.getCourse());
        }

        return studentRepository.save(student);
    }

    public void delete(Integer id){

        studentRepository.deleteById(id);
    }

    public boolean verifyExists(Integer id){
        return studentRepository.existsById(id);
    }
}
