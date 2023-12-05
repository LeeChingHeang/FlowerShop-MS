package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import classes.api.JsonDatabaseV2;

public class Test {
    private static final String Optional = null;

    public static void main(String[] args) {
        int customerId;
        JsonDatabaseV2<CustomerInfo> customerDb = new JsonDatabaseV2<>(
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
            System.out.println(customer);
        });
        

    } 
}
