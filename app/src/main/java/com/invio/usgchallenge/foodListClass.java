package com.bonappetit.usgchallenge;

public class foodListClass {
    String mealName,  mealUrl,  mealId;

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealUrl() {
        return mealUrl;
    }

    public void setMealUrl(String mealUrl) {
        this.mealUrl = mealUrl;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }



    foodListClass(String mealName, String mealUrl, String  mealId){
        this.mealId=mealId;
        this.mealName=mealName;
        this.mealUrl=mealUrl;
    }
}
