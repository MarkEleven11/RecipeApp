package com.example.recipeapp.controllers;

import com.example.recipeapp.dto.InfoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class InfoController {

    private static final InfoDTO INFO = new InfoDTO("Alexandr Titov", "Recipe Book Application", LocalDate.of(2023, 3, 1),
            "This application created for managing recipes and ingredients");

    @GetMapping
    public String welcome() {
        return "Welcome to the Recipe Book Application";
    }

    @GetMapping("info")
    public InfoDTO info() {
        return  INFO;
    }
}
