package com.stockhandler.model;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {

    private final Map<String, Position> positions;

    public Portfolio(){
        positions = new HashMap<>();
    }

    public Map<String, Position> getAllPositions(){
        return positions;
    }

    public void add(Position position) {
        String symbol = position.getStock().symbol();

        if(positions.containsKey(symbol)) {
            Position existingPosition = positions.get(symbol);
            Integer newQuantity = existingPosition.getQuantity() + position.getQuantity();

            Double newAveragePrice = ((existingPosition.getQuantity() * existingPosition.getAveragePrice()) + (position.getQuantity() * position.getAveragePrice()))/ newQuantity;

            existingPosition.setQuantity(newQuantity);
            existingPosition.setAveragePrice(newAveragePrice);

        } else {
            positions.put(symbol, position);
        }
    }

    public Position getPosition(String symbol) {
        return positions.get(symbol);
    }

    public Double getTotalValue() {
        return positions.values().stream()
                .mapToDouble(Position::getValue)
                .sum();
    }

    public void print(){
        positions.values().forEach(System.out::println);
        System.out.println("-------------------------");
        System.out.println(getTotalValue());
    }
}