package com.example.todo_api.follow.dto;

import com.example.todo_api.follow.Follow;
import lombok.Getter;

@Getter
public class FollowResponse {
    private Long id;
    private Long followerId;
    private Long followeeId;

    public FollowResponse(Follow follow) {
        this.id = follow.getId();
        this.followerId = follow.getFollower().getId();
        this.followeeId = follow.getFollowee().getId();
    }
}
