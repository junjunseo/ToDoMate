package com.example.todo_api.todo.dto;
import com.example.todo_api.todo.Todo;
import lombok.Getter;

@Getter
public class TodoResponse {
    private Long id;
    private String content;
    private boolean isChecked;
    private Long userId;

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.isChecked = todo.isChecked();
        this.userId = todo.getUser().getId();
    }
}
