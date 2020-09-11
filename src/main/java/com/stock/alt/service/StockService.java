package com.stock.alt.service;

import com.stock.alt.exception.StockException;
import com.stock.alt.model.Stock;

import java.util.List;
import java.util.Set;

public interface StockService {

    Stock addstock(Stock stock);

    String getAllStock();

    Set<Stock> getStock(List<String> stockQuotes) throws StockException;
}
