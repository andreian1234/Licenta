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

    private static RootFoodDTO getFoodRootDetailsObject(JsonObject jsonObject) throws Exception {
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
            NutrientsDTO nutrientsDTOObj;
            JsonObject nutrientsJson = (JsonObject) foodJson.get("nutrients");
            var enercKcal = (JsonPrimitive) nutrientsJson.get("ENERC_KCAL");
            var procnt = (JsonPrimitive) nutrientsJson.get("PROCNT");
            var fat = (JsonPrimitive) nutrientsJson.get("FAT");
            var chocdf = (JsonPrimitive) nutrientsJson.get("CHOCDF");
            var fibtg = (JsonPrimitive) nutrientsJson.get("FIBTG");
            nutrientsDTOObj = new NutrientsDTO(enercKcal.getAsDouble(), procnt.getAsDouble(), fat.getAsDouble(), chocdf.getAsDouble(), fibtg.getAsDouble());

            foodDTOObj.setNutrientsDTO(nutrientsDTOObj);

            //Get measueres ObjArray

            JsonArray measuresJsonArray = (JsonArray) ((JsonObject) hintsJsonArray.get(i)).get("measures");
            MeasureDTO measuresObj = new MeasureDTO();
            for (int j = 0; j < measuresJsonArray.size(); j++) {
                MeasureDTO measureDTOObj = new MeasureDTO();

                if(((JsonObject) measuresJsonArray.get(j)).get("label").getAsString().equals("Serving"))
                {
                    measuresObj.setLabel(((JsonObject) measuresJsonArray.get(j)).get("label").getAsString());
                    measuresObj.setWeight(((JsonObject) measuresJsonArray.get(j)).get("weight").getAsDouble());

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
