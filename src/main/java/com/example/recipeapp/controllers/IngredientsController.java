package com.example.recipeapp.controllers;

import com.example.recipeapp.dto.IngredientsDTO;
import com.example.recipeapp.models.Ingredients;
import com.example.recipeapp.services.IngredientsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("{id}")
    public  IngredientsDTO editIngredient(@PathVariable ("id") int id, @RequestBody Ingredients ingredients) {
        return ingredientsService.editIngredients(id, ingredients);
    }

    @DeleteMapping("{id}")
    public IngredientsDTO deleteIngredient (@PathVariable ("id") int id, @RequestBody Ingredients ingredients) {
        return ingredientsService.deleteIngredients(id, ingredients);
    }

    @GetMapping
    public List<IngredientsDTO> getAllIngredients() {
        return  ingredientsService.getAllIngredients();
    }
}
