package com.licenta.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Testare {

    @SneakyThrows
    public static void main(String[] args) {


      /*  String url = "https://api.edamam.com/api/recipes/v2?type=public&q=chicken&app_id=ec0fe243&app_key=f72bc6f17877b6601c111a6a3aef13b5";
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        int responseCode = connection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonObject = new JSONObject(response.toString());

        System.out.println(jsonObject.getJSONObject("_links").getJSONObject("next").getString("href"));

    */
    }
}
