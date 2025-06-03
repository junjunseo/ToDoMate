package com.example.todo_api.todo.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class TodoCreateRequest {

    @Length(max = 200, message = "할 일 내용은 200자를 넘을 수 없습니다.")
    private String content;

    @NotNull(message = "유저 ID는 필수입니다.")
    private Long userId;
}
