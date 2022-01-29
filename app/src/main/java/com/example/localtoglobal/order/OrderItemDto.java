package com.example.localtoglobal.order;

import java.io.Serializable;

public class OrderItemDto implements Serializable {
    private String productId;
    private Long merchantId;
    private Long price;
    private Long quantity;
    private String name;

    public OrderItemDto(){

    }

    public OrderItemDto(String productId, Long merchantId, Long price, Long quantity, String name) {
        this.productId = productId;
        this.merchantId = merchantId;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
