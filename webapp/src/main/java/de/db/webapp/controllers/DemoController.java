package de.db.webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

//@Component
//    @Service
//    @Repository
//    @Controller
//        @RestController

@RestController // @Component
@RequestMapping("/v1/demo")
// @SessionScope (Bitte nicht verwenden)
public class DemoController {

    private String warenkorb="";

    @GetMapping("/gruss")
    public String gruss() {
        return "Hallo Spring";
    }
    @GetMapping("/uuid")
    public String createUUID() {
        return UUID.randomUUID().toString();
    }




}
