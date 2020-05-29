package com.test.task.stocks.stocks_api.util;

import com.test.task.stocks.stocks_api.entity.Company;

import java.util.Comparator;

public class CompanyChangePercentComparator implements Comparator<Company> {

    @Override
    public int compare(Company c1, Company c2) {
        Double firstChangePercent = Math.abs(c1.getChangePercent());
        Double secondChangePercent = Math.abs(c2.getChangePercent());
        return secondChangePercent.compareTo(firstChangePercent);
    }
}
