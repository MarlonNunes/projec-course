package com.projectcourse.service;

import com.projectcourse.dto.post.UserSaveDTO;
import com.projectcourse.dto.response.UserResponse;
import com.projectcourse.model.User;
import com.projectcourse.repository.UserRepository;
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
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
    }

    public User findById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found"));
    }

    public UserResponse save(UserSaveDTO userSave, User userAuth){
        if(userAuth == null || !userAuth.hasRole("ROLE_ADMIN")){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Don't have permission");
        }
       User user = new User(userSave);
       user.setPassword(passwordEncoder.encode(userSave.getPassword()));
       return new UserResponse(userRepository.save(user));
    }

    public void delete (Integer id){
         userRepository.deleteById(id);
    }

    public boolean verifyExists(Integer id){
        return userRepository.existsById(id);
    }

    public User replace(User user){
        return userRepository.save(user);
    }

}
