package com.example.recipeapp.services;

import com.example.recipeapp.dto.IngredientsDTO;
import com.example.recipeapp.exeptions.IngredientNotFoundExeption;
import com.example.recipeapp.exeptions.InvalidIngredientFormatExeption;
import com.example.recipeapp.models.Ingredients;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientsService {

    private int idCounter = 0;

    Map<Integer, Ingredients> ingredients = new HashMap<>();


    // Использование библиотеки Apache
    public IngredientsDTO addIngredient (Ingredients ingredient) {
        if (StringUtils.isBlank(ingredient.getTitle())) {
            throw new InvalidIngredientFormatExeption();
        }
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

    public IngredientsDTO editIngredients(int id, Ingredients ingredient) {
        Ingredients existIngredient = ingredients.get(id);
        if (existIngredient == null) {
            throw new IngredientNotFoundExeption();
        }
        ingredients.put(id, ingredient);
        return IngredientsDTO.from(id, ingredient);
    }

    public IngredientsDTO deleteIngredients(int id, Ingredients ingredient) {
        Ingredients delete = ingredients.get(id);
        if (delete == null) {
            throw new IngredientNotFoundExeption();
        }
        ingredients.remove(id);
        return IngredientsDTO.from(id, ingredient);
    }

    public List<IngredientsDTO> getAllIngredients() {
        List<IngredientsDTO> result = new ArrayList<>();
        for (Map.Entry<Integer, Ingredients> entry : ingredients.entrySet()) {
            result.add(IngredientsDTO.from(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}