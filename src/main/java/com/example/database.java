package com.example;
import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class database {
    public static void main(String[] args) {
        // working with json files
       try { 
            ObjectMapper objectMapper = new ObjectMapper();
            // writing to json file
            // User user = new User(1, "admin", "admin");
            // objectMapper.writeValue(new File("src/main/resources/com/example/user.json"), user);
            // String json = "{\"id\":1,\"username\":\"admin\",\"password\":\"admin\"}";
            // JsonNode jsonNode = objectMapper.readTree(json);
       } catch (final Exception e) {
           e.printStackTrace();
       }

    }
}
