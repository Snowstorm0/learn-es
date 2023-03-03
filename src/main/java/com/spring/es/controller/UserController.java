package com.spring.es.controller;

import com.spring.es.model.UserEntity;
import com.spring.es.service.UserService;
import com.spring.es.modeles.ESUser;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *
 * @author 代码的路
 * @date 2023/2/23
 */
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 写入user
    @ResponseBody
    @PostMapping("/add")
    public UserEntity addUser(@RequestBody UserEntity ESUser) {
        userService.addUser(ESUser);
        return ESUser;
    }

    // 从es返回user信息
    @GetMapping("/search/es")
    public List<ESUser> searchES(String key) {
        return userService.searchES(key);
    }

    // 从es返回完整数据
    @GetMapping("/search/whole")
    public SearchHits<ESUser> searchWhole(String key) {
        return userService.searchWhole(key);
    }

    // 从数据库返回user信息
    @GetMapping("/search/db")
    public List<UserEntity> searchDb(String key) {
        return userService.searchDb(key);
    }
}