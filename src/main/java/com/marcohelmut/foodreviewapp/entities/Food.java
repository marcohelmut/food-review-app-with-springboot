package com.marcohelmut.foodreviewapp.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Food")
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "photo_file_path")
    private String photoFilePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stall_id", nullable = false)
    private Stall stall;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Food() {}

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

    public String getPhotoFilePath() {
        return photoFilePath;
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

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
