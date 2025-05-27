package com.example.todo_api.todo.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoCreateRequest {
    private String content;
    private Long userId;
}
