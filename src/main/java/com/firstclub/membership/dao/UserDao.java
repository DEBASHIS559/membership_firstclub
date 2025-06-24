package com.firstclub.membership.dao;

import com.firstclub.membership.data.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao {
    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
