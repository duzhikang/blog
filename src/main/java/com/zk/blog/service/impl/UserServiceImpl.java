package com.zk.blog.service.impl;

import com.zk.blog.entity.UserDo;
import com.zk.blog.repository.UserRepository;
import com.zk.blog.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void insert(UserDo userDo) {
        String username = userDo.getUsername();
        UserDo user = userRepository.findByUsername(username);
        if (user != null) {
            throw new RuntimeException("用户名已经存在");
        }
        userRepository.save(userDo);
    }

    @Override
    public UserDo getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
