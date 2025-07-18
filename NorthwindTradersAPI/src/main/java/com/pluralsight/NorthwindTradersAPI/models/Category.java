package com.pluralsight.NorthwindTradersAPI.models;

public class Category {
    private int categoryID;
    private String categoryName;
    private String description;

    public Category() {
    }

    public Category(int categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ",categoryName='" + categoryName + '\'' +
                ", Description=" + description + '}';
    }
}
