package com.stock.alt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.alt.exception.StockException;
import com.stock.alt.exception.StockLimitException;
import com.stock.alt.model.Stock;

@Service
class StockServiceImpl implements StockService {

	Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

	Map<String, Stock> stockMap = new HashMap<String, Stock>();

	StockServiceImpl() {

		Stock stock = new Stock();
		stock.setStockQuote("HCL");
		stock.setDate(java.time.LocalDate.now().toString());
		System.out.println(java.time.LocalDate.now());
		stock.setCompanyName("HCL Tech");
		stock.setPrice(200.20989);
		stock.setCurrency("INR");

		logger.debug("Initial stock  : ", stock);
		stockMap.put(stock.getStockQuote(), stock);
	}

	public Stock addstock(Stock stock) {

		if (stockMap.size() < 10) {

			logger.debug(" Stock count : ", stockMap.size());
			stockMap.put(stock.getStockQuote(), stock);
		} else {
			logger.error(" Stock limit is 10");
			throw new StockLimitException();
		}
		return stock;
	}

	public String getAllStock() {
		try {
			if (stockMap.isEmpty())
				return "NO STOCK AVAILABLE";
			else
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(stockMap.values());

		} catch (Exception ex) {

			logger.error(" Error occured while getting all stock", ex, ex.getMessage());
			throw new StockException("Internal server Error");
		}
	}

	public Set<Stock> getStock(List<String> stockQuotes) throws StockException {

		Set<Stock> stocks = stockQuotes.stream().map(st -> stockMap.get(st)).filter(Objects::nonNull)
				.collect(Collectors.toSet());
		
		logger.debug(" filtered stocks : ",stocks);

		return stocks;
	}
}
