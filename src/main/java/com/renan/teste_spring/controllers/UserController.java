package com.renan.teste_spring.controllers;
import com.renan.teste_spring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController{
    private List<User> listUser = new ArrayList<User>();
    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("users", listUser);
        model.addAttribute("size", listUser.size()); //no template tem que estar igual ao nome que está entre aspas no comando
        return "users/index";
    }

    @GetMapping("/novo")
    public String userNovo(ModelMap model){
        model.addAttribute("user", new User());
        return "users/novo";

    }

    @PostMapping("/criar")
    public String userCriar(@ModelAttribute User user, ModelMap model){
        System.out.println("#############################################");
        System.out.println("Nome recebido: " + user.getName());
        System.out.println("E-mail recebido: " + user.getArroba());
        System.out.println("CPF recebido: " + user.getCpf());
        System.out.println("#############################################");

        listUser.add(user);
        return "redirect:/users/index";
    }
}