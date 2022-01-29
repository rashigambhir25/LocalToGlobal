package com.example.localtoglobal.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderDto implements Serializable {
    private String id;
    private Long total;
    private Date date;
    private Long userId;
    private List<OrderItemDto> orderItems;

    public OrderDto(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderDto(String id, Long total, Date date, Long userId, List<OrderItemDto> orderItems) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.userId = userId;
        this.orderItems = orderItems;
    }
}
