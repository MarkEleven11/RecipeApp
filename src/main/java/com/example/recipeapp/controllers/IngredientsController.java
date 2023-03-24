package com.example.recipeapp.controllers;
import com.example.recipeapp.dto.IngredientsDTO;
import com.example.recipeapp.models.Ingredients;
import com.example.recipeapp.services.IngredientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD - операции и эндпоинты для работы с ингредиентами")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }


    @PostMapping
    @Operation(summary = "Добавление ингредиента в список.",
            description = "Поволяет добавить новый ингредиент.")
    public IngredientsDTO addIngredient(@RequestBody Ingredients ingredients) {
        return ingredientsService.addIngredient(ingredients);
    }

    @GetMapping("{id}")
    @Operation(summary = "Поиск ингредиента.",
            description = "Выполняет поиск ингредиента по id")
    public IngredientsDTO getIngredient(@PathVariable("id") int id) {
        return ingredientsService.getIngredient(id);
    }

    @PostMapping("{id}")
    @Operation(summary = "Редактирование ингредиента.",
            description = "Позволяет редактировать ингредиент.")
    public IngredientsDTO editIngredient(@PathVariable("id") int id, @RequestBody Ingredients ingredients) {
        return ingredientsService.editIngredients(id, ingredients);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление ингредиента.",
            description = "Удаляет ингредиент по id")
    public IngredientsDTO deleteIngredient(@PathVariable("id") int id, @RequestBody Ingredients ingredients) {
        return ingredientsService.deleteIngredients(id, ingredients);
    }

    @GetMapping
    @Operation(summary = "Получение списка всех ингредиентов.",
            description = "Выводит все заданные ингредиенты.")
    public List<IngredientsDTO> getAllIngredients() {
        return ingredientsService.getAllIngredients();
    }
}
