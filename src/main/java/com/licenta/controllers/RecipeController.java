package com.licenta.controllers;

import com.licenta.dto.RootRecipeDTO;
import com.licenta.services.RecipeApiService;
import com.licenta.services.URLService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recipes")
public class RecipeController {


    @GetMapping(path = "")
    public String verify(){
        return "merge";
    }

    @SneakyThrows
    @GetMapping(path = "/{ingredient}")
    public int show(@PathVariable String ingredient) {
        String url = URLService.getRecipeUrl(ingredient);
        RootRecipeDTO rootRecipeDTO = RecipeApiService.getRootRecipeDetailsObject(URLService.getApi(url));
        return rootRecipeDTO.getTo();

    }
}
