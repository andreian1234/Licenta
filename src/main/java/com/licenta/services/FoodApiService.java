package com.licenta.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.licenta.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodApiService {

    public static RootFoodDTO getFoodRootDetailsObject(JsonObject jsonObject) throws Exception {
        RootFoodDTO rootFoodDTOObj;
        LinksDTO linksDTOObj;
        NextDTO nextDTOObj;
        SelfDTO selfDTOObj;

        String label1 = "label";

        var response = jsonObject;

        //Get root primitives from respose and put them in an object


        var text = (JsonPrimitive) response.get("text");
        var links = "_links";
        JsonObject linksJsonObj = (JsonObject) response.get(links);
        JsonObject nextJsonObj = (JsonObject) linksJsonObj.get("next");

        var href = (JsonPrimitive) nextJsonObj.get("href");
        var title = (JsonPrimitive) nextJsonObj.get("title");

        nextDTOObj = new NextDTO(href.getAsString(), title.getAsString());

        selfDTOObj = new SelfDTO("", "");

        linksDTOObj = new LinksDTO(nextDTOObj, selfDTOObj);

        //Get food array and put it in the root object//

        JsonArray hintsJsonArray = (JsonArray) response.get("hints");
        List<HintDTO> hintDTOListObj = new ArrayList<>();
        for (int i = 0; i < hintsJsonArray.size(); i++) {
            HintDTO hintDTOObj;
            FoodDTO foodDTOObj = new FoodDTO();

            //Get food primitives

            JsonObject foodJson = (JsonObject) ((JsonObject) hintsJsonArray.get(i)).get("food");

            var foodId = (JsonPrimitive) foodJson.get("foodId");
            foodDTOObj.setFoodId(foodId.getAsString());
            var label = (JsonPrimitive) foodJson.get(label1);
            foodDTOObj.setLabel(label.getAsString());
            var category = (JsonPrimitive) foodJson.get("category");
            foodDTOObj.setCategory(category.getAsString());


            foodDTOObj.setImage(!foodJson.has("image") ? "" : ((JsonPrimitive) foodJson.get("image")).getAsString());

            foodDTOObj.setCategoryLabel(!foodJson.has("categoryLabel") ? "" : ((JsonPrimitive) foodJson.get("categoryLabel")).getAsString());


            //Get nutrients Obj
            NutrientsDTO nutrientsDTOObj = new NutrientsDTO();
            JsonObject nutrientsJson = (JsonObject) foodJson.get("nutrients");
            nutrientsDTOObj.setEnercKcal(!nutrientsJson.has("ENERC_KCAL") ? 0 : ((JsonPrimitive) nutrientsJson.get("ENERC_KCAL")).getAsDouble());
            nutrientsDTOObj.setProtein(!nutrientsJson.has("PROCNT") ? 0 : ((JsonPrimitive) nutrientsJson.get("PROCNT")).getAsDouble());
            nutrientsDTOObj.setFat(!nutrientsJson.has("FAT") ? 0 : ((JsonPrimitive) nutrientsJson.get("FAT")).getAsDouble());
            nutrientsDTOObj.setCarbs(!nutrientsJson.has("CHOCDF") ? 0 : ((JsonPrimitive) nutrientsJson.get("CHOCDF")).getAsDouble());
            nutrientsDTOObj.setFiber(!nutrientsJson.has("FIBTG") ? 0 : ((JsonPrimitive) nutrientsJson.get("FIBTG")).getAsDouble());


            foodDTOObj.setNutrientsDTO(nutrientsDTOObj);

            //Get measueres ObjArray

            JsonArray measuresJsonArray = (JsonArray) ((JsonObject) hintsJsonArray.get(i)).get("measures");
            MeasureDTO measuresObj = new MeasureDTO();
            for (int j = 0; j < measuresJsonArray.size(); j++) {
                MeasureDTO measureDTOObj = new MeasureDTO();
                if (((JsonObject) measuresJsonArray.get(j)).has("label")) {
                    if (((JsonObject) measuresJsonArray.get(j)).get("label").getAsString().equals("Serving")) {
                        measuresObj.setLabel(((JsonObject) measuresJsonArray.get(j)).get("label").getAsString());
                        measuresObj.setWeight(((JsonObject) measuresJsonArray.get(j)).get("weight").getAsDouble());
                    }
                } else {
                    measureDTOObj.setLabel("");
                    measureDTOObj.setWeight(0);
                }


            }
            hintDTOObj = new HintDTO(foodDTOObj, measuresObj);
            foodDTOObj.setMeasureDTO(measuresObj);
            hintDTOListObj.add(hintDTOObj);
        }

        rootFoodDTOObj = new RootFoodDTO(text.getAsString(), hintDTOListObj, linksDTOObj);
        return rootFoodDTOObj;

    }
}
