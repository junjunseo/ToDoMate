package com.example.demo.hw.controller;

import com.example.demo.hw.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/todo")
    public String getTodo() {
        return todoService.getTodo();
    }
}
