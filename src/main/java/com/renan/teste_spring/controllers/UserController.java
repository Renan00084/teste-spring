package com.renan.teste_spring.controllers;
import com.renan.teste_spring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController{
    private List<User> listUser = new ArrayList<User>();
    @GetMapping("/index")
    public String index(ModelMap model){
        listUser.add(new User(1L, "Renan", "@renan00084", 119));
        model.addAttribute("size", listUser.size()); //no template tem que estar igual ao nome que está entre aspas no comando
        return "users/index";
    }

    @GetMapping("/new")
    public String novo(){
        return "new";

    }

}