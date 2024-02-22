package org.example;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<Stock>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue() {
        double value = 0;
        for (Stock stock : stocks) {
            value += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }
        return value;
    }
}