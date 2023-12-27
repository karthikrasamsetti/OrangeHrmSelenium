package org.example.base;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class JsonConverter {
    JsonObject jsonObject;
    public JsonObject getJson(String filePath){
        try {
            FileReader fileReader =new FileReader(System.getProperty("user.dir")+filePath);
            jsonObject= JsonParser.parseReader(fileReader).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
