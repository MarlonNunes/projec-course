package com.projectcourse.service;

import com.projectcourse.model.Teacher;
import com.projectcourse.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService implements UserDetailsService {

    private final TeacherRepository teacherRepository;

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

    public Teacher save(Teacher teacher){
        teacher.setAuthorities("ROLE_USER");
        return teacherRepository.save(teacher);
    }

    public void delete(Integer id){
        teacherRepository.deleteById(id);
    }

    public Teacher replace(Teacher teacher){
        return teacherRepository.save(teacher);
    }
}
