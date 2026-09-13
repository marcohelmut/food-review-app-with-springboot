package com.marcohelmut.foodreviewapp.entities;

import jakarta.persistence.*;

@Entity(name = "Review")
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    protected Review() {}

    public Review(Integer priceScore, Integer tasteScore, Integer cleanlinessScore, String comment) {
        this.priceScore = priceScore;
        this.tasteScore = tasteScore;
        this.cleanlinessScore = cleanlinessScore;
        this.comment = comment;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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

}
