package com.licenta.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("recipes")
public class RecipeController {


//    private final UserService userService;
//    private final RecipeService recipeService;
//
//    @Autowired
//    public RecipeController(UserService userService, final RecipeService recipeService) {
//        this.userService = userService;
//        this.recipeService = recipeService;
//    }
//
//
//    @GetMapping()
//    public String verify() {
//        return "merge";
//    }
//
//    @SneakyThrows
//    @RequestMapping(path = "/{ingredient}")
//    @ResponseBody
//    public final List<Recipe> addRecipesToDB(
//            @PathVariable final String ingredient
//    ) {
//////        final UserDTO userDTO = currentAuthenticatedUser(request);
////
////        String url = URLService.getRecipeUrl(ingredient);
////        RootRecipeDTO rootRecipeDTO = RecipeApiService.getRootRecipeDetailsObject(URLService.getApi(url));
////        List<RecipeDTO> recipeDTOList = new ArrayList<>();
////        for (HitDTO hitDTO : rootRecipeDTO.getHitDTOS())
////            recipeDTOList.add(hitDTO.getRecipeDTO());
////        recipeService.saveRecipeDTOList(recipeDTOList);
////        return recipeService.searchRecipes();
////    }


}
