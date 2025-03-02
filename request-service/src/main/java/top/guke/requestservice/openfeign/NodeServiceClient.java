package top.guke.requestservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "node-service", url = "http://localhost:3000")
public interface NodeServiceClient {

    @GetMapping("/users")
    List<User> getUsers();

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") int id);

    @PostMapping("/users")
    User createUser(@RequestBody User user);

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable("id") int id, @RequestBody User user);

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable("id") int id);
}