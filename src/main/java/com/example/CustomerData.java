package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerData {

  private Integer customerId;

  private Integer flowerId;

  private String flowerName;

  private Integer quantity;

  private Double price;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private String date;

  // Constructor
  // add annotation @JsonProperty to map the json property to the java class so java know which property to map to which json property else will return 0 or no data loaded
  // cuz I call .objectMapper.getTypeFactory().constructCollectionType(List.class, elementType)) to get the data from the json file
  // which mean I need to map the json property to the java class
  public CustomerData(
    @JsonProperty("customerId") Integer customerId,
    @JsonProperty("flowerId") Integer flowerId,
    @JsonProperty("flowerName") String flowerName,
    @JsonProperty("quantity") Integer quantity,
    @JsonProperty("price") Double price,
    @JsonProperty("date") String date
  ) {
    this.customerId = customerId;
    this.flowerId = flowerId;
    this.flowerName = flowerName;
    this.quantity = quantity;
    this.price = price;
    this.date = date;
  }

  public Integer getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
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

  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
