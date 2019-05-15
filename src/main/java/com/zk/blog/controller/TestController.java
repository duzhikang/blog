package com.zk.blog.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return user;
    }

    @GetMapping("/get")
    public String get() {
        return "this is GetTestMethod";
    }

    static class User {
        private Integer id;

        private String name;

        private Integer gender;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }
    }
}
