package com.example.todo_api.follow;


import com.example.todo_api.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follow_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="follower_id")
    private Member follower;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="followee_id")
    private Member followee;

    public Follow(Member follower, Member followee){
        this.follower=follower;
        this.followee=followee;
    }
}
