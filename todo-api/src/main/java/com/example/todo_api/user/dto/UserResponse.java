package com.example.todo_api.user.dto;

import com.example.todo_api.user.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String email;
    private String name;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
