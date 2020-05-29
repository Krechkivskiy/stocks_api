package com.test.task.stocks.stocks_api.service;

import com.test.task.stocks.stocks_api.entity.Company;

import java.util.List;

public interface CompanyService {

    List<String> getCompaniesSymbols();

    List<Company> uploadCompaniesInfoBySymbols(List<String> symbols1) throws InterruptedException;

    void saveAll(List<Company> companies);

    List<Company> getStatisticByHighestStock();

    List<Company> getTopCompaniesWithHigherChangeActionsPercent();
}
