package com.example.todo_api.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public User findByEmail(String email) {
        return em.createQuery(
                        "select u from User u where u.email = :email",User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    //이름은 고유하지 않을 수 있으므로 리스트로 구현함
    public List<User> findByName(String name) {
        return em.createQuery(
                        "select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public void delete(User user) {
        em.remove(user);
    }
}