package com.example.recipeapp.dto;

import com.example.recipeapp.models.Ingredients;
import com.example.recipeapp.models.Recipes;
import lombok.Data;


import java.util.List;

@Data
public class RecipeDTO {

    private final int id;

    private final String title;

    private final int timeCoocking;

    private final List<Ingredients> ingredients;

    private final List<String> steps;


    public static RecipeDTO from(int id, Recipes recipes) {
        return new RecipeDTO(id, recipes.getTitle(), recipes.getTimeCoocking(), recipes.getIngredients(), recipes.getSteps());
    }


}