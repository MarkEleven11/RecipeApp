package com.example.recipeapp.services;

import com.example.recipeapp.dto.RecipeDTO;
import com.example.recipeapp.exeptions.InvalidRecipeFormatExeption;
import com.example.recipeapp.exeptions.RecipeNotFoundExeption;
import com.example.recipeapp.models.Recipes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeService {

    private int idCounter = 0;

    private final Map<Integer, Recipes> recipes = new HashMap<>();


    // Использование библиотеки Apache.
    public RecipeDTO addRecipe(Recipes recipe) {
        if(StringUtils.isBlank(recipe.getTitle())) {
            throw new InvalidRecipeFormatExeption();
        }
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
    public RecipeDTO editRecipe(int id, Recipes recipe) {
        Recipes existRecipe = recipes.get(id);
        if (existRecipe == null) {
            throw new RecipeNotFoundExeption();
        }
        recipes.put(id, recipe);
        return RecipeDTO.from(id, recipe);
    }

    public RecipeDTO deleteRecipe(int id, Recipes recipe) {
        Recipes recipeFind = recipes.remove(id);
        if (recipeFind == null) {
            throw new RuntimeException();
        }
        return RecipeDTO.from(id, recipe);
    }


    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> result = new ArrayList<>();
        for (Map.Entry<Integer, Recipes> entry : recipes.entrySet()) {
            result.add(RecipeDTO.from(entry.getKey(), entry.getValue()));
        }
        return result;
    }

}