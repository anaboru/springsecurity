package com.anaboru.springsecurity.services;

import com.anaboru.springsecurity.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User findById(Long id);

    void remove(Long id);

    void save(User user);

    void update(Long id, User user);

    User findByUsername(String username);


}
