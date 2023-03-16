package com.example.recipeapp.dto;
import com.example.recipeapp.models.Ingredients;
public class IngredientsDTO {

    private final int id;
    private final String title;

    private final int number;

    private final String measure;

    public IngredientsDTO(int id, String title, int number, String measure) {
        this.id = id;
        this.title = title;
        this.number = number;
        this.measure = measure;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public String getMeasure() {
        return measure;
    }

    public static IngredientsDTO from(int id, Ingredients ingredients) {
        return new IngredientsDTO(id, ingredients.getTitle(), ingredients.getNumber(), ingredients.getMeasure());
    }
}

