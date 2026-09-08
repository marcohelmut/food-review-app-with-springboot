package com.marcohelmut.foodreviewapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stalls")
public class Stall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Stall name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "stall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> foods = new ArrayList<>();

    public Stall() {}

    public Stall(String name) {
        this.name = name;
    }

    public void addFood(Food food) {
        foods.add(food);
        food.setStall(this);
    }

    public void removeFood(Food food) {
        foods.remove(food);
        food.setStall(null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
