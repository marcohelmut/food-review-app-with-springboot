package com.marcohelmut.foodreviewapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price_score", nullable = false)
    private int priceScore;

    @Column(name = "taste_score", nullable = false)
    private int tasteScore;

    @Column(name = "cleanliness_score", nullable = false)
    private int cleanlinessScore;

    @Column(name = "comment", length = 100, nullable = true)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    @JsonIgnore
    private Food food;

    public Review() {}

    public Review(int priceScore, int tasteScore, int cleanlinessScore, String comment) {
        this.priceScore = priceScore;
        this.tasteScore = tasteScore;
        this.cleanlinessScore = cleanlinessScore;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public int getPriceScore() {
        return priceScore;
    }

    public int getTasteScore() {
        return tasteScore;
    }

    public int getCleanlinessScore() {
        return cleanlinessScore;
    }

    public String getComment() {
        return comment;
    }

    public Food getFood() {
        return food;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPriceScore(int priceScore) {
        this.priceScore = priceScore;
    }

    public void setTasteScore(int tasteScore) {
        this.tasteScore = tasteScore;
    }

    public void setCleanlinessScore(int cleanlinessScore) {
        this.cleanlinessScore = cleanlinessScore;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
