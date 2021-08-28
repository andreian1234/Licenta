package com.licenta.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.licenta.models.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodApiService {

    private static RootFood getFoodRootDetailsObject(JsonObject jsonObject) throws Exception {
        RootFood rootFoodObj;
        Links linksObj;
        Next nextObj;
        Self selfObj;

        String label1 = "label";

        var response = jsonObject;

        //Get root primitives from respose and put them in an object


        var text = (JsonPrimitive) response.get("text");
        var links = "_links";
        JsonObject linksJsonObj = (JsonObject) response.get(links);
        JsonObject nextJsonObj = (JsonObject) linksJsonObj.get("next");

        var href = (JsonPrimitive) nextJsonObj.get("href");
        var title = (JsonPrimitive) nextJsonObj.get("title");

        nextObj = new Next(href.getAsString(), title.getAsString());

        selfObj = new Self("", "");

        linksObj = new Links(nextObj, selfObj);

        //Get food array and put it in the root object//

        JsonArray hintsJsonArray = (JsonArray) response.get("hints");
        List<Hint> hintListObj = new ArrayList<>();
        for (int i = 0; i < hintsJsonArray.size(); i++) {
            Hint hintObj;
            Food foodObj = new Food();

            //Get food primitives

            JsonObject foodJson = (JsonObject) ((JsonObject) hintsJsonArray.get(i)).get("food");

            var foodId = (JsonPrimitive) foodJson.get("foodId");
            foodObj.setFoodId(foodId.getAsString());
            var label = (JsonPrimitive) foodJson.get(label1);
            foodObj.setLabel(label.getAsString());
            var category = (JsonPrimitive) foodJson.get("category");
            foodObj.setCategory(category.getAsString());


            foodObj.setImage(!foodJson.has("image") ? "" : ((JsonPrimitive) foodJson.get("image")).getAsString());

            foodObj.setServingsPerContainer(!foodJson.has("servingsPerContainer") ? 0 : ((JsonPrimitive) foodJson.get("servingsPerContainer")).getAsDouble());

            foodObj.setFoodContentsLabel(!foodJson.has("setFoodContentsLabel") ? "" : ((JsonPrimitive) foodJson.get("setFoodContentsLabel")).getAsString());

            foodObj.setCategoryLabel(!foodJson.has("categoryLabel") ? "" : ((JsonPrimitive) foodJson.get("categoryLabel")).getAsString());


            //Get nutrients Obj
            Nutrients nutrientsObj;
            JsonObject nutrientsJson = (JsonObject) foodJson.get("nutrients");
            var enercKcal = (JsonPrimitive) nutrientsJson.get("ENERC_KCAL");
            var procnt = (JsonPrimitive) nutrientsJson.get("PROCNT");
            var fat = (JsonPrimitive) nutrientsJson.get("FAT");
            var chocdf = (JsonPrimitive) nutrientsJson.get("CHOCDF");
            var fibtg = (JsonPrimitive) nutrientsJson.get("FIBTG");
            nutrientsObj = new Nutrients(enercKcal.getAsDouble(), procnt.getAsDouble(), fat.getAsDouble(), chocdf.getAsDouble(), fibtg.getAsDouble());

            foodObj.setNutrients(nutrientsObj);

            //Get servingSizes ObjArray
            if (((JsonObject) hintsJsonArray.get(i)).has("servingSizes")) {
                JsonArray servingSizesJsonArray = (JsonArray) foodJson.get("servingSizes");
                List<ServingSize> servingSizeListObj = new ArrayList<>();
                for (int j = 0; j < servingSizesJsonArray.size(); j++) {
                    ServingSize servingSizeObj = new ServingSize();

                    servingSizeObj.setLabel(((JsonObject) servingSizesJsonArray.get(j)).get(label1).isJsonNull() ? "" : ((JsonObject) servingSizesJsonArray.get(j)).get(label1).getAsString());

                    if (((JsonObject) servingSizesJsonArray.get(j)).get("quantity").isJsonNull()) {
                        servingSizeObj.setQuantity(0);
                    } else
                        servingSizeObj.setQuantity(((JsonObject) servingSizesJsonArray.get(j)).get("quantity").getAsDouble());

                    servingSizeListObj.add(servingSizeObj);

                }
                foodObj.setServingSizes(servingSizeListObj);
            } else {
                List<ServingSize> servingSizeListObj = new ArrayList<>();
                foodObj.setServingSizes(servingSizeListObj);
            }


            //Get measueres ObjArray

            JsonArray measuresJsonArray = (JsonArray) ((JsonObject) hintsJsonArray.get(i)).get("measures");
            List<Measure> measuresListObj = new ArrayList<>();
            for (int j = 0; j < measuresJsonArray.size(); j++) {
                Measure measureObj = new Measure();

                if (!((JsonObject) measuresJsonArray.get(j)).has(label1)) {
                    measureObj.setLabel("");
                } else measureObj.setLabel(((JsonObject) measuresJsonArray.get(j)).get(label1).getAsString());

                if (!((JsonObject) measuresJsonArray.get(j)).has("weight")) {
                    measureObj.setWeight(0);
                } else measureObj.setWeight(((JsonObject) measuresJsonArray.get(j)).get("weight").getAsDouble());

                measuresListObj.add(measureObj);

            }


            hintObj = new Hint(foodObj, measuresListObj);
            hintListObj.add(hintObj);
        }
        rootFoodObj = new RootFood(text.getAsString(), hintListObj, linksObj);
        return rootFoodObj;
    }
}
