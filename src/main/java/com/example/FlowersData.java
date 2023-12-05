package com.example;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class FlowersData {
    private Integer flowerId;

    private String flowerName;

    private String status;
    
    private Double price;
    
    private String image;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private String date;

    public FlowersData(@JsonProperty("flowerId") Integer flowerId,@JsonProperty("flowerName") String flowerName,@JsonProperty ("status") String status,@JsonProperty ("price") Double price, @JsonProperty("date") String date, @JsonProperty("image") String image) {
        this.flowerId = flowerId;
        this.flowerName = flowerName;
        this.status = status;
        this.price = price;
        this.date = date;
        this.image = image;
    }
   

    public Integer getFlowerId() {
        return this.flowerId;
    }

    public void setFlowerId(Integer flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerName() {
        return this.flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    // @Override
    // public String toString() {
    //     return "FlowersData{" +
    //             "flowerId=" + flowerId +
    //             ", flowerName='" + flowerName + '\'' +
    //             ", status='" + status + '\'' +
    //             ", price=" + price +
    //             '}';
    // }

}