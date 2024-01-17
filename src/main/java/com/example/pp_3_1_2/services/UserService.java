package com.example.pp_3_1_2.services;

import com.example.pp_3_1_2.models.User;
import com.example.pp_3_1_2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findOne(int id);

    @Transactional
    public void save(User user);

    @Transactional
    public void update(int id, User updatedUser);

    @Transactional
    public void delete(int id);

}
