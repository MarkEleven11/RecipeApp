package com.example.recipeapp.dto;

import com.example.recipeapp.models.Ingredients;
import com.example.recipeapp.models.Recipes;

import java.util.List;

public class RecipeDTO {

    private final int id;

    private final String title;

    private final int timeCoocking;

    private final List<Ingredients> ingredients;

    private final List<String> steps;

    public RecipeDTO(int id, String title, int timeCoocking, List<Ingredients> ingredients, List<String> steps) {
        this.id = id;
        this.title = title;
        this.timeCoocking = timeCoocking;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getTimeCoocking() {
        return timeCoocking;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public static RecipeDTO from(int id, Recipes recipes) {
        return new RecipeDTO(id, recipes.getTitle(), recipes.getTimeCoocking(), recipes.getIngredients(), recipes.getSteps());
    }


}