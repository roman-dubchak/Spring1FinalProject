package com.gb.hw_lesson12_finalproject.controller;

import com.gb.hw_lesson12_finalproject.dto.PersonInput;
import com.gb.hw_lesson12_finalproject.dto.ErrorDto;
import com.gb.hw_lesson12_finalproject.entities.Person;
import com.gb.hw_lesson12_finalproject.exeption.PersonNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.gb.hw_lesson12_finalproject.repo.PersonRepo;

import javax.validation.Valid;
import java.util.UUID;


@Controller
@RequestMapping("/person")
public class PersonController {

    //TODO
    // 1. Собрать весь проект.
    // 1.1 Добавить в Продукт Max, Min, FindByTitle 1.2 Переделать Rest Controller PersonController на Controller
    // 1.2 Добавить Cart
    // 2. Подключить Security 2.0 Добавиьт внешний ключ person -> role ManyToOne
    // 2.1 Создать страницу со списком товаров, на которой можно добавлять позиции
    // и редактировать существующие. На эту страницу должны иметь доступ админы и менеджеры.
    // 2.2 Создать страницу со списком всех пользователей, к которой имеют доступ только админы.
    // 2.3 Добавить роль суперадмина и дать ему возможность создавать новых пользователей
    // и указывать роли существующим.


    private final PersonRepo personRepo;
    public PersonController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping("/")
    public String findAll(Model model){
        model.addAttribute("persons", personRepo.findAll());
        return "person";
    }

    @GetMapping("/delete/{id}")
    public String deletePersonById(@PathVariable("id") Long id, Model model){
       personRepo.deleteById(id);
       return "redirect:/person/";
    }

    @PostMapping("/save")
    public String saveCustomer(@RequestBody @Valid PersonInput personInput){
        Person customer = new Person();
        customer.setFirstname(personInput.getFirstname());
        customer.setLastname(personInput.getLastname());
        customer.setCity(personInput.getCity());
        personRepo.save(customer);
        return "redirect:/person/";
    }

    @GetMapping("/{id}")
    public String findPersonBiId(Model model, @PathVariable("id") Long id){
         model.addAttribute("selectPerson", personRepo.findById(id).orElseThrow(()
                -> new PersonNotFoundException(String.format("Not found Person by this id %s", id))));
        return "/persondetails";
    }

    @ExceptionHandler
    public ErrorDto errorDto(PersonNotFoundException e){
        return new ErrorDto(e.getMessage());
    }

    @GetMapping("/form-person")
    public String formAddPerson(PersonInput personInput, Model model){
        model.addAttribute("person", personInput);
        System.out.println("GetMapping personInput - " + personInput);
        return "form-person";
    }

    @PostMapping(value = "/form-person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String formAddPerson(PersonInput personInput){
        Person person = new Person();
//        person.setId(UUID.randomUUID());
        person.setFirstname(personInput.getFirstname());
        person.setLastname(personInput.getLastname());
        person.setCity(personInput.getCity());
        person.setLogin(personInput.getLogin());
        person.setPassword(personInput.getPassword());
        System.out.println("POST save person - " + person);
        personRepo.save(person);
        return "redirect:/person/";
    }

}
