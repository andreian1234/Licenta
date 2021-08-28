package com.licenta.controllers;

import com.google.gson.JsonObject;
import com.licenta.models.RootRecipe;
import com.licenta.services.RecipeApiService;
import com.licenta.services.URLService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("recipes")
public class RecipeController {

    RecipeApiService recipeApiService;
    URLService urlService;

    @GetMapping(path = "")
    public String verify(){
        System.out.println("merge");
        return "merge";
    }

    @SneakyThrows
    @GetMapping(path = "recipes/{ingredient}")
    public void show(@PathVariable String ingredient)
    {
        String url = urlService.getRecipeUrl(ingredient);
        RootRecipe rootRecipe = recipeApiService.getRootRecipeDetailsObject(urlService.getApi(url));
        System.out.println(rootRecipe.getTo());

    }
}
