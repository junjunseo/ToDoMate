package com.example.todo_api.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_email",columnDefinition = "varchar(30)")
    private String email;

    @Column(name="user_name", columnDefinition = "varchar(30)")
    private String name;

    @Column(name="user_password", columnDefinition = "varchar(30)")
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}