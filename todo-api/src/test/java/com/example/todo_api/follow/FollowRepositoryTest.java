package com.example.todo_api.follow;

import com.example.todo_api.user.User;
import com.example.todo_api.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FollowRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Test
    void save_and_findById(){
        //given
        User user1 = new User("eve@example.com", "Eve", "password5");
        User user2 = new User("fred@example.com", "fred", "password6");
        userRepository.save(user1);
        userRepository.save(user2);

        Follow follow1 = new Follow(user1, user2);

        //when
        followRepository.save(follow1);

        //then
        assertThat(follow1.getId()).isNotNull();
        Follow found = followRepository.findById(follow1.getId());
        assertThat(found.getFollower().getId()).isEqualTo(user1.getId());
        assertThat(found.getFollowee().getId()).isEqualTo(user2.getId());
    }

    @Test
    void findByFollower_and_findByFollowee() {
        //given
        User user1 = new User("gina@example.com", "Gina", "password7");
        User user2 = new User("harry@example.com", "Harry", "password8");
        User user3 = new User("irene@example.com", "Irene", "password9");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Follow follow1 = new Follow(user1, user2); //gina -> harry follow
        Follow follow2 = new Follow(user1, user3); //gina -> irene follow
        followRepository.save(follow1);
        followRepository.save(follow2);

        //when
        List<Follow> following = followRepository.findByFollower(user1);
        List<Follow> followersOfUser2 = followRepository.findByFollower(user2);

        //then
        assertThat(following).hasSize(2);
        assertThat(followersOfUser2).hasSize(1)
                .first().extracting("follower")
                .isEqualTo(user1);
    }

    @Test
    void delete_follow() {
        //given
        User user1 = new User("john@example.com", "John", "password10");
        User user2 = new User("kim@example.com", "Kim", "password11");
        userRepository.save(user1);
        userRepository.save(user2);

        Follow follow1 = new Follow(user1, user2);
        followRepository.save(follow1);
        Long fid = follow1.getId();

        //when
        followRepository.delete(follow1);

        //then
        assertThat(followRepository.findById(fid)).isNull();
    }
}
