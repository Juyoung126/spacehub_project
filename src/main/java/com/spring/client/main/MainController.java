package com.spring.client.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
    public String MainPage() {
        return "client/main"; 
    }
}
