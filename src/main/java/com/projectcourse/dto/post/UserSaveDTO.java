package com.projectcourse.dto.post;

import lombok.Data;

@Data
public class UserSaveDTO {
    protected String name;
    protected String username;
    protected String email;
    protected String password;
    protected String authorities;
}
