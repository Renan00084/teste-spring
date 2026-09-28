package com.renan.teste_spring.controllers;

import com.renan.teste_spring.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private List<Car> listCar = new ArrayList<Car>();

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.addAttribute("cars", listCar);
        model.addAttribute("size", listCar.size());
        return "cars/index";
    }

    @GetMapping("/new")
    public String carNew(ModelMap model) {
        model.addAttribute("car", new Car());
        return "cars/new";
    }

    @PostMapping("/create")
    public String carCreate(@ModelAttribute Car car, ModelMap model) {

        System.out.println("############ criando novo carro ##########################");
        System.out.println("Marca: " + car.getMarca());
        System.out.println("Modelo: " + car.getModelo());
        System.out.println("Ano: " + car.getAno());
        System.out.println("######################################");

        long id = listCar.size() + 1;

        listCar.add(new Car(id, car.getMarca(), car.getModelo(), car.getAno()));

        model.addAttribute("car", car);

        return "redirect:/cars/index";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, ModelMap model) {

        int idInt = Math.toIntExact(id - 1L);

        Car carEdit = listCar.get(idInt);

        model.addAttribute("car", carEdit);

        return "cars/edit";
    }

    @PostMapping("/update/{id}")
    public String carUpdate(@PathVariable Long id,@ModelAttribute Car car, ModelMap model) {

        System.out.println("############ EDITANDO carro ##########################");
        System.out.println("ID: " + car.getId());
        System.out.println("Marca: " + car.getMarca());
        System.out.println("Modelo: " + car.getModelo());
        System.out.println("Ano: " + car.getAno());
        System.out.println("######################################");

        int idInt = Math.toIntExact(id - 1L);

        Car carEdit = listCar.get(idInt);

        carEdit.setMarca(car.getMarca());
        carEdit.setModelo(car.getModelo());
        carEdit.setAno(car.getAno());

        model.addAttribute("car", car);

        return "redirect:/cars/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {

        int idInt = Math.toIntExact(id - 1L);

        Car carShow = listCar.get(idInt);

        model.addAttribute("car", carShow);

        return "cars/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {

        int idInt = Math.toIntExact(id - 1L);

        listCar.remove(idInt);

        return "redirect:/cars/index";
    }
}