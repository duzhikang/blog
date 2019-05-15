package com.zk.blog.service;

import com.zk.blog.entity.UserDo;

public interface UserService {

    /**
     * 添加新用户
     * @param userDo
     */
    void insert(UserDo userDo);

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    UserDo getByUsername(String username);
}
