package com.projects.katat;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class JSONFile {
       public static JSONArray readArray(String fileName) {
        JSONArray data = null;
        try (FileReader reader = new FileReader(fileName)) {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            data = (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
