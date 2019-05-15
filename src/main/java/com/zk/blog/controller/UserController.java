package com.zk.blog.controller;

import com.zk.blog.base.ApiResponse;
import com.zk.blog.base.BaseMessageEnum;
import com.zk.blog.entity.UserDo;
import com.zk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public ApiResponse user(@AuthenticationPrincipal Principal principal){
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        List list = sessionRegistry.getAllPrincipals();
        System.out.println(list.size());
        System.out.println(principal.getName());
        return ApiResponse.success(principal.getName());
    }

    @GetMapping("/admin")
    public ApiResponse admin(@AuthenticationPrincipal Principal principal){
        return ApiResponse.success(principal.getName());
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody UserDo user) {
        //

        userService.insert(user);
        return ApiResponse.success(BaseMessageEnum.REGISTER_SUCCESS);
    }
}
