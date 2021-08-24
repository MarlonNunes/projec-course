package com.projectcourse.service;

import com.projectcourse.model.Administrator;
import com.projectcourse.model.Course;
import com.projectcourse.model.Student;
import com.projectcourse.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorService implements UserDetailsService {

    private final AdministratorRepository administratorRepository;

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(administratorRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("Admin Not Found"));
    }

    public Administrator findById(Integer id){
        return administratorRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found"));
    }

    public Administrator save(Administrator administrator){
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        administrator.setAuthorities("ROLE_ADMIN");
        return administratorRepository.save(administrator);
    }

    public void delete (Integer id){
         administratorRepository.deleteById(id);
    }

    public boolean verifyExists(Integer id){
        return administratorRepository.existsById(id);
    }

    public Administrator replace(Administrator administrator){
        return administratorRepository.save(administrator);
    }

}
