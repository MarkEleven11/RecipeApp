package com.example.recipeapp.services;
import com.example.recipeapp.dto.IngredientsDTO;
import com.example.recipeapp.exeptions.IngredientNotFoundExeption;
import com.example.recipeapp.exeptions.InvalidIngredientFormatExeption;
import com.example.recipeapp.models.Ingredients;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class IngredientsService {

    private static final String FILE_STORE_NAME = "ingredients";
    private int idCounter = 0;

    Map<Integer, Ingredients> ingredients = new HashMap<>();

    private final FileService fileService;

    public IngredientsService(FileService fileService) {
        this.fileService = fileService;
        Map <Integer, Ingredients> storedMap = fileService.readFromFile(FILE_STORE_NAME, new TypeReference<>() {
        });
        this.ingredients = Objects.requireNonNullElseGet(storedMap, HashMap::new);
    }


    // Использование библиотеки Apache
    public IngredientsDTO addIngredient (Ingredients ingredient) {
        if (StringUtils.isBlank(ingredient.getTitle())) {
            throw new InvalidIngredientFormatExeption();
        }
        int id = idCounter++;
        ingredients.put(id, ingredient);
        this.fileService.saveToFile(FILE_STORE_NAME, this.ingredients);
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
        this.fileService.saveToFile(FILE_STORE_NAME, this.ingredients);
        return IngredientsDTO.from(id, ingredient);
    }

    public IngredientsDTO deleteIngredients(int id, Ingredients ingredient) {
        Ingredients delete = ingredients.get(id);
        if (delete == null) {
            throw new IngredientNotFoundExeption();
        }
        ingredients.remove(id);
        this.fileService.saveToFile(FILE_STORE_NAME, this.ingredients);
        return IngredientsDTO.from(id, ingredient);
    }

    public List<IngredientsDTO> getAllIngredients() {
        List<IngredientsDTO> result = new ArrayList<>();
        for (Map.Entry<Integer, Ingredients> entry : ingredients.entrySet()) {
            result.add(IngredientsDTO.from(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public Resource getIngredientsFile () {
        return fileService.getResorce(FILE_STORE_NAME);
    }

    public void importRecipes (Resource resource) {
        fileService.saveResource(FILE_STORE_NAME, resource);
        this.ingredients = fileService.readFromFile(FILE_STORE_NAME, new TypeReference<>() {
        });
    }
}