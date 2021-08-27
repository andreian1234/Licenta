package com.licenta.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.licenta.models.*;
import com.licenta.services.Utils;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodItemRecipe {

    public static void getFoodRootDetailsObject() throws Exception {
        RootFood rootFoodObj;
        Links linksObj;
        Next nextObj;
        Self selfObj;


        var response = Utils.getApi("https://api.edamam.com/api/food-database/v2/parser?app_id=0e606b1e&app_key=78de0ba1870a61e88db02d2c6d158d4d&ingr=chicken&nutrition-type=logging");

        //Get root primitives from respose and put them in an object

        var from = (JsonPrimitive) response.get("from");
        var to = (JsonPrimitive) response.get("to");
        var count = (JsonPrimitive) response.get("count");

        JsonObject linksJsonObj = (JsonObject) response.get("_links");
        JsonObject nextJsonObj = (JsonObject) linksJsonObj.get("next");

        var href = (JsonPrimitive) nextJsonObj.get("href");
        var title = (JsonPrimitive) nextJsonObj.get("title");

        nextObj = new Next(href.getAsString(), title.getAsString());

        selfObj = new Self("", "");
        linksObj = new Links(nextObj, selfObj);

        //Get food array and put it in the root object//

        JsonArray foodsJsonArray = (JsonArray) response.get("hints");
        List<Hint> hintListObj = new ArrayList<>();
        for (int i = 0; i < foodsJsonArray.size(); i++) {
            Hint hintObj;
            Food foodObj = new Food();
            Measure measureObj;

            //Get food primitives

            JsonObject foodJson = (JsonObject) ((JsonObject) foodsJsonArray.get(i)).get("food");

            var foodId = (JsonPrimitive) foodJson.get("foodId");
            var label = (JsonPrimitive) foodJson.get("label");
            var category = (JsonPrimitive) foodJson.get("category");


            if (((JsonObject) foodsJsonArray.get(i)).get("image").isJsonNull()) {
                foodObj.setImage("");
            } else foodObj.setImage(((JsonObject) foodsJsonArray.get(i)).get("image").getAsString());

            if (((JsonObject) foodsJsonArray.get(i)).get("servingsPerContainer").isJsonNull()) {
                foodObj.setServingsPerContainer(0);
            } else foodObj.setServingsPerContainer(((JsonObject) foodsJsonArray.get(i)).get("servingsPerContainer").getAsDouble());

            if (((JsonObject) foodsJsonArray.get(i)).get("foodContentsLabel").isJsonNull()) {
                foodObj.setFoodContentsLabel("");
            } else foodObj.setFoodContentsLabel(((JsonObject) foodsJsonArray.get(i)).get("foodContentsLabel").getAsString());
            if (((JsonObject) foodsJsonArray.get(i)).get("categoryLabel").isJsonNull()) {
                foodObj.setCategoryLabel("");
            } else foodObj.setCategoryLabel(((JsonObject) foodsJsonArray.get(i)).get("categoryLabel").getAsString());

            JsonArray servingSizes= (JsonArray) ((JsonObject) foodsJsonArray.get(i)).get("");
            String author = null;
            if(authors.length() > 0) {
                author = authors.getString(authors.length() - 1);
            } else {
                author="unknown";
            }
        }
    }
}
