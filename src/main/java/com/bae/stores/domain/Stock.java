package com.bae.stores.domain;

import javax.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column
    private Integer stockCount;

    public Stock() {}

    public Stock(String description, String category, Integer stockCount) {
        this.description = description;
        this.category = category;
        this.stockCount = stockCount;
    }

    public Stock(Integer id, String description, String category, Integer stockCount) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.stockCount = stockCount;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    @Override
    public String toString() {
        return "Stock: ID = " + id + ", description = " + description + ", category = " + category + ", stockCount = " + stockCount;
    }
}
