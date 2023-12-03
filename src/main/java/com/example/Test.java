package com.example;

import java.util.List;

import classes.api.GetUserData;
import classes.api.JsonDatabaseV2;

public class Test {
    public static void main(String[] args) {
        // Your code here
        List<User> userList = new GetUserData().getUserList();
        System.out.println(userList);
        
        List<User> userList2 = new JsonDatabaseV2<User>("src/main/resources/com/example/data/login/admin.json", User.class).getEntityList();
        System.out.println(userList2);
    }
}
