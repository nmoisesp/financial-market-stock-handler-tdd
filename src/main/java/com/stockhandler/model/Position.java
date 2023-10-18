package com.stockhandler.model;

public class Position {

    private Stock stock;
    private Integer quantity;
    private Double price;

    public Position(Stock stock, Integer quantity, Double price){
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
    }

    public Stock getStock() {
        return stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getAveragePrice() {
        return price;
    }

    public Double getValue(){
        return quantity * price;
    }

    public void setQuantity(Integer newQuantity) {
        this.quantity = newQuantity;
    }

    public void setAveragePrice(Double newAveragePrice) {
        this.price = newAveragePrice;
    }

    public String toString(){
        return String.format("{ %s | Qty: %s | Px: %s | Value: %s }", stock, quantity, price, getValue());
    }
}
