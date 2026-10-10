package com.recipenest.model;
import  java.time.LocalDateTime;

public class Comment {
    private int commentId;
    private int userId;
    private int recipeId;
    private String commentText;
    private LocalDateTime createdAt;

public Comment() {
}

public Comment(int commentId, int userId, int recipeId, String commentText,
    LocalDateTime createdAt) {
        this.commentId = commentId;
        this.userId = userId;
        this.recipeId = recipeId;
        this.commentText = commentText;
        this.createdAt = createdAt;
    }

public int getCommentId() {
    return commentId;
}
public void setCommentId(int commentId) {
    this.commentId = commentId;
}
public int getUserId() {
    return userId;
}
public void setUserId(int userId) {
    this.userId = userId;
}
public int getRecipeId() {
    return recipeId;
}
public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
}
public String getCommentText() {
    return commentText;
}
public void setCommentText(String commentText) {
    this.commentText = commentText;
}
public LocalDateTime getCreatedAt() {
    return createdAt;
}
public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}

}
