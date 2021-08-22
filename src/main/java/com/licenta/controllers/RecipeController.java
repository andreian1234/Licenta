package com.licenta.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.licenta.models.*;
import com.licenta.services.Utils;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class RecipeController {

    public static void getRootDetailsObject() throws Exception {


        Root rootObj = new Root();
        Links linksObj;
        Next nextObj;
        Self selfObj;
        var response = Utils.getApi("https://api.edamam.com/api/recipes/v2?type=public&q=chicken&app_id=ec0fe243&app_key=f72bc6f17877b6601c111a6a3aef13b5");

        //Get root primitives from respose and put them in an object
        var from = (JsonPrimitive) response.get("from");
        rootObj.setFrom(from.getAsInt());

        var to = (JsonPrimitive) response.get("to");
        rootObj.setTo(to.getAsInt());

        var count = (JsonPrimitive) response.get("count");
        rootObj.setTo(count.getAsInt());

        JsonObject linksJsonObj = (JsonObject) response.get("_links");
        JsonObject nextJsonObj = (JsonObject) linksJsonObj.get("next");
        var href = (JsonPrimitive) nextJsonObj.get("href");
        var title = (JsonPrimitive) nextJsonObj.get("title");

        nextObj = new Next(href.getAsString(), title.getAsString());

        selfObj = new Self("", "");
        linksObj = new Links(nextObj, selfObj);
        rootObj.set_links(linksObj);


        //Get recipe array and put it in the root object//

        JsonArray recipesJsonArray = (JsonArray) response.get("hits");
        List<Hit> hitListObj = new ArrayList<>();
        for (int i = 0; i < recipesJsonArray.size(); i++) {
            Hit hitObj;
            Recipe recipeObj;
            Links links1Obj;
            Self self1Obj;
            Next next1Obj;


            //Get recipe link Obj//

            JsonObject links1JsonObj = (JsonObject) ((JsonObject) recipesJsonArray.get(i)).get("_links");
            JsonObject self1JsonObj = (JsonObject) links1JsonObj.get("self");

            next1Obj = new Next("", "");

            var href1 = (JsonPrimitive) self1JsonObj.get("href");
            var title1 = (JsonPrimitive) self1JsonObj.get("title");
            self1Obj = new Self(href1.getAsString(), title1.getAsString());

            links1Obj = new Links(next1Obj, self1Obj);


            JsonObject recipeJson = (JsonObject) ((JsonObject) recipesJsonArray.get(i)).get("recipe");

            //Get recipe primitives//

            var uri = (JsonPrimitive) recipeJson.get("uri");
            var label = (JsonPrimitive) recipeJson.get("label");
            var image = (JsonPrimitive) recipeJson.get("image");
            var source = (JsonPrimitive) recipeJson.get("source");
            var url = (JsonPrimitive) recipeJson.get("url");
            var shareAs = (JsonPrimitive) recipeJson.get("shareAs");
            var yield = (JsonPrimitive) recipeJson.get("yield");
            var calories = (JsonPrimitive) recipeJson.get("calories");
            var totalWeight = (JsonPrimitive) recipeJson.get("totalWeight");
            var totalTime = (JsonPrimitive) recipeJson.get("totalTime");

            //Get dietLabels ObjArrays//

            JsonArray dietLabelsJsonArray = (JsonArray) recipeJson.get("dietLabels");
            List<String> dietLabelsListObj = new ArrayList<>();
            setLists(dietLabelsJsonArray, dietLabelsListObj);

            JsonArray healthLabelsJsonArray = (JsonArray) recipeJson.get("healthLabels");
            List<String> healthLabelsListObj = new ArrayList<>();
            setLists(healthLabelsJsonArray, healthLabelsListObj);

            JsonArray cautionsLabelsJsonArray = (JsonArray) recipeJson.get("cautions");
            List<String> cautionsLabelsListObj = new ArrayList<>();
            setLists(cautionsLabelsJsonArray, cautionsLabelsListObj);

            JsonArray ingredientLinesLabelsJsonArray = (JsonArray) recipeJson.get("ingredientLines");
            List<String> ingredientLinesListObj = new ArrayList<>();
            setLists(ingredientLinesLabelsJsonArray, ingredientLinesListObj);

            JsonArray cuisineTypeJsonArray = (JsonArray) recipeJson.get("cuisineType");
            List<String> cuisineTypeLabelsListObj = new ArrayList<>();
            setLists(cuisineTypeJsonArray, cuisineTypeLabelsListObj);

            JsonArray mealTypeJsonArray = (JsonArray) recipeJson.get("mealType");
            List<String> mealTypeListObj = new ArrayList<>();
            setLists(mealTypeJsonArray, mealTypeListObj);

            JsonArray dishTypeJsonArray = (JsonArray) recipeJson.get("dishType");
            List<String> dishTypeListObj = new ArrayList<>();
            setLists(dishTypeJsonArray, dishTypeListObj);

            //Get ingridients Obj List//

            JsonArray ingredientsJsonArray = (JsonArray) recipeJson.get("ingredients");
            List<Ingredient> ingredientListObj = new ArrayList<>();
            for (int j = 0; j < ingredientsJsonArray.size(); j++) {


                Ingredient ingredient = new Ingredient();

                if(((JsonObject)ingredientsJsonArray.get(j)).get("image").isJsonNull()) {
                    ingredient.setImage("");
                }
                else ingredient.setImage(((JsonObject)ingredientsJsonArray.get(j)).get("image").getAsString());

                if(((JsonObject)ingredientsJsonArray.get(j)).get("weight").isJsonNull()) {
                    ingredient.setWeight(0.0);
                }
                else ingredient.setWeight(((JsonObject)ingredientsJsonArray.get(j)).get("weight").getAsDouble());

                if(((JsonObject)ingredientsJsonArray.get(j)).get("foodCategory").isJsonNull()) {
                    ingredient.setFoodCategory("");
                }
                else ingredient.setFoodCategory(((JsonObject)ingredientsJsonArray.get(j)).get("foodCategory").getAsString());

                if(((JsonObject)ingredientsJsonArray.get(j)).get("foodId").isJsonNull()) {
                    ingredient.setFoodId("");
                }
                else ingredient.setFoodId(((JsonObject)ingredientsJsonArray.get(j)).get("foodId").getAsString());

                if(((JsonObject)ingredientsJsonArray.get(j)).get("text").isJsonNull()) {
                    ingredient.setText("");
                }
                else ingredient.setText(((JsonObject)ingredientsJsonArray.get(j)).get("text").getAsString());

                ingredientListObj.add(ingredient);
            }

            JsonArray digestJsonArray = (JsonArray) recipeJson.get("digest");
            List<Digest> digestListObj = new ArrayList<>();
            for (int j = 0; j < digestJsonArray.size(); j++) {
                Digest digestObj = new Digest();

                if(((JsonObject)digestJsonArray.get(j)).get("label").isJsonNull()) {
                    digestObj.setLabel("");
                }
                else digestObj.setLabel(((JsonObject)digestJsonArray.get(j)).get("label").getAsString());

                if(((JsonObject)digestJsonArray.get(j)).get("tag").isJsonNull()) {
                    digestObj.setTag("");
                }
                else digestObj.setTag(((JsonObject)digestJsonArray.get(j)).get("tag").getAsString());

                if(((JsonObject)digestJsonArray.get(j)).get("schemaOrgTag").isJsonNull()) {
                    digestObj.setSchemaOrgTag("");
                }
                else digestObj.setSchemaOrgTag(((JsonObject)digestJsonArray.get(j)).get("schemaOrgTag").getAsString());

                if(((JsonObject)digestJsonArray.get(j)).get("total").isJsonNull()) {
                    digestObj.setTotal(0);
                }
                else digestObj.setTotal(((JsonObject)digestJsonArray.get(j)).get("total").getAsDouble());

                if(((JsonObject)digestJsonArray.get(j)).get("hasRDI").isJsonNull()) {
                    digestObj.setHasRDI(false);
                }
                else digestObj.setHasRDI(((JsonObject)digestJsonArray.get(j)).get("hasRDI").getAsBoolean());

                if(((JsonObject)digestJsonArray.get(j)).get("daily").isJsonNull()) {
                    digestObj.setDaily(0.0);
                }
                else digestObj.setDaily(((JsonObject)digestJsonArray.get(j)).get("daily").getAsDouble());

                if(((JsonObject)digestJsonArray.get(j)).get("unit").isJsonNull()) {
                    digestObj.setUnit("");
                }
                else digestObj.setUnit(((JsonObject)digestJsonArray.get(j)).get("unit").getAsString());


                if(j == 0 || j == 1) {

                    JsonArray subArrayJson = (JsonArray) ((JsonObject) digestJsonArray.get(j)).get("sub");
                    List<Sub> subListObj = new ArrayList<>();
                    for (int k = 0; k < subArrayJson.size(); k++){
                        Sub subObj = new Sub();
                        if(((JsonObject)subArrayJson.get(k)).get("label").isJsonNull()) {
                            subObj.setLabel("");
                        }
                        else subObj.setLabel(((JsonObject)subArrayJson.get(j)).get("label").getAsString());

                        if(((JsonObject)subArrayJson.get(j)).get("tag").isJsonNull()) {
                            subObj.setTag("");
                        }
                        else subObj.setTag(((JsonObject)subArrayJson.get(j)).get("tag").getAsString());

                        if(((JsonObject)subArrayJson.get(j)).get("schemaOrgTag").isJsonNull()) {
                            subObj.setSchemaOrgTag("");
                        }
                        else subObj.setSchemaOrgTag(((JsonObject)subArrayJson.get(j)).get("schemaOrgTag").getAsString());

                        if(((JsonObject)subArrayJson.get(j)).get("total").isJsonNull()) {
                            subObj.setTotal(0);
                        }
                        else subObj.setTotal(((JsonObject)subArrayJson.get(j)).get("total").getAsDouble());

                        if(((JsonObject)subArrayJson.get(j)).get("hasRDI").isJsonNull()) {
                            subObj.setHasRDI(false);
                        }
                        else subObj.setHasRDI(((JsonObject)subArrayJson.get(j)).get("hasRDI").getAsBoolean());

                        if(((JsonObject)subArrayJson.get(j)).get("daily").isJsonNull()) {
                            subObj.setDaily(0.0);
                        }
                        else subObj.setDaily(((JsonObject)subArrayJson.get(j)).get("daily").getAsDouble());

                        if(((JsonObject)subArrayJson.get(j)).get("unit").isJsonNull()) {
                            subObj.setUnit("");
                        }
                        else subObj.setUnit(((JsonObject)subArrayJson.get(j)).get("unit").getAsString());

                        subListObj.add(subObj);
                    }
                    digestObj.setSub(subListObj);
                }
                else
                    digestObj.setSub(null);

                digestListObj.add(digestObj);

            }
            recipeObj = new Recipe(uri.getAsString(), label.getAsString(), image.getAsString(), source.getAsString(), url.getAsString(), shareAs.getAsString(),
                    yield.getAsInt(), dietLabelsListObj, healthLabelsListObj, cautionsLabelsListObj, ingredientLinesListObj, ingredientListObj, calories.getAsDouble(),
                    totalWeight.getAsDouble(), totalTime.getAsInt(), cuisineTypeLabelsListObj,mealTypeListObj, dishTypeListObj, digestListObj);

            hitObj = new Hit(recipeObj,links1Obj);
            hitListObj.add(hitObj);
        }
        rootObj.setHits(hitListObj);

    }


    public static void setLists(JsonArray jsonArray, List<String> stringList) {
        for (int i = 0; i < jsonArray.size(); i++) {
            stringList.add(((JsonPrimitive) jsonArray.get(i)).getAsString());
        }


    }


    @SneakyThrows
    public static void main(String[] args) {
        getRootDetailsObject();

    }
}
