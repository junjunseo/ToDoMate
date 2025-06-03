package com.example.todo_api.todo;

import com.example.todo_api.todo.dto.TodoCreateRequest;
import com.example.todo_api.todo.dto.TodoResponse;
import com.example.todo_api.todo.dto.TodoUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody @Valid TodoCreateRequest request) {
        return ResponseEntity.ok(todoService.createTodo(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TodoResponse>> getMyTodos(@PathVariable Long userId) {
        return ResponseEntity.ok(todoService.getMyTodos(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id,
                                               @RequestBody TodoUpdateRequest request) {
        return ResponseEntity.ok(todoService.updateTodo(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/toggle")
    public ResponseEntity<TodoResponse> toggle(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.toggleCheck(id));
    }

    @GetMapping("/friend/{friendId}")
    public ResponseEntity<List<TodoResponse>> getFriendTodos(@PathVariable Long friendId) {
        return ResponseEntity.ok(todoService.getFriendTodos(friendId));
    }
}
