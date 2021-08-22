package com.licenta.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public static JsonObject getApi(String urlToRead) throws Exception{
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while(( line = rd.readLine()) != null){
            result.append(line);
        }
        rd.close();

        JsonObject jsonObject = (JsonObject) JsonParser.parseString(result.toString());
        return jsonObject;
    }

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(getApi("https://api.edamam.com/api/recipes/v2?type=public&q=chicken&app_id=ec0fe243&app_key=f72bc6f17877b6601c111a6a3aef13b5"));
    }
}
