package com.example.todo_api.todo;

import com.example.todo_api.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="todo_id")
    private Long id;

    @Column(name="todo_content", columnDefinition = "varchar(200)")
    private String content;

    @Column(name="todo_is_checked", columnDefinition="tinyint(1)")
    private boolean isChecked = false;

    @JoinColumn(name="member_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    public Todo(String content, User user){
        this.content=content;
        this.user=user;
    }

    public void updateContent(String content){
        this.content=content;
    }

    public void setChecked(boolean isChecked){
        this.isChecked=isChecked;
    }
}