package com.stack.stackoverflow.user;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/test")
public class UserController {

    @GetMapping
    public String hello() {
        return "hello pre-project!!!!!!";
    }
}
