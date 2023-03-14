package com.example.recipeapp.services;

import com.example.recipeapp.dto.RecipeDTO;
import com.example.recipeapp.models.Recipes;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {

    private int idCounter = 0;

    private final Map<Integer, Recipes> recipes = new HashMap<>();

    public RecipeDTO addRecipe(Recipes recipe) {
        int id = idCounter++;
        recipes.put(id, recipe);
        return RecipeDTO.from(id, recipe);
    }

    public RecipeDTO getRecipe (int id) {
        Recipes recipe = recipes.get(id);
        if (recipe != null) {
            return RecipeDTO.from(id, recipe);
        }
        return null;
    }
}