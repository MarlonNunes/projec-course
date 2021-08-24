package com.projectcourse.service;

import com.projectcourse.model.Course;
import com.projectcourse.model.Student;
import com.projectcourse.repository.StudentRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final InformationStudentService informationStudentService;


    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(studentRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("Student Not Found"));
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findById(Integer id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Student save(Student student){
        student.setAuthorities("ROLE_USER");
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        informationStudentService.save(student);
        return studentRepository.save(student);
    }

    public void delete(Integer id){

        studentRepository.deleteById(id);
    }

    public boolean verifyExists(Integer id){
        return studentRepository.existsById(id);
    }

    public Student Replace (Student student){

        return studentRepository.save(student);
    }
}
