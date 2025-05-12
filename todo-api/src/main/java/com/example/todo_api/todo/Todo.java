package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
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

    @Column(name="todo_content")
    private String content;

    @Column(name="todo_is_checked", columnDefinition="tinyint(1)")
    private boolean ischecked;

    @JoinColumn(name="member_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private Member member;

    public Todo(String content, boolean ischecked, Member member){
        this.content=content;
        this.ischecked=ischecked;
        this.member=member;
    }
}
