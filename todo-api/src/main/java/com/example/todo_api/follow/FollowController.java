package com.example.todo_api.follow;

import com.example.todo_api.follow.dto.FollowResponse;
import com.example.todo_api.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping
    public ResponseEntity<FollowResponse> follow(@RequestParam Long followerId,
                                                 @RequestParam Long followeeId) {
        return ResponseEntity.ok(followService.follow(followerId, followeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> unfollow(@PathVariable Long id) {
        followService.unfollow(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<UserResponse>> getFriends(@PathVariable Long userId) {
        List<UserResponse> friends = followService.getMyFriends(userId)
                .stream().map(UserResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(friends);
    }
}
