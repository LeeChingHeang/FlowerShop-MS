package com.example;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
public class FlowersData {
    @JsonProperty("flower_id")
    private Integer flowerId;

    @JsonProperty("flower_name")
    private String flowerName;

    @JsonProperty("status")
    private String status;
    
    @JsonProperty("price")
    private Double price;
    
    @JsonProperty("image")
    private String image;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    
    public FlowersData(Integer flowerId, String flowerName, String status, Double price, LocalDate date, String image) {
        this.flowerId = flowerId;
        this.flowerName = flowerName;
        this.status = status;
        this.price = price;
        this.date = date;
        this.image = image;
    }
    
    public Integer getFlowerId() {
        return flowerId;
    }


    public String getFlowerName() {
        return flowerName;
    }


    public String getStatus() {
        return status;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }
}