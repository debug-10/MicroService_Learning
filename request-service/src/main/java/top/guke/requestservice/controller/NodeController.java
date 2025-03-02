package top.guke.requestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.guke.requestservice.entity.User;
import top.guke.requestservice.openfeign.NodeServiceClient;

import java.util.List;

@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeServiceClient nodeServiceClient;

    @GetMapping("/users")
    public List<User> getUsers() {
        return nodeServiceClient.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return nodeServiceClient.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return nodeServiceClient.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return nodeServiceClient.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        nodeServiceClient.deleteUser(id);
    }
}