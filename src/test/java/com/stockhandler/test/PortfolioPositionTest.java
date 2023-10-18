package com.stockhandler.test;

import com.stockhandler.model.Portfolio;
import com.stockhandler.model.Position;
import com.stockhandler.model.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PortfolioPositionTest {

    @Test
    public void emptyPortfolioZeroPositions(){
        var portfolio = new Portfolio();
        Assertions.assertEquals(0, portfolio.getAllPositions().size());
    }

    @Test
    public void portfolioWithOnePositionReturnsThatPosition(){
        var portfolio = new Portfolio();

        String microsoft = "MSFT";

        portfolio.add(new Position(new Stock(microsoft),  10, 260.0));
        Assertions.assertEquals(1, portfolio.getAllPositions().size());

        Assertions.assertEquals(10, portfolio.getPosition(microsoft).getQuantity());
        Assertions.assertEquals(260, portfolio.getPosition(microsoft).getAveragePrice());
        Assertions.assertEquals(2600, portfolio.getPosition(microsoft).getValue());
    }

    @Test
    public void portfolioWithTwoDifferentPositionsReturnsThosePositions(){
        var portfolio = new Portfolio();

        String microsoft = "MSFT";
        String apple = "AAPL";

        portfolio.add(position(microsoft,  10, 260.0));
        portfolio.add(position(apple,  2, 150.0));

        Assertions.assertEquals(2, portfolio.getAllPositions().size());

        Assertions.assertEquals(10, portfolio.getPosition(microsoft).getQuantity());
        Assertions.assertEquals(260, portfolio.getPosition(microsoft).getAveragePrice());
        Assertions.assertEquals(2600, portfolio.getPosition(microsoft).getValue());

        Assertions.assertEquals(2, portfolio.getPosition(apple).getQuantity());
        Assertions.assertEquals(150, portfolio.getPosition(apple).getAveragePrice());
        Assertions.assertEquals(300, portfolio.getPosition(apple).getValue());
    }

    @Test
    public void portfolioWithSameStockReturnsOnePosition(){
        var portfolio = new Portfolio();

        String microsoft = "MSFT";

        portfolio.add(position(microsoft, 10, 260.0));
        portfolio.add(position(microsoft, 5, 200.0));

        Assertions.assertEquals(1, portfolio.getAllPositions().size());
    }

    @Test
    public void portfolioWithSameStockReturnsCorrectQuantity(){
        var portfolio = new Portfolio();

        String microsoft = "MSFT";

        portfolio.add(position(microsoft, 10, 260.0));
        portfolio.add(position(microsoft, 1, 200.0));

        Assertions.assertEquals(11, portfolio.getPosition(microsoft).getQuantity());
    }

    @Test
    public void portfolioWithSameStockReturnsCorrectAveragePrice(){
        var portfolio = new Portfolio();

        String microsoft = "MSFT";

        portfolio.add(position(microsoft, 1, 240.0));
        portfolio.add(position(microsoft, 1, 220.0));

        Assertions.assertEquals(230, portfolio.getPosition(microsoft).getAveragePrice());
    }

    @Test
    public void portfolioWithSameStockReturnsCorrectPositionValue(){
        var portfolio = new Portfolio();

        String microsoft = "MSFT";

        portfolio.add(position(microsoft, 2, 240.0));
        portfolio.add(position(microsoft, 1, 220.0));

        Double expected = (2 * 240.0) + 220.0;
        Assertions.assertEquals(expected, portfolio.getPosition(microsoft).getValue());
    }

    @Test
    public void complexPortfolioReturnsCorrectTotalValue(){
        var portfolio = new Portfolio();

        portfolio.add(position("MSFT", 1, 260.0));
        portfolio.add(position("MSFT", 2, 250.0));

        portfolio.add(position("AAPL", 5, 90.0));
        portfolio.add(position("AAPL", 10, 80.0));

        portfolio.add(position("ORCL", 100, 80.0));

        Assertions.assertEquals(3, portfolio.getAllPositions().size());
        Assertions.assertEquals(10010.0, portfolio.getTotalValue());

    }

    private static Position position(String symbol, Integer quantity, Double price){
        return new Position(new Stock(symbol), quantity, price);
    }
}
