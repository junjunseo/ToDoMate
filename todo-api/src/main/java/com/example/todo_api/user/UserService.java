package com.example.todo_api.user;

import com.example.todo_api.user.dto.UserCreateRequest;
import com.example.todo_api.user.dto.UserLoginRequest;
import com.example.todo_api.user.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest req) {
        User user = new User(req.getEmail(), req.getName(), req.getPassword());
        userRepository.save(user);
        return new UserResponse(user);
    }

    public UserResponse login(UserLoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail());
        if (user == null || !user.getPassword().equals(req.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return new UserResponse(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}