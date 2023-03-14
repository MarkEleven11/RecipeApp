package com.example.recipeapp.controllers;

import com.example.recipeapp.dto.RecipeDTO;
import com.example.recipeapp.models.Recipes;
import com.example.recipeapp.services.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public RecipeDTO getRecipe(@PathVariable("id") int id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping
    public RecipeDTO addRecipe(@RequestBody Recipes recipes){
        return recipeService.addRecipe(recipes);
    }
}

