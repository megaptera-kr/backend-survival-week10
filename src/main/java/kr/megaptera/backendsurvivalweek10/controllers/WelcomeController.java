package kr.megaptera.backendsurvivalweek10.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public String home() {
        return "Hello, World!";
    }

    @GetMapping("/user")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String user() {
        return "Hello, World!";
    }

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String admin() {
        return "Hello, World!";
    }
}
