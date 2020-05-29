package com.test.task.stocks.stocks_api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "latestPrice")
    private Double latestPrice;

    @Column(name = "changePercent")
    private Double changePercent;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "primaryExchange")
    private String primaryExchange;

    @Column(name = "calculationPrice")
    private String calculationPrice;

    public Company() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", latestPrice=" + latestPrice +
                ", changePercent=" + changePercent +
                ", companyName='" + companyName + '\'' +
                ", primaryExchange='" + primaryExchange + '\'' +
                ", calculationPrice='" + calculationPrice + '\'' +
                '}';
    }
}
