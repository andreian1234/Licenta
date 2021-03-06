package com.licenta.controllers;

import com.licenta.dto.HitDTO;
import com.licenta.dto.RecipeDTO;
import com.licenta.dto.RecipeEatenDigest;
import com.licenta.dto.RootRecipeDTO;
import com.licenta.models.Recipe;
import com.licenta.models.RecipeEaten;
import com.licenta.models.User;
import com.licenta.services.RecipeApiService;
import com.licenta.services.RecipeService;
import com.licenta.services.URLService;
import com.licenta.services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/recipes")
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


    //Returns to the frontend a list of recipesDTO with the first 100 searches
    @SneakyThrows
    @GetMapping(path = "/search")
    public final List<RecipeDTO> searchRecies(
            @RequestBody final Map<String, Object> payload
    ) {
//        final UserDTO userDTO = currentAuthenticatedUser(request);

        String link = payload.get("link").toString();
        RootRecipeDTO rootRecipeDTO = RecipeApiService.getRootRecipeDetailsObject(URLService.getApi(link));
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        for (HitDTO hitDTO : rootRecipeDTO.getHitDTOS()) {
            recipeDTOList.add(hitDTO.getRecipeDTO());
        }

        for (int i = 0; i < 4; i++) {
            link = rootRecipeDTO.get_linksDTO().getNextDTO().getHref();
            rootRecipeDTO = RecipeApiService.getRootRecipeDetailsObject(URLService.getApi(link));
            for (HitDTO hitDTO : rootRecipeDTO.getHitDTOS())
                recipeDTOList.add(hitDTO.getRecipeDTO());
        }
        return recipeDTOList;
    }

    @SneakyThrows
    @PostMapping(path = "/search/add")
    public final RecipeEaten addRecipeEaten(
            @RequestBody final Map<String, Object> payload
    ) {
        Recipe recipe = recipeService.saveOneRecipe(RecipeApiService.getRecipeDetailsObject(URLService.getApi(payload.get("link").toString())));
        return recipeService.saveRecipeEaten(userService.findUserByEmail("andreian@gmail.com"), recipe, LocalDate.now(), (Double) payload.get("quantity"));

    }

    @GetMapping(path = "/eaten/{id}")
    public final RecipeEaten getRecipeEatenById(
            @PathVariable final Long id
    ) {
        return recipeService.findById(id);
    }

    @GetMapping(path = "eaten/stats/{id}")
    public final RecipeEatenDigest getRecipeEatenDigest(
            @PathVariable final Long id
    ) {
        return recipeService.getRecipeEatenDigest(id);
    }

    @GetMapping(path = "/eaten/stats/date/{date}")
    public final RecipeEatenDigest getDigestByDay(
            @PathVariable final String date
    ) {

        User user = userService.findUserByEmail("andreian@gmail.com");
        return recipeService.getDigestFromDate(user, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

}
