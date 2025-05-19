package com.example.todo_api.follow;

import com.example.todo_api.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Table(name="follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follow_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="follower_id")
    private User follower;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="followee_id")
    private User followee;

    public Follow(User follower, User followee){
        this.follower=follower;
        this.followee=followee;
    }
}
