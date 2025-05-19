package com.example.todo_api.user;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser_assignId() {
        //given
        User user = new User("alice@example.com", "Alice", "password1");

        //when
        userRepository.save(user);

        //then
        assertThat(user.getId()).isNotNull();
    }

    @Test
    void findById_returnUser() {
        //given
        User user = new User("bob@example.com", "Bob", "password2");
        userRepository.save(user);

        //when
        User found = userRepository.findById(user.getId());

        //then
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("bob@example.com");
        assertThat(found.getName()).isEqualTo("Bob");
        assertThat(found.getPassword()).isEqualTo("password2");
    }

    @Test
    void findByEmail_returnCorrectUser() {
        //given
        userRepository.save(new User("carol@example.com", "Carol", "password3"));
        userRepository.save(new User("daniel@example.com", "Daniel", "password4"));

        //when
        User carol = userRepository.findByEmail("carol@example.com");

        //then
        assertThat(carol).isNotNull();
        assertThat(carol.getName()).isEqualTo("Carol");
    }

    @Test
    void findByName_returnAllMatchingUsers() {
        userRepository.save(new User("eve@example.com", "Eve", "password5"));
        userRepository.save(new User("fred@example.com", "Fred", "password6"));
        userRepository.save(new User("gina@example.com", "Gina", "password7"));

        //when
        List<User> eves = userRepository.findByName("Eve");

        //then
        assertThat(eves).hasSize(2)
                .extracting(User::getEmail)
                .containsExactly("eve@example.com", "Fred@example.com");
    }

    @Test
    void findAll_returnsAllUsers() {
        //given
        userRepository.save(new User("harry@example.com", "Harry", "password8"));
        userRepository.save(new User("irene@example.com", "Irene", "password9"));
        userRepository.save(new User("john@example.com", "John", "password10"));

        //when
        List<User> all = userRepository.findAll();

        //then
        assertThat(all).hasSize(3)
                .extracting(User::getName)
                .containsExactlyInAnyOrder("Harry", "Irene", "John");
    }

    @Test
    void delete_user() {
        //given
        User user = new User("kim", "kem@example.com", "password11");
        userRepository.save(user);
        Long id = user.getId();
        User toDelete = userRepository.findById(id);

        //when
        userRepository.delete(toDelete);

        //then
        assertNull(userRepository.findById(id));

    }
}
