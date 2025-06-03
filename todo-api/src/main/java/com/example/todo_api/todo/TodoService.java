package com.example.todo_api.todo;

import com.example.todo_api.common.BadRequestException;
import com.example.todo_api.common.ErrorMessage;
import com.example.todo_api.todo.dto.TodoCreateRequest;
import com.example.todo_api.todo.dto.TodoResponse;
import com.example.todo_api.todo.dto.TodoUpdateRequest;
import com.example.todo_api.user.User;
import com.example.todo_api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.todo_api.common.ErrorMessage.MEMBER_NOT_EXISTS;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public TodoResponse createTodo(TodoCreateRequest req) {
        User user = userRepository.findById(req.getUserId());
        if(user == null) throw new BadRequestException(MEMBER_NOT_EXISTS);
        Todo todo = new Todo(req.getContent(), user);
        todoRepository.save(todo);
        return new TodoResponse(todo);
    }

    public List<TodoResponse> getMyTodos(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) throw new IllegalArgumentException("User not found");
        return todoRepository.findAllByUser(user)
                .stream().map(TodoResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public TodoResponse updateTodo(Long id, TodoUpdateRequest req) {
        Todo todo = todoRepository.findById(id);
        if (todo == null) throw new IllegalArgumentException("Todo not found");
        todo.updateContent(req.getContent());
        return new TodoResponse(todo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional
    public TodoResponse toggleCheck(Long id) {
        Todo todo = todoRepository.findById(id);
        if (todo == null) throw new IllegalArgumentException("Todo not found");
        todo.setChecked(!todo.isChecked());
        return new TodoResponse(todo);
    }

    public List<TodoResponse> getFriendTodos(Long friendId) {
        User friend = userRepository.findById(friendId);
        if (friend == null) throw new IllegalArgumentException("User not found");
        return todoRepository.findAllByUser(friend)
                .stream().map(TodoResponse::new).collect(Collectors.toList());
    }
}

