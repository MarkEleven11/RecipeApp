package com.example.recipeapp.services;

import com.example.recipeapp.dto.IngredientsDTO;
import com.example.recipeapp.models.Ingredients;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsService {

    private int idCounter = 0;

    Map<Integer, Ingredients> ingredients = new HashMap<>();

    public IngredientsDTO addIngredient (Ingredients ingredient) {
        int id = idCounter++;
        ingredients.put(id, ingredient);
        return IngredientsDTO.from(id, ingredient);
    }

    public IngredientsDTO getIngredient (int id) {
        Ingredients ingredient = ingredients.get(id);
        if (ingredient != null) {
            return IngredientsDTO.from(id, ingredient);
        } return null;
    }
}