package com.licenta.controllers;

import com.licenta.dto.HitDTO;
import com.licenta.dto.RecipeDTO;
import com.licenta.dto.RootRecipeDTO;
import com.licenta.services.RecipeApiService;
import com.licenta.services.RecipeService;
import com.licenta.services.URLService;
import com.licenta.services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("recipes")
public class RecipeController {


    private final UserService userService;
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(UserService userService, final RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }


    @GetMapping()
    public String verify() {
        return "merge";
    }

    @SneakyThrows
    @RequestMapping(path = "/addToDatabase/{ingredient}")
    @ResponseBody
    public final RecipeDTO addRecipesToDB(
            @PathVariable final String ingredient
    ) {
//        final UserDTO userDTO = currentAuthenticatedUser(request);

        String url = URLService.getRecipeUrl(ingredient);
        RootRecipeDTO rootRecipeDTO = RecipeApiService.getRootRecipeDetailsObject(URLService.getApi(url));
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        for (HitDTO hitDTO : rootRecipeDTO.getHitDTOS())
            recipeDTOList.add(hitDTO.getRecipeDTO());
        return recipeDTOList.get(0);
//        recipeService.saveRecipeDTOList(recipeDTOList);

//        for(int i = 0; i< 4; i++)
//        {
//            String link = rootRecipeDTO.get_linksDTO().getNextDTO().getHref();
//            rootRecipeDTO = RecipeApiService.getRootRecipeDetailsObject(URLService.getApi(link));
//            List<RecipeDTO> recipeDTOList1 = new ArrayList<>();
//            for (HitDTO hitDTO : rootRecipeDTO.getHitDTOS())
//                recipeDTOList1.add(hitDTO.getRecipeDTO());
//            recipeService.saveRecipeDTOList(recipeDTOList1);
//            System.out.println("Esti Aici !!!!!!!!!!!!!!!!!!!!!!!!!!" + i);
//            recipeDTOList1.clear();
//        }
    }


}
