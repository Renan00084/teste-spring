package com.renan.teste_spring.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController{
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/new")
    public String novo(){
        return "new";

    }

}