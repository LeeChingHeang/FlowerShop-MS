package com.jsonserver;
import java.io.File;
import java.util.List;

import com.example.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Database {
    public static void main(String[] args) {
        // working with json files
       try { 
            ObjectMapper objectMapper = new ObjectMapper();
            // locate json file
            File adminData = new File("src/main/resources/com/example/data/login/admin.json");
            // writing to json file
            // User user = new User(1, "admin", "admin");
            // objectMapper.writeValue(new File("src/main/resources/com/example/user.json"), user);
            
            // converting json to java object
            // String json = "{\"id\":1,\"username\":\"admin\",\"password\":\"admin\"}";
            // JsonNode jsonNode = objectMapper.readTree(user);
            // int id = jsonNode.get("id").asInt();
            // String username = jsonNode.get("username").asText();
            // String password = jsonNode.get("password").asText();
            // objectMapper.writeValue(new File("src/main/resources/com/example/data/login/user.txt"), id + "," + username + "," + password);
            
            // reading from json file
            // User user = objectMapper.readValue(new File("src/main/resources/com/example/data/login/user.json"), User.class);
            // objectMapper.writeValue(new File("src/main/resources/com/example/data/login/user.txt"), user.getId() + "," + user.getUsername() + "," + user.getPassword()); 
            
            // admin login
            List<User> userList = objectMapper.readValue(adminData, new TypeReference<List<User>>() {}); 
            for(User user : userList) {
                System.out.println(user.getId() + "," + user.getUsername() + "," + user.getPassword());
            }
       } catch (final Exception e) {
           e.printStackTrace();
       }

    }
}
