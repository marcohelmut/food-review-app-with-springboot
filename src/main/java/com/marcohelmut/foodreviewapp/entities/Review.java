package com.marcohelmut.foodreviewapp.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_number", nullable = false)
    private Integer studentNumber;

    @Column(name = "price_score", nullable = false)
    private Integer priceScore;

    @Column(name = "taste_score", nullable = false)
    private Integer tasteScore;

    @Column(name = "cleanliness_score", nullable = false)
    private Integer cleanlinessScore;

    @Column(name = "comment", length = 100, nullable = true)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public Review() {}

    public Review(Integer studentNumber, Integer priceScore, Integer tasteScore, Integer cleanlinessScore, String comment) {
        this.studentNumber = studentNumber;
        this.priceScore = priceScore;
        this.tasteScore = tasteScore;
        this.cleanlinessScore = cleanlinessScore;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public Integer getPriceScore() {
        return priceScore;
    }

    public Integer getTasteScore() {
        return tasteScore;
    }

    public Integer getCleanlinessScore() {
        return cleanlinessScore;
    }

    public String getComment() {
        return comment;
    }

    public Food getFood() {
        return food;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setPriceScore(Integer priceScore) {
        this.priceScore = priceScore;
    }

    public void setTasteScore(Integer tasteScore) {
        this.tasteScore = tasteScore;
    }

    public void setCleanlinessScore(Integer cleanlinessScore) {
        this.cleanlinessScore = cleanlinessScore;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
