package com.example.demo1;

public class Food {
    private String foodName;
    private Float foodPrice;
    private String imgsrc;

    public Food(String foodName, Float foodPrice, String imgsrc) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.imgsrc = imgsrc;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public Float getFoodPrice() {
        return foodPrice;
    }
    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;

    }
    public String getImgsrc() {
        return imgsrc;
    }
    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}
