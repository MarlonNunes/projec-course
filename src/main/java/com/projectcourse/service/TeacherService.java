package com.projectcourse.service;

import com.projectcourse.dto.post.TeacherPostDTO;
import com.projectcourse.dto.put.TeacherPutDTO;
import com.projectcourse.model.Teacher;
import com.projectcourse.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Log4j2
public class TeacherService implements UserDetailsService {

    private final TeacherRepository teacherRepository;


    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(teacherRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("Teacher Not Found"));
    }

    public List<Teacher> listAll(){
        return teacherRepository.findAll();
    }

    public Teacher findById(Integer id){
        return teacherRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not Found"));
    }

    public Teacher save(TeacherPostDTO teacherDTO){
        Teacher teacher = Teacher.builder().name(teacherDTO.getName()).username(teacherDTO.getUsername())
                        .password(passwordEncoder.encode(teacherDTO.getPassword())).authorities("ROLE_TEACHER")
                        .courses(teacherDTO.getCourse()).email(teacherDTO.getEmail()).build();

        return teacherRepository.save(teacher);
    }

    public void delete(Integer id){
        teacherRepository.deleteById(id);
    }

    public boolean verifyExists(Integer id){
        return teacherRepository.existsById(id);
    }

    public Teacher replace(TeacherPutDTO teacherPutDTO){
        Teacher teacher = Teacher.builder().idTeacher(teacherPutDTO.getIdTeacher()).name(teacherPutDTO.getName())
                .username(teacherPutDTO.getUsername()).password(passwordEncoder.encode(teacherPutDTO.getPassword()))
                .courses(teacherPutDTO.getCourse()).email(teacherPutDTO.getEmail()).authorities("ROLE_TEACHER").build();
        log.info(teacher);
        return teacherRepository.save(teacher);
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
