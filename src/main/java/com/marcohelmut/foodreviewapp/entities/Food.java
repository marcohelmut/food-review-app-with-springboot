package com.marcohelmut.foodreviewapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Food")
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Food name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be valid")
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull(message = "Food must have a stall")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stall_id", nullable = false)
    @JsonIgnore
    private Stall stall;

    @JsonIgnore
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    protected Food() {}

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setFood(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setFood(null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Stall getStall() {
        return stall;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
