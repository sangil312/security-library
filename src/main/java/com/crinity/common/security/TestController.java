package com.crinity.common.security;

import com.crinity.common.security.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public void test(User user) {
        System.out.println("id: " + user.id());
        System.out.println("tenant: " + user.tenant());
    }
}
