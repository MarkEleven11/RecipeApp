package com.example.recipeapp.controllers;

import com.example.recipeapp.dto.InfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
@Tag(name = "Информация о приложении", description = "Данный блок содержит описание о создании и создателях приложения.")
public class InfoController {

    private static final InfoDTO INFO = new InfoDTO("Alexandr Titov", "Recipe Book Application", LocalDate.of(2023, 3, 1),
            "This application created for managing recipes and ingredients");

    @GetMapping
    @Operation(summary = "Приветственный экран",
            description = "Выводит приветственное слово")
    public String welcome() {
        return "Welcome to the Recipe Book Application";
    }

    @GetMapping("info")
    @Operation(summary = "Информация о приложении и создателях.",
            description = "Выводит информацию о приложении и создателях.")
    public InfoDTO info() {
        return INFO;
    }
}
