package com.example.localtoglobal.productRetro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MerchantProductDto implements Serializable {
    private String id;
    private Long merchantId;
    private Long price;
    private Long stock;
    private Double rating;
    private List<Double> sizes;
    private List<String> colours;
    private Long weight;
    private Date expiryDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Double> getSizes() {
        return sizes;
    }

    public void setSizes(List<Double> sizes) {
        this.sizes = sizes;
    }

    public List<String> getColours() {
        return colours;
    }

    public void setColours(List<String> colours) {
        this.colours = colours;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public MerchantProductDto() {
    }

    public MerchantProductDto(String id, Long merchantId, Long price, Long stock, Double rating, List<Double> sizes, List<String> colours, Long weight, Date expiryDate) {
        this.id = id;
        this.merchantId = merchantId;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
        this.sizes = sizes;
        this.colours = colours;
        this.weight = weight;
        this.expiryDate = expiryDate;
    }
}
