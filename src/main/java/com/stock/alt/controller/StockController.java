package com.stock.alt.controller;

import java.util.List;
import java.util.Set;

import javax.naming.LimitExceededException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stock.alt.exception.StockException;
import com.stock.alt.model.Stock;
import com.stock.alt.service.StockService;

@RestController
public class StockController {
	
	Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockServiceImpl;

    @PostMapping("/stockQuote")
    @ResponseStatus(HttpStatus.CREATED)
    public Stock createStock(@RequestBody Stock stock) throws LimitExceededException {

    	logger.debug("Received stock : ",stock);
        return stockServiceImpl.addstock(stock);
    }

    @GetMapping("/stockQuote")
    @ResponseStatus(HttpStatus.OK)
    public Set<Stock> getStock(@RequestParam List<String> stockQuotes) throws StockException {

    	logger.debug("Received stockQuotes list : ",stockQuotes);
        return stockServiceImpl.getStock(stockQuotes);
    }

    @GetMapping("/stockQuotes")
    @ResponseStatus(HttpStatus.OK)
    public String getStock() throws StockException {

        return stockServiceImpl.getAllStock();
    }
}
