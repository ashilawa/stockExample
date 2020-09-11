package com.stock.alt.exception;

public class StockLimitException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockLimitException() {

        super("Only 10 Stock allowed");
    }
}