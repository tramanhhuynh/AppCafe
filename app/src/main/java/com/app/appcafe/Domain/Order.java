package com.app.appcafe.Domain;

import com.app.appcafe.Adapter.CartAdapter;

import java.util.List;

public class Order {
    private List<Drinks> items;
    private double totalFee;
    private double tax;
    private double delivery;
    private double total;
    public List<Drinks> getItems(){
        return items;
    }

    public void setItems(List<Drinks> items) {
        this.items = items;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
