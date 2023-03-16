package com.example.recipeapp.controllers;

import com.example.recipeapp.dto.RecipeDTO;
import com.example.recipeapp.models.Recipes;
import com.example.recipeapp.services.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("{id}")
    public RecipeDTO editRecipe(@PathVariable ("id") int id, @RequestBody Recipes recipes) {
        return recipeService.editRecipe(id, recipes);
    }

    @DeleteMapping("{id}")
    public RecipeDTO deleteRecipes(@PathVariable ("id") int id, @RequestBody Recipes recipes) {
        return  recipeService.deleteRecipe(id, recipes);
    }
    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return  recipeService.getAllRecipes();
    }
}


