package com.example.localtoglobal.productRetro;

import java.io.Serializable;
import java.util.List;

public class ProductDto implements Serializable {

    private String id;
    private String name;
    private boolean allowed;
    private String description;
    private String category;
    private List<MerchantProductDto> merchantProducts;

    public List<MerchantProductDto> getMerchantProducts() {
        return merchantProducts;
    }

    public void setMerchantProducts(List<MerchantProductDto> merchantProducts) {
        this.merchantProducts = merchantProducts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
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

    public ProductDto(String id, String name, boolean allowed, String description, String category, List<MerchantProductDto> merchantProduct) {
        this.id = id;
        this.name = name;
        this.allowed = allowed;
        this.description = description;
        this.category = category;
        this.merchantProducts = merchantProduct;
    }

    public ProductDto() {
    }
}
