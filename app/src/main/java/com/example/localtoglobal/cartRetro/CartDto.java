package com.example.localtoglobal.cartRetro;

import java.util.List;

public class CartDto {
    private Long userId;
    private List<CartItemDto> cartItems;
    private Long total ;

    public CartDto(){
    }

    public CartDto(Long userId, List<CartItemDto> cartItems, Long total) {
        this.userId = userId;
        this.cartItems = cartItems;
        this.total = total;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
