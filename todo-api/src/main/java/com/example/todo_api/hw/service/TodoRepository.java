package com.example.demo.hw.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
    public String getTodo() {
        return "할 일 하나 가져왔어요!";
    }
}
