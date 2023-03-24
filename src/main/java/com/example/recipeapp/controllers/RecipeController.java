package com.example.recipeapp.controllers;

import com.example.recipeapp.dto.RecipeDTO;
import com.example.recipeapp.models.Recipes;
import com.example.recipeapp.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD - операции и эндпоинты для работы с рецептами")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Поиск рецепта.",
            description = "Выполняет поиск рецепта по id")
    public RecipeDTO getRecipe(@PathVariable("id") int id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта в список.",
            description = "Поволяет добавить новый рецепт.")
    public RecipeDTO addRecipe(@RequestBody Recipes recipes) {
        return recipeService.addRecipe(recipes);
    }

    @PostMapping("{id}")
    @Operation(summary = "Редактирование рецепта.",
            description = "Позволяет редактировать рецепт.")
    public RecipeDTO editRecipe(@PathVariable("id") int id, @RequestBody Recipes recipes) {
        return recipeService.editRecipe(id, recipes);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление рецепта.",
            description = "Удаляет рецепт по id")
    public RecipeDTO deleteRecipes(@PathVariable("id") int id, @RequestBody Recipes recipes) {
        return recipeService.deleteRecipe(id, recipes);
    }

    @GetMapping("byIngredient/{id}")
    @Operation(summary = "Получение рецепта по ID ингредиента.",
    description = "Выводит рецепты по ID ингредиенту.")
    public List<RecipeDTO> getByIngredientId(@PathVariable ("id") int id) {
        return recipeService.getRecipesByIngredientId(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех рецептов.",
            description = "Выводит все рецепты.")
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

}


