package com.stock.alt.model;

import org.apache.commons.math3.util.Precision;

public class Stock {

    private String stockQuote;
    private String companyName;
    private String date;
    private double price;
    private String currency;

    public String getStockQuote() {
        return stockQuote;
    }

    public void setStockQuote(String stockQuote) {
        this.stockQuote = stockQuote;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        this.price = Precision.round(price, 2);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (stockQuote != null) {
            result = 31 * result + stockQuote.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stock other = (Stock) obj;
        if (stockQuote != other.stockQuote)
            return false;
        return true;
    }
}
