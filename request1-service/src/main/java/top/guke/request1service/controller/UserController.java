package top.guke.request1service.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.guke.request1service.client.UserClient;
import top.guke.request1service.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserClient userClient;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userClient.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userClient.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userClient.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userClient.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        return userClient.deleteUser(id);
    }
}