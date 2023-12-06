package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerInfo {
    private Integer customerId;
    private Double total;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String date;
    
    public CustomerInfo(@JsonProperty("customerId") Integer customerId, @JsonProperty("total") Double total, @JsonProperty("date") String date) {
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

    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
