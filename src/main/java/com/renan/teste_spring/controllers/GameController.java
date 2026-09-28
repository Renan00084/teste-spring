package com.renan.teste_spring.controllers;

import com.renan.teste_spring.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private List<Game> listGame = new ArrayList<Game>();

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.addAttribute("games", listGame);
        model.addAttribute("size", listGame.size());
        return "games/index";
    }

    @GetMapping("/new")
    public String gameNew(ModelMap model) {
        model.addAttribute("game", new Game());
        return "games/new";
    }

    @PostMapping("/create")
    public String gameCreate(@ModelAttribute Game game, ModelMap model) {

        System.out.println("############ criando novo jogo ##########################");
        System.out.println("Nome: " + game.getNome());
        System.out.println("Gênero: " + game.getGenero());
        System.out.println("Plataforma: " + game.getPlataforma());
        System.out.println("######################################");

        long id = listGame.size() + 1;

        listGame.add(new Game(id, game.getNome(), game.getGenero(), game.getPlataforma()));

        model.addAttribute("game", game);

        return "redirect:/games/index";
    }

    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable Long id, ModelMap model) {

        int idInt = Math.toIntExact(id - 1L);

        Game gameEdit = listGame.get(idInt);

        model.addAttribute("game", gameEdit);

        return "games/edit";
    }

    @PostMapping("/update/{id}")
    public String gameUpdate(@PathVariable Long id, @ModelAttribute Game game, ModelMap model) {

        System.out.println("############ EDITANDO jogo ##########################");
        System.out.println("ID: " + game.getId());
        System.out.println("Nome: " + game.getNome());
        System.out.println("Gênero: " + game.getGenero());
        System.out.println("Plataforma: " + game.getPlataforma());
        System.out.println("######################################");

        int idInt = Math.toIntExact(id - 1L);

        Game gameEdit = listGame.get(idInt);

        gameEdit.setNome(game.getNome());
        gameEdit.setGenero(game.getGenero());
        gameEdit.setPlataforma(game.getPlataforma());

        model.addAttribute("game", game);

        return "redirect:/games/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {

        int idInt = Math.toIntExact(id - 1L);

        Game gameShow = listGame.get(idInt);

        model.addAttribute("game", gameShow);

        return "games/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {

        int idInt = Math.toIntExact(id - 1L);

        listGame.remove(idInt);

        return "redirect:/games/index";
    }
}