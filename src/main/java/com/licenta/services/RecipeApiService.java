package com.licenta.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.licenta.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeApiService {


    public static RootRecipeDTO getRootRecipeDetailsObject(JsonObject jsonObject) throws Exception {


        RootRecipeDTO rootRecipeDTOObj = new RootRecipeDTO();
        LinksDTO linksDTOObj;
        NextDTO nextDTOObj;
        SelfDTO selfDTOObj;
        var response = jsonObject;

        //Get root primitives from response and put them in an object
        var from = (JsonPrimitive) response.get("from");
        rootRecipeDTOObj.setFrom(from.getAsInt());

        var to = (JsonPrimitive) response.get("to");
        rootRecipeDTOObj.setTo(to.getAsInt());

        var count = (JsonPrimitive) response.get("count");
        rootRecipeDTOObj.setTo(count.getAsInt());

        JsonObject linksJsonObj = (JsonObject) response.get("_links");
        JsonObject nextJsonObj = (JsonObject) linksJsonObj.get("next");
        var href = (JsonPrimitive) nextJsonObj.get("href");
        var title = (JsonPrimitive) nextJsonObj.get("title");

        nextDTOObj = new NextDTO(href.getAsString(), title.getAsString());

        selfDTOObj = new SelfDTO("", "");
        linksDTOObj = new LinksDTO(nextDTOObj, selfDTOObj);
        rootRecipeDTOObj.set_linksDTO(linksDTOObj);


        //Get recipe array and put it in the root object//

        JsonArray recipesJsonArray = (JsonArray) response.get("hits");
        List<HitDTO> hitDTOListObj = new ArrayList<>();
        for (int i = 0; i < recipesJsonArray.size(); i++) {
            HitDTO hitDTOObj;
            RecipeDTO recipeDTOObj;
            LinksDTO linksDTO1Obj;
            SelfDTO selfDTO1Obj;
            NextDTO nextDTO1Obj;


            //Get recipe link Obj//

            JsonObject links1JsonObj = (JsonObject) ((JsonObject) recipesJsonArray.get(i)).get("_links");
            JsonObject self1JsonObj = (JsonObject) links1JsonObj.get("self");

            nextDTO1Obj = new NextDTO("", "");

            var href1 = (JsonPrimitive) self1JsonObj.get("href");
            var title1 = (JsonPrimitive) self1JsonObj.get("title");
            selfDTO1Obj = new SelfDTO(href1.getAsString(), title1.getAsString());

            linksDTO1Obj = new LinksDTO(nextDTO1Obj, selfDTO1Obj);


            JsonObject recipeJson = (JsonObject) ((JsonObject) recipesJsonArray.get(i)).get("recipe");

            //Get recipe primitives//

            var label1 = "label";
            var label = (JsonPrimitive) recipeJson.get(label1);
            var image1 = "image";
            var image = (JsonPrimitive) recipeJson.get(image1);
            var url = (JsonPrimitive) recipeJson.get("url");
            var yield1 = (JsonPrimitive) recipeJson.get("yield");
            var calories = (JsonPrimitive) recipeJson.get("calories");
            var totalWeight = (JsonPrimitive) recipeJson.get("totalWeight");
            var totalTime = (JsonPrimitive) recipeJson.get("totalTime");

            //Get ObjArrays//
            List<String> dietLabelsListObj = new ArrayList<>();
            if (recipeJson.has("dietLabels")) {
                JsonArray dietLabelsJsonArray = (JsonArray) recipeJson.get("dietLabels");
                setLists(dietLabelsJsonArray, dietLabelsListObj);
            }

            List<String> healthLabelsListObj = new ArrayList<>();
            if (recipeJson.has("healthLabels")) {
                JsonArray healthLabelsJsonArray = (JsonArray) recipeJson.get("healthLabels");
                setLists(healthLabelsJsonArray, healthLabelsListObj);
            }

            List<String> cautionsLabelsListObj = new ArrayList<>();
            if (recipeJson.has("cautions")) {
                JsonArray cautionsLabelsJsonArray = (JsonArray) recipeJson.get("cautions");
                setLists(cautionsLabelsJsonArray, cautionsLabelsListObj);
            }

            List<String> ingredientLinesListObj = new ArrayList<>();
            if (recipeJson.has("ingredientLines")) {
                JsonArray ingredientLinesLabelsJsonArray = (JsonArray) recipeJson.get("ingredientLines");
                setLists(ingredientLinesLabelsJsonArray, ingredientLinesListObj);
            }

            List<String> cuisineTypeLabelsListObj = new ArrayList<>();
            if (recipeJson.has("cuisineType")) {
                JsonArray cuisineTypeJsonArray = (JsonArray) recipeJson.get("cuisineType");
                setLists(cuisineTypeJsonArray, cuisineTypeLabelsListObj);
            }

            List<String> mealTypeListObj = new ArrayList<>();
            if (recipeJson.has("mealType")) {
                JsonArray mealTypeJsonArray = (JsonArray) recipeJson.get("mealType");
                setLists(mealTypeJsonArray, mealTypeListObj);
            }

            List<String> dishTypeListObj = new ArrayList<>();
            if (recipeJson.has("dishType")) {
                JsonArray dishTypeJsonArray = (JsonArray) recipeJson.get("dishType");
                setLists(dishTypeJsonArray, dishTypeListObj);
            }
            //Get ingredients Obj List//

            JsonArray ingredientsJsonArray = (JsonArray) recipeJson.get("ingredients");
            List<IngredientDTO> ingredientDTOListObj = new ArrayList<>();
            for (int j = 0; j < ingredientsJsonArray.size(); j++) {


                IngredientDTO ingredientDTOObj = new IngredientDTO();

                if (((JsonObject) ingredientsJsonArray.get(j)).get(image1).isJsonNull()) {
                    ingredientDTOObj.setImage("");
                } else ingredientDTOObj.setImage(((JsonObject) ingredientsJsonArray.get(j)).get(image1).getAsString());

                if (((JsonObject) ingredientsJsonArray.get(j)).get("weight").isJsonNull()) {
                    ingredientDTOObj.setWeight(0.0);
                } else
                    ingredientDTOObj.setWeight(((JsonObject) ingredientsJsonArray.get(j)).get("weight").getAsDouble());

                if (((JsonObject) ingredientsJsonArray.get(j)).get("foodCategory").isJsonNull()) {
                    ingredientDTOObj.setFoodCategory("");
                } else
                    ingredientDTOObj.setFoodCategory(((JsonObject) ingredientsJsonArray.get(j)).get("foodCategory").getAsString());

                if (((JsonObject) ingredientsJsonArray.get(j)).get("foodId").isJsonNull()) {
                    ingredientDTOObj.setFoodId("");
                } else
                    ingredientDTOObj.setFoodId(((JsonObject) ingredientsJsonArray.get(j)).get("foodId").getAsString());

                if (((JsonObject) ingredientsJsonArray.get(j)).get("text").isJsonNull()) {
                    ingredientDTOObj.setText("");
                } else ingredientDTOObj.setText(((JsonObject) ingredientsJsonArray.get(j)).get("text").getAsString());

                ingredientDTOListObj.add(ingredientDTOObj);
            }

            JsonArray digestJsonArray = (JsonArray) recipeJson.get("digest");
            List<DigestDTO> digestDTOListObj = new ArrayList<>();
            for (int j = 0; j < digestJsonArray.size(); j++) {
                DigestDTO digestDTOObj = new DigestDTO();

                if (((JsonObject) digestJsonArray.get(j)).get(label1).isJsonNull()) {
                    digestDTOObj.setLabel("");
                } else digestDTOObj.setLabel(((JsonObject) digestJsonArray.get(j)).get(label1).getAsString());

                if (((JsonObject) digestJsonArray.get(j)).get("tag").isJsonNull()) {
                    digestDTOObj.setTag("");
                } else digestDTOObj.setTag(((JsonObject) digestJsonArray.get(j)).get("tag").getAsString());

                var schemaOrgTag = "schemaOrgTag";
                if (((JsonObject) digestJsonArray.get(j)).get(schemaOrgTag).isJsonNull()) {
                    digestDTOObj.setSchemaOrgTag("");
                } else
                    digestDTOObj.setSchemaOrgTag(((JsonObject) digestJsonArray.get(j)).get(schemaOrgTag).getAsString());

                var total = "total";
                if (((JsonObject) digestJsonArray.get(j)).get(total).isJsonNull()) {
                    digestDTOObj.setTotal(0);
                } else digestDTOObj.setTotal(((JsonObject) digestJsonArray.get(j)).get(total).getAsDouble());

                var hasRDI = "hasRDI";
                if (((JsonObject) digestJsonArray.get(j)).get(hasRDI).isJsonNull()) {
                    digestDTOObj.setHasRDI(false);
                } else digestDTOObj.setHasRDI(((JsonObject) digestJsonArray.get(j)).get(hasRDI).getAsBoolean());

                var daily = "daily";
                if (((JsonObject) digestJsonArray.get(j)).get(daily).isJsonNull()) {
                    digestDTOObj.setDaily(0.0);
                } else digestDTOObj.setDaily(((JsonObject) digestJsonArray.get(j)).get(daily).getAsDouble());

                if (((JsonObject) digestJsonArray.get(j)).get("unit").isJsonNull()) {
                    digestDTOObj.setUnit("");
                } else digestDTOObj.setUnit(((JsonObject) digestJsonArray.get(j)).get("unit").getAsString());


                if (j == 0 || j == 1) {

                    JsonArray subArrayJson = (JsonArray) ((JsonObject) digestJsonArray.get(j)).get("sub");
                    List<SubDTO> subDTOListObj = new ArrayList<>();
                    for (int k = 0; k < subArrayJson.size(); k++) {
                        SubDTO subDTOObj = new SubDTO();
                        if (((JsonObject) subArrayJson.get(k)).get(label1).isJsonNull()) {
                            subDTOObj.setLabel("");
                        } else subDTOObj.setLabel(((JsonObject) subArrayJson.get(j)).get(label1).getAsString());

                        if (((JsonObject) subArrayJson.get(j)).get("tag").isJsonNull()) {
                            subDTOObj.setTag("");
                        } else subDTOObj.setTag(((JsonObject) subArrayJson.get(j)).get("tag").getAsString());

                        if (((JsonObject) subArrayJson.get(j)).get(schemaOrgTag).isJsonNull()) {
                            subDTOObj.setSchemaOrgTag("");
                        } else
                            subDTOObj.setSchemaOrgTag(((JsonObject) subArrayJson.get(j)).get(schemaOrgTag).getAsString());

                        if (((JsonObject) subArrayJson.get(j)).get(total).isJsonNull()) {
                            subDTOObj.setTotal(0);
                        } else subDTOObj.setTotal(((JsonObject) subArrayJson.get(j)).get(total).getAsDouble());

                        if (((JsonObject) subArrayJson.get(j)).get(hasRDI).isJsonNull()) {
                            subDTOObj.setHasRDI(false);
                        } else subDTOObj.setHasRDI(((JsonObject) subArrayJson.get(j)).get(hasRDI).getAsBoolean());

                        if (((JsonObject) subArrayJson.get(j)).get(daily).isJsonNull()) {
                            subDTOObj.setDaily(0.0);
                        } else subDTOObj.setDaily(((JsonObject) subArrayJson.get(j)).get(daily).getAsDouble());

                        if (((JsonObject) subArrayJson.get(j)).get("unit").isJsonNull()) {
                            subDTOObj.setUnit("");
                        } else subDTOObj.setUnit(((JsonObject) subArrayJson.get(j)).get("unit").getAsString());

                        subDTOListObj.add(subDTOObj);
                    }
                    digestDTOObj.setSubDTO(subDTOListObj);
                } else
                    digestDTOObj.setSubDTO(null);

                digestDTOListObj.add(digestDTOObj);

            }
            recipeDTOObj = new RecipeDTO(label.getAsString(), image.getAsString(), url.getAsString(),
                    yield1.getAsInt(), calories.getAsDouble(), totalWeight.getAsDouble(), cuisineTypeLabelsListObj, mealTypeListObj, dishTypeListObj, dietLabelsListObj, healthLabelsListObj, cautionsLabelsListObj, ingredientLinesListObj, ingredientDTOListObj,
                    digestDTOListObj, href1.getAsString());

            hitDTOObj = new HitDTO(recipeDTOObj, linksDTO1Obj);
            hitDTOListObj.add(hitDTOObj);
        }
        rootRecipeDTOObj.setHitDTOS(hitDTOListObj);

        return rootRecipeDTOObj;
    }


    public static RecipeDTO getRecipeDetailsObject(JsonObject jsonObject) {

        RecipeDTO recipeDTOObj;

        var response = jsonObject;
        var recipeJson = (JsonObject) response.get("recipe");
        var links = (JsonObject) response.get("_links");
        var self = (JsonObject) links.get("self");
        var href = self.get("href").getAsString();

        var label1 = "label";
        var label = (JsonPrimitive) recipeJson.get(label1);
        var image1 = "image";
        var image = (JsonPrimitive) recipeJson.get(image1);
        var url = (JsonPrimitive) recipeJson.get("url");
        var yield1 = (JsonPrimitive) recipeJson.get("yield");
        var calories = (JsonPrimitive) recipeJson.get("calories");
        var totalWeight = (JsonPrimitive) recipeJson.get("totalWeight");
        var totalTime = (JsonPrimitive) recipeJson.get("totalTime");

        //Get ObjArrays//
        List<String> dietLabelsListObj = new ArrayList<>();
        if (recipeJson.has("dietLabels")) {
            JsonArray dietLabelsJsonArray = (JsonArray) recipeJson.get("dietLabels");
            setLists(dietLabelsJsonArray, dietLabelsListObj);
        }

        List<String> healthLabelsListObj = new ArrayList<>();
        if (recipeJson.has("healthLabels")) {
            JsonArray healthLabelsJsonArray = (JsonArray) recipeJson.get("healthLabels");
            setLists(healthLabelsJsonArray, healthLabelsListObj);
        }

        List<String> cautionsLabelsListObj = new ArrayList<>();
        if (recipeJson.has("cautions")) {
            JsonArray cautionsLabelsJsonArray = (JsonArray) recipeJson.get("cautions");
            setLists(cautionsLabelsJsonArray, cautionsLabelsListObj);
        }

        List<String> ingredientLinesListObj = new ArrayList<>();
        if (recipeJson.has("ingredientLines")) {
            JsonArray ingredientLinesLabelsJsonArray = (JsonArray) recipeJson.get("ingredientLines");
            setLists(ingredientLinesLabelsJsonArray, ingredientLinesListObj);
        }

        List<String> cuisineTypeLabelsListObj = new ArrayList<>();
        if (recipeJson.has("cuisineType")) {
            JsonArray cuisineTypeJsonArray = (JsonArray) recipeJson.get("cuisineType");
            setLists(cuisineTypeJsonArray, cuisineTypeLabelsListObj);
        }

        List<String> mealTypeListObj = new ArrayList<>();
        if (recipeJson.has("mealType")) {
            JsonArray mealTypeJsonArray = (JsonArray) recipeJson.get("mealType");
            setLists(mealTypeJsonArray, mealTypeListObj);
        }

        List<String> dishTypeListObj = new ArrayList<>();
        if (recipeJson.has("dishType")) {
            JsonArray dishTypeJsonArray = (JsonArray) recipeJson.get("dishType");
            setLists(dishTypeJsonArray, dishTypeListObj);
        }
        //Get ingredients Obj List//

        JsonArray ingredientsJsonArray = (JsonArray) recipeJson.get("ingredients");
        List<IngredientDTO> ingredientDTOListObj = new ArrayList<>();
        for (int j = 0; j < ingredientsJsonArray.size(); j++) {


            IngredientDTO ingredientDTOObj = new IngredientDTO();

            if (((JsonObject) ingredientsJsonArray.get(j)).get(image1).isJsonNull()) {
                ingredientDTOObj.setImage("");
            } else ingredientDTOObj.setImage(((JsonObject) ingredientsJsonArray.get(j)).get(image1).getAsString());

            if (((JsonObject) ingredientsJsonArray.get(j)).get("weight").isJsonNull()) {
                ingredientDTOObj.setWeight(0.0);
            } else
                ingredientDTOObj.setWeight(((JsonObject) ingredientsJsonArray.get(j)).get("weight").getAsDouble());

            if (((JsonObject) ingredientsJsonArray.get(j)).get("foodCategory").isJsonNull()) {
                ingredientDTOObj.setFoodCategory("");
            } else
                ingredientDTOObj.setFoodCategory(((JsonObject) ingredientsJsonArray.get(j)).get("foodCategory").getAsString());

            if (((JsonObject) ingredientsJsonArray.get(j)).get("foodId").isJsonNull()) {
                ingredientDTOObj.setFoodId("");
            } else
                ingredientDTOObj.setFoodId(((JsonObject) ingredientsJsonArray.get(j)).get("foodId").getAsString());

            if (((JsonObject) ingredientsJsonArray.get(j)).get("text").isJsonNull()) {
                ingredientDTOObj.setText("");
            } else ingredientDTOObj.setText(((JsonObject) ingredientsJsonArray.get(j)).get("text").getAsString());

            ingredientDTOListObj.add(ingredientDTOObj);
        }

        JsonArray digestJsonArray = (JsonArray) recipeJson.get("digest");
        List<DigestDTO> digestDTOListObj = new ArrayList<>();
        for (int j = 0; j < digestJsonArray.size(); j++) {
            DigestDTO digestDTOObj = new DigestDTO();

            if (((JsonObject) digestJsonArray.get(j)).get(label1).isJsonNull()) {
                digestDTOObj.setLabel("");
            } else digestDTOObj.setLabel(((JsonObject) digestJsonArray.get(j)).get(label1).getAsString());

            if (((JsonObject) digestJsonArray.get(j)).get("tag").isJsonNull()) {
                digestDTOObj.setTag("");
            } else digestDTOObj.setTag(((JsonObject) digestJsonArray.get(j)).get("tag").getAsString());

            var schemaOrgTag = "schemaOrgTag";
            if (((JsonObject) digestJsonArray.get(j)).get(schemaOrgTag).isJsonNull()) {
                digestDTOObj.setSchemaOrgTag("");
            } else
                digestDTOObj.setSchemaOrgTag(((JsonObject) digestJsonArray.get(j)).get(schemaOrgTag).getAsString());

            var total = "total";
            if (((JsonObject) digestJsonArray.get(j)).get(total).isJsonNull()) {
                digestDTOObj.setTotal(0);
            } else digestDTOObj.setTotal(((JsonObject) digestJsonArray.get(j)).get(total).getAsDouble());

            var hasRDI = "hasRDI";
            if (((JsonObject) digestJsonArray.get(j)).get(hasRDI).isJsonNull()) {
                digestDTOObj.setHasRDI(false);
            } else digestDTOObj.setHasRDI(((JsonObject) digestJsonArray.get(j)).get(hasRDI).getAsBoolean());

            var daily = "daily";
            if (((JsonObject) digestJsonArray.get(j)).get(daily).isJsonNull()) {
                digestDTOObj.setDaily(0.0);
            } else digestDTOObj.setDaily(((JsonObject) digestJsonArray.get(j)).get(daily).getAsDouble());

            if (((JsonObject) digestJsonArray.get(j)).get("unit").isJsonNull()) {
                digestDTOObj.setUnit("");
            } else digestDTOObj.setUnit(((JsonObject) digestJsonArray.get(j)).get("unit").getAsString());


            if (j == 0 || j == 1) {

                JsonArray subArrayJson = (JsonArray) ((JsonObject) digestJsonArray.get(j)).get("sub");
                List<SubDTO> subDTOListObj = new ArrayList<>();
                for (int k = 0; k < subArrayJson.size(); k++) {
                    SubDTO subDTOObj = new SubDTO();
                    if (((JsonObject) subArrayJson.get(k)).get(label1).isJsonNull()) {
                        subDTOObj.setLabel("");
                    } else subDTOObj.setLabel(((JsonObject) subArrayJson.get(j)).get(label1).getAsString());

                    if (((JsonObject) subArrayJson.get(j)).get("tag").isJsonNull()) {
                        subDTOObj.setTag("");
                    } else subDTOObj.setTag(((JsonObject) subArrayJson.get(j)).get("tag").getAsString());

                    if (((JsonObject) subArrayJson.get(j)).get(schemaOrgTag).isJsonNull()) {
                        subDTOObj.setSchemaOrgTag("");
                    } else
                        subDTOObj.setSchemaOrgTag(((JsonObject) subArrayJson.get(j)).get(schemaOrgTag).getAsString());

                    if (((JsonObject) subArrayJson.get(j)).get(total).isJsonNull()) {
                        subDTOObj.setTotal(0);
                    } else subDTOObj.setTotal(((JsonObject) subArrayJson.get(j)).get(total).getAsDouble());

                    if (((JsonObject) subArrayJson.get(j)).get(hasRDI).isJsonNull()) {
                        subDTOObj.setHasRDI(false);
                    } else subDTOObj.setHasRDI(((JsonObject) subArrayJson.get(j)).get(hasRDI).getAsBoolean());

                    if (((JsonObject) subArrayJson.get(j)).get(daily).isJsonNull()) {
                        subDTOObj.setDaily(0.0);
                    } else subDTOObj.setDaily(((JsonObject) subArrayJson.get(j)).get(daily).getAsDouble());

                    if (((JsonObject) subArrayJson.get(j)).get("unit").isJsonNull()) {
                        subDTOObj.setUnit("");
                    } else subDTOObj.setUnit(((JsonObject) subArrayJson.get(j)).get("unit").getAsString());

                    subDTOListObj.add(subDTOObj);
                }
                digestDTOObj.setSubDTO(subDTOListObj);
            } else
                digestDTOObj.setSubDTO(null);

            digestDTOListObj.add(digestDTOObj);

        }
        recipeDTOObj = new RecipeDTO(label.getAsString(), image.getAsString(), url.getAsString(),
                yield1.getAsInt(), calories.getAsDouble(), totalWeight.getAsDouble(), cuisineTypeLabelsListObj, mealTypeListObj, dishTypeListObj, dietLabelsListObj, healthLabelsListObj, cautionsLabelsListObj, ingredientLinesListObj, ingredientDTOListObj,
                digestDTOListObj, href);

        return recipeDTOObj;

    }

    public static void setLists(JsonArray jsonArray, List<String> stringList) {
        for (int i = 0; i < jsonArray.size(); i++) {
            stringList.add((jsonArray.get(i)).getAsString());
        }


    }

}
