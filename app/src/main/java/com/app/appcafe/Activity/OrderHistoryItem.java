package com.app.appcafe.Activity;

public class OrderHistoryItem {
    private String orderId;
    private String orderDate;

    public OrderHistoryItem(String orderId, String orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public
    String getOrderDate() {
        return orderDate;
    }
}
