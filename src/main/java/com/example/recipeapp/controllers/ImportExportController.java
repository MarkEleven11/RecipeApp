package com.example.recipeapp.controllers;

import com.example.recipeapp.services.IngredientsService;
import com.example.recipeapp.services.RecipeService;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImportExportController {

    private final RecipeService recipeService;
    private final IngredientsService ingredientsService;

    public ImportExportController(RecipeService recipeService, IngredientsService ingredientsService) {
        this.recipeService = recipeService;
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/files/exports/recipes")
    public ResponseEntity<Resource> downloadRecipes() {
        Resource recipes = recipeService.getRecipesFile();
        ContentDisposition disposition = ContentDisposition.attachment()
                .name("recipe.json")
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(disposition);
        return ResponseEntity.ok().headers(httpHeaders).body(recipes);
    }

    @GetMapping("files/exports/ingredients")
    public  ResponseEntity<Resource> downloadIngredients() {
        Resource ingredients = ingredientsService.getIngredientsFile();
        ContentDisposition disposition = ContentDisposition.attachment()
                .name("ingredients.json")
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(disposition);
        return ResponseEntity.ok().headers(httpHeaders).body(ingredients);

    }

    @PostMapping(value = "/files/import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importRecipes(@RequestParam MultipartFile file) {
        this.recipeService.importRecipes(file.getResource());
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/files/import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importIngredients(@RequestParam MultipartFile file) {
        this.ingredientsService.importRecipes(file.getResource());
        return ResponseEntity.noContent().build();
    }
}
