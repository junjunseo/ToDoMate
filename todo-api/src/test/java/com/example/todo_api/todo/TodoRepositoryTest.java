package com.example.todo_api.todo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.todo_api.user.User;
import com.example.todo_api.user.UserRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    void TodoSaveTest(){
        //given
        Todo todo = new Todo("todo content", false, null);

        //when
        todoRepository.save(todo);

        //then
        assertThat(todo.getId()).isNotNull();
        //transaction 종료 => 커밋
        //에러 발생시 자동으로 롤백

    }

    @Test
    @Transactional
    void todoFindOneTest() {
        //given
        Todo todo = new Todo("content", false, null);
        todoRepository.save(todo);

        //when
        Todo findTodo = todoRepository.findById(todo.getId());

        assertThat(todo).isNotNull();
        System.out.println(todo.getId());
        System.out.println(todo.getContent());
    }

    @Test
    @Transactional
    void todoFindAllTest() {
        //given
        todoRepository.save(new Todo("content1", false, null));
        todoRepository.save(new Todo("content2", false, null));
        todoRepository.save(new Todo("content3", false, null));

        //when
        List<Todo> todos = todoRepository.findAll();

        //then
        assertThat(todos).hasSize(3);
    }

    @Test
    @Transactional
    void todoFindAllByUserTest() {
        //given
        User user1 = new User();
        User user2 = new User();
        userRepository.save(user1);
        userRepository.save(user2);

        Todo todo1 = new Todo("todo content1", false, user1);
        Todo todo2 = new Todo("todo content2", false, user1);
        Todo todo3 = new Todo("todo content3", false, user2);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> user1TodoList = todoRepository.findAllByUser(user1);
        List<Todo> user2TodoList = todoRepository.findAllByUser(user2);

        Assertions.assertThat(user1TodoList).hasSize(2);
        Assertions.assertThat(user2TodoList).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateTodoTest() {
        //give
        Todo todo = new Todo("content",false, null);
        todoRepository.save(todo);
        Todo findTodo = todoRepository.findById(todo.getId());

        //when
        findTodo.updateContent("newContent");
    }

    @Test
    @Transactional
    @Rollback(false)
    void todoRemoveTest(){
        //given
        Todo todo = new Todo("to remove todo", false, null);
        todoRepository.save(new Todo("content1", false, null));
        todoRepository.save(new Todo("content2", false, null));

        //when
        todoRepository.deleteById(todo.getId());
    }
    //in memory database

    @AfterAll
    public static void doNotFinish(){
        System.out.println("test finished");
        while(true){

        }
    }
}
