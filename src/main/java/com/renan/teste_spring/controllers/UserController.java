package com.renan.teste_spring.controllers;

import com.renan.teste_spring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private List<User> listUser = new ArrayList<User>();

    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("users", listUser);
        model.addAttribute("size", listUser.size());
        return "users/index";
    }

    @GetMapping("/new")
    public String userNew(ModelMap model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/create")
    public String userCreate(@ModelAttribute User user, ModelMap model){
        // Imprime os dados no console do servidor
        System.out.println("############ criando novo user ##########################");
        System.out.println("Nome: " + user.getName());
        System.out.println("Arroba: " + user.getArroba());
        System.out.println("Nome: " + user.getCpf());
        System.out.println("######################################");

        // Passa o objeto de volta para exibir os dados na tela de sucesso
        long id = listUser.size()+1;
        listUser.add(new User(id, user.getName(), user.getArroba(), user.getCpf()));
        model.addAttribute("user", user);
        return "redirect:/users/index";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, ModelMap model){
        int idInt = Math.toIntExact((id-1L));
        User userEdit = listUser.get(idInt);
        model.addAttribute("user",  userEdit );
        return "users/edit";
    }

    @PostMapping("/update/{id}")
    public String userUpdate(@PathVariable Long id, @ModelAttribute User user, ModelMap model){
        // Imprime os dados no console do servidor
        System.out.println("############ EDITANDO user ##########################");
        System.out.println("ID: " + user.getId());
        System.out.println("Nome: " + user.getName());
        System.out.println("Arroba: " + user.getArroba());
        System.out.println("Nome: " + user.getCpf());
        System.out.println("######################################");
        int idInt = Math.toIntExact((id-1L));
        User userEdit = listUser.get(idInt);
        userEdit.setName(user.getName());
        userEdit.setArroba(user.getArroba());
        userEdit.setCpf(user.getCpf());
        model.addAttribute("user", user);
        return "redirect:/users/index";
    }

    @GetMapping("/show/{id}") // detalhes do usuário
    public String show(@PathVariable Long id, ModelMap model) {
        int idInt = Math.toIntExact((id-1L));
        User userShow = listUser.get(idInt);
        model.addAttribute("user", userShow);
        return "/users/show";
    }
}

