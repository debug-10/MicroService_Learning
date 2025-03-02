package top.guke.request1service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.guke.request1service.entity.User;

import java.util.List;

@FeignClient(name = "node-service", url = "http://127.0.0.1:3000/api")
public interface UserClient {

    @GetMapping("/users")
    List<User> getAllUsers();

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") int id);

    @PostMapping("/users")
    User createUser(@RequestBody User user);

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable("id") int id, @RequestBody User user);

    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable("id") int id);
}