package com.example.todo_api.user;

import com.example.todo_api.user.dto.UserCreateRequest;
import com.example.todo_api.user.dto.UserLoginRequest;
import com.example.todo_api.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        List<User> users;
        if (email != null) {
            User u = userService.findByEmail(email);
            users = u != null ? List.of(u) : List.of();
        } else if (name != null) {
            users = userService.findByName(name);
        } else {
            users = userService.findAll();
        }
        List<UserResponse> result = users.stream()
                .map(UserResponse::new)
                .toList();
        return ResponseEntity.ok(result);
    }
}
