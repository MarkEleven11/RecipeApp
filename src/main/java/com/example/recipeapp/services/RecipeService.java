package com.example.recipeapp.services;

import com.example.recipeapp.dto.IngredientsDTO;
import com.example.recipeapp.dto.RecipeDTO;
import com.example.recipeapp.exeptions.InvalidRecipeFormatExeption;
import com.example.recipeapp.exeptions.RecipeNotFoundExeption;
import com.example.recipeapp.models.Ingredients;
import com.example.recipeapp.models.Recipes;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final static String STORE_FILE_NAME = "recipes";
    private int idCounter = 0;

    private Map<Integer, Recipes> recipes = new HashMap<>();

    private final IngredientsService ingredientsService;

    private final FileService fileService;


    public RecipeService(IngredientsService ingredientsService, FileService fileService) {
        this.ingredientsService = ingredientsService;
        this.fileService = fileService;
        Map<Integer, Recipes> storedMap = fileService.readFromFile(STORE_FILE_NAME, new TypeReference<>() {
        });
        this.recipes = Objects.requireNonNullElseGet(storedMap, HashMap::new);
    }


    // Использование библиотеки Apache.
    public RecipeDTO addRecipe(Recipes recipe) {
        if (StringUtils.isBlank(recipe.getTitle())) {
            throw new InvalidRecipeFormatExeption();
        }
        int id = idCounter++;
        recipes.put(id, recipe);
        for (Ingredients ingredient : recipe.getIngredients()) {
            this.ingredientsService.addIngredient(ingredient);
        }
        this.fileService.saveToFile(STORE_FILE_NAME, this.recipes);
        return RecipeDTO.from(id, recipe);
    }

    public List<RecipeDTO> getRecipesByIngredientId(int ingredientId) {
        IngredientsDTO ingredie = this.ingredientsService.getIngredient(ingredientId);
        return this.recipes.entrySet()
                .stream()
                .filter(e -> e.getValue().getIngredients().stream().anyMatch(i -> i.getTitle().equals(ingredie)))
                .map(e -> RecipeDTO.from(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public RecipeDTO getRecipe(int id) {
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
        this.fileService.saveToFile(STORE_FILE_NAME, this.recipes);
        return RecipeDTO.from(id, recipe);
    }

    public RecipeDTO deleteRecipe(int id, Recipes recipe) {
        Recipes recipeFind = recipes.remove(id);
        if (recipeFind == null) {
            throw new RuntimeException();
        }
        this.fileService.saveToFile(STORE_FILE_NAME, this.recipes);
        return RecipeDTO.from(id, recipe);
    }


    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> result = new ArrayList<>();
        for (Map.Entry<Integer, Recipes> entry : recipes.entrySet()) {
            result.add(RecipeDTO.from(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public Resource getRecipesFile() {
        return fileService.getResorce(STORE_FILE_NAME);
    }

    public void importRecipes(Resource resource) {
        fileService.saveResource(STORE_FILE_NAME, resource);
        this.recipes = fileService.readFromFile(STORE_FILE_NAME, new TypeReference<>() {
        });
    }

    public void exportFileFromMemories(PrintWriter writer) {
        for (Recipes recipes : this.recipes.values()) {
            writer.println(recipes.getTitle());
            writer.println("Время приготовления: %d минут".formatted(recipes.getTimeCoocking()));
            writer.println("Ингредиенты:");
            for (Ingredients ingredients : recipes.getIngredients()) {
                writer.println("\t%s - %d %s".formatted(ingredients.getTitle(), ingredients.getNumber(), ingredients.getMeasure()));
            }
            writer.println("Инструкция приготовления");
            for (int i = 0; i < recipes.getSteps().size(); i++) {
                writer.println("%d. %s".formatted(i+1, recipes.getSteps().get(i)));
            }
            writer.println(" ");
        }
        writer.flush();
    }

}