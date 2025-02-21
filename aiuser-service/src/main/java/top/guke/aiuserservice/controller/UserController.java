package top.guke.aiuserservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.guke.aiuserservice.service.AIClient;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AIClient aiServiceClient;

    @GetMapping("/ask")
    public String askQuestion(@RequestParam String question) {
        String answer = aiServiceClient.askQuestion(question);
        return answer;
    }
}