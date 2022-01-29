package com.example.localtoglobal.cartRetro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CartItemDto implements Serializable {
    private String name;
    private String productId;
    private Long userId;
    private Long merchantId;
    private Long price;
    private Date date;
    private Long quantity;
    public CartItemDto(){

    }

    public CartItemDto(String name, String productId, Long userId, Long merchantId, Long price, Date date, Long quantity) {
        this.name = name;
        this.productId = productId;
        this.userId = userId;
        this.merchantId = merchantId;
        this.price = price;
        this.date = date;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
