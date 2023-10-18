package com.stockhandler;

import com.stockhandler.model.Portfolio;
import com.stockhandler.model.Position;
import com.stockhandler.model.Stock;

public class Main {

    public static void main(String[] args) {
        var portfolio = new Portfolio();

        portfolio.add(position("MSFT", 1, 260.0));
        portfolio.add(position("MSFT", 2, 250.0));
        portfolio.add(position("AAPL", 5, 90.0));
        portfolio.add(position("AAPL", 10, 80.0));
        portfolio.add(position("ORCL", 100, 80.0));

        portfolio.print();
    }

    private static Position position(String symbol, Integer quantity, Double price){
        return new Position(new Stock(symbol), quantity, price);
    }
}