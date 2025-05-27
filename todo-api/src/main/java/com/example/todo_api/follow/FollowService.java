package com.example.todo_api.follow;

import com.example.todo_api.follow.dto.FollowResponse;
import com.example.todo_api.user.User;
import com.example.todo_api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public FollowResponse follow(Long followerId, Long followeeId) {
        User follower = userRepository.findById(followerId);
        User followee = userRepository.findById(followeeId);
        if(follower == null || followee == null) throw new IllegalArgumentException("User not found");
        Follow follow = new Follow(follower, followee);
        return new FollowResponse(follow);
    }

    @Transactional
    public void unfollow(Long id) {
        Follow follow = followRepository.findById(id);
        if(follow == null) throw new IllegalArgumentException("Follow not found");
        followRepository.delete(follow);
    }

    public List<User> getMyFriends(Long userId) {
        User user = userRepository.findById(userId);
        if(user == null) throw new IllegalArgumentException("User not found");
        return followRepository.findByFollower(user)
                .stream().map(Follow::getFollowee).collect(Collectors.toList());
    }
}
