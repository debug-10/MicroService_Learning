package top.guke.userservice.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.guke.userservice.config.GukeProperties;
import top.guke.userservice.entity.User;
import top.guke.userservice.mapper.UserMapper;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private GukeProperties gukeProperties;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        if(gukeProperties.isServiceFlag()){
            return userMapper.selectById(id);
        }else {
            throw new RuntimeException("Service flag is under maintenance");
        }
    }
}