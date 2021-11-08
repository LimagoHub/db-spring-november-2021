package de.db.webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
//    @Service
//    @Repository
//    @Controller
//        @RestController

@RestController
@RequestMapping("/v1/demo")
public class DemoController {

    @GetMapping("/gruss")
    public String gruss() {
        return "Hallo Spring";
    }
}
