package com.example.recipeapp.dto;
import com.example.recipeapp.models.Ingredients;
import lombok.Data;


@Data
public class IngredientsDTO {

    private final int id;

    private final String title;

    private final int number;

    private final String measure;

    public static IngredientsDTO from(int id, Ingredients ingredients) {
        return new IngredientsDTO(id, ingredients.getTitle(), ingredients.getNumber(), ingredients.getMeasure());
    }
}

