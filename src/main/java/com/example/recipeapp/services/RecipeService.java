package com.example.recipeapp.services;

import com.example.recipeapp.dto.RecipeDTO;
import com.example.recipeapp.exeptions.InvalidRecipeFormatExeption;
import com.example.recipeapp.exeptions.RecipeNotFoundExeption;
import com.example.recipeapp.models.Recipes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    private int idCounter = 0;

    private Map<Integer, Recipes> recipes = new HashMap<>();

    private final FileService fileService;

    public RecipeService(FileService fileService) {
        this.fileService = fileService;
    }


    // Использование библиотеки Apache.
    public RecipeDTO addRecipe(Recipes recipe) {
        if(StringUtils.isBlank(recipe.getTitle())) {
            throw new InvalidRecipeFormatExeption();
        }
        int id = idCounter++;
        recipes.put(id, recipe);
        saveToFile();
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
        saveToFile();
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

    private void saveToFile() {
        try {
           String json =  new ObjectMapper().writeValueAsString(recipes);
           fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = fileService.readFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipes>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}