package com.example.demo.DataBase;

public class CartProducts extends Product{

    private int quantity;
    private double totalCost;

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {

        return totalCost;
    }

    public void setTotalCost(double totalCost) {

        this.totalCost = totalCost;
    }
}
