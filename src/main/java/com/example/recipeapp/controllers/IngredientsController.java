package com.example.recipeapp.controllers;

import com.example.recipeapp.dto.IngredientsDTO;
import com.example.recipeapp.models.Ingredients;
import com.example.recipeapp.services.IngredientsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }


    @PostMapping
    public IngredientsDTO addIngredient (@RequestBody Ingredients ingredients) {
        return ingredientsService.addIngredient(ingredients);
    }

    @GetMapping("{id}")
    public IngredientsDTO getIngredient(@PathVariable ("id") int id) {
        return  ingredientsService.getIngredient(id);
    }

}
