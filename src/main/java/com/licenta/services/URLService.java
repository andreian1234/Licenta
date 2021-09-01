package com.licenta.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@UtilityClass
public class URLService {


    public static JsonObject getApi(String urlToRead) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        return (JsonObject) JsonParser.parseString(result.toString());

    }

    public static String getRecipeUrl(String ingredient) {
        String apiDomain = "https://api.edamam.com/api/recipes/v2?type=public&q=";
        String apiKeys = "&app_id=ec0fe243&app_key=f72bc6f17877b6601c111a6a3aef13b5";
        return apiDomain + ingredient + apiKeys;
    }

    private static String getFoodUrl(String ingredient) {
        String apiDomainKeys = "https://api.edamam.com/api/food-database/v2/parser?app_id=0e606b1e&app_key=78de0ba1870a61e88db02d2c6d158d4d&ingr=";
        String logging = "&nutrition-type=logging";
        return apiDomainKeys + ingredient + logging;
    }


}