package com.recipenest.model;
import java.time.LocalDateTime;

public class Recipe {
    
    private int recipeId;
    private int userId;
    private String title;
    private String description; 
    private String ingredients;
    private String instructions;
    private String category;
    private LocalDateTime createdAt;

    public Recipe() {
    }
    
    public Recipe(int recipeId, int userId, String title, String description,
     String ingredients, String instructions, String category, LocalDateTime createdAt) {
        this.recipeId = recipeId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.category = category;
        this.createdAt = createdAt;
     }

     public int getRecipeId() {
        return recipeId;
     }
     public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
     }
     public int getUserId() {
        return userId;
     }
     public void setUserId(int userId) {
        this.userId = userId;
     }
     public String getTitle() {
        return title;
     }
     public void setTitle(String title) {
        this.title = title;
     }
     public String getDescription() {
        return description;
     }
     public void setDescription(String description) {
        this.description = description;
     }
     public String getIngredients() {
        return ingredients;
     }
     public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
     }
     public String getInstructions() {
        return instructions;
     }
     public void setInstructions(String instructions) {
        this.instructions = instructions;
     }
     public String getCategory() {
        return category;
     } 
     public  void setCategory(String category) {
        this.category = category;
     }
     public LocalDateTime getCreatedAt() {
        return createdAt;
     }
     public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
     }
    }
