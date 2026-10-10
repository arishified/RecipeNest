package com.recipenest.model;

public class Rating {
    private int ratingId;
    private int userId;
    private int recipeId;
    private int rating;

    public Rating() {
    }

    public Rating(int ratingId, int userId, int recipeId, int rating) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.recipeId = recipeId;
        this.rating =rating;
    }

    public int getRatingId() {
        return ratingId;
    }
    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserid(int userId) {
        this.userId = userId;
    }
    public int getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}
