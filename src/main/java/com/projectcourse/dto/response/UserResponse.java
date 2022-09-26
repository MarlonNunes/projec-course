package com.projectcourse.dto.response;

import com.projectcourse.model.User;
import lombok.Data;

@Data
public class UserResponse {

    private Integer id;
    private String username;

    private String email;

    private String name;

    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
