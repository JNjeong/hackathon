package com.hackaton.hackaton.repository;


import com.hackaton.hackaton.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User findById(long id);

    int save(User user);

    int delete(long id);
}