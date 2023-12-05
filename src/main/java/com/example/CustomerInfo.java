package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerInfo {
    private Integer customerId;
    private Integer total;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String date;
    
    public CustomerInfo(@JsonProperty("customerId") Integer customerId, @JsonProperty("total") Integer total, @JsonProperty("date") String date) {
        this.customerId = customerId;
        this.total = total;
        this.date = date;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
