package com.example;

import java.util.List;
import java.util.stream.Collectors;

import classes.api.JsonDatabaseV2;

public class Test {
    public static void main(String[] args) {
        int customerId;
        JsonDatabaseV2<CustomerInfo> customerDb = new JsonDatabaseV2<>(
                "src/main/resources/com/example/data/purchase/Customer_info.json", CustomerInfo.class);
        // fetch the last customer id
        List<CustomerInfo> customerList = customerDb.getEntityList();

        System.out.println("Customer List: " + customerList.size());
    } 
}
