package com.example.todo_api.follow;


import com.example.todo_api.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Follow follow) {
        em.persist(follow);
    }

    public Follow findById(Long id) {
        return em.find(Follow.class, id);
    }

    public List<Follow> findByFollower(User follower) {
        return em.createQuery(
                "select f from Follow f where f.follower = :follower", Follow.class)
                .setParameter("follower", follower)
                .getResultList();
    }

    public List<Follow> findByFollowee(User followee) {
        return em.createQuery(
                "select f from Follow f where f.followee = :followee", Follow.class)
                .setParameter("followee", followee)
                .getResultList();
    }

    public void delete(Follow follow) {
        em.remove(follow);
    }
}
