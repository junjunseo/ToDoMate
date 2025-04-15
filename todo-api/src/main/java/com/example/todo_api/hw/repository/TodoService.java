package com.example.demo.hw.service;

import com.example.demo.hw.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public String getTodo() {
        return todoRepository.getTodo();
    }
}
