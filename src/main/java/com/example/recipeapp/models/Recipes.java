package com.example.recipeapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class Recipes {

    private String title;

    private int timeCoocking;

    private List<Ingredients> ingredients;

    private List<String> steps;

}
