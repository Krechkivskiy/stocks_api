package com.test.task.stocks.stocks_api;

import com.test.task.stocks.stocks_api.entity.Company;
import com.test.task.stocks.stocks_api.service.CompanyServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner {

    private final CompanyServiceImpl companyService;

    public Runner(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                List<String> companiesSymbols = companyService.getCompaniesSymbols();
                List<Company> companies = companyService.uploadCompaniesInfoBySymbols(companiesSymbols);
                companyService.saveAll(companies);
                List<Company> topByMaxActionCost = companyService.getStatisticByHighestStock();
                List<Company> topByChangePercent = companyService.getTopCompaniesWithHigherChangeActionsPercent();
                System.out.println("TOP BY COMPANY COST");
                topByMaxActionCost.forEach(System.out::println);
                Thread.sleep(5000);
                System.out.println("TOP BY CHANGE PERCENT");
                topByChangePercent.forEach(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
