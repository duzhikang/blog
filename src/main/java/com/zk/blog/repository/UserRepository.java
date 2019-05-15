package com.zk.blog.repository;

import com.zk.blog.entity.UserDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDo, Long> {

    UserDo findByUsername(String username);


}
