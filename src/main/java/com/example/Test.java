package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import classes.api.JsonLoaderV2;

public class Test {

    public static void main(String[] args) {
        int customerId;
        JsonLoaderV2<CustomerInfo> customerDb = new JsonLoaderV2<>(
                "src/main/resources/com/example/data/purchase/Customer_info.json", CustomerInfo.class);
        // fetch the last customer id
        List<CustomerInfo> customerList = customerDb.getEntityList();
        
        Optional<CustomerInfo> option = customerDb.checkEntity("customerId", "1");
        
        customerId = customerList.stream()
                .mapToInt(CustomerInfo::getCustomerId)
                .max()
                .orElse(0);
        System.out.println(customerId);

        customerList.stream()
                .filter(customer -> customer.getCustomerId() == customerId)
                .collect(Collectors.toList())
                .forEach(customer -> {
                    System.out.println(customer);
                });
        
        customerList.stream().filter(customer -> customer.getCustomerId() == customerId).collect(Collectors.toList()).forEach(customer -> {
            System.out.println(customer);
        });
       
        customerDb.getEntityList().stream().filter(customer -> customer.getCustomerId() == customerId).collect(Collectors.toList()).forEach(customer -> {
            System.out.println(customer);
        });
        option.ifPresent(customer -> {
            System.out.println(customer.getCustomerId());
        });
        
/*         JsonLoaderV3<User> jsonDatabase = new JsonLoaderV3<>(
                "src/main/resources/com/example/data/db.json", User.class);

// Add a new entry to the "login" map
jsonDatabase.addMapEntry("login", "newUser", "newPassword");

// Update an existing entry in the "login" map
jsonDatabase.updateMapEntry("login", "admin", "newAdminPassword");

// Delete an entry from the "login" map
jsonDatabase.deleteMapEntry("login", "root");

// Fetch the "login" map
Map<String, Object> loginMap = jsonDatabase.fetchMap("login");

System.out.println(loginMap); */


    } 
}
