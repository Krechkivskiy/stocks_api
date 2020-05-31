package com.test.task.stocks.stocks_api.service;

import com.test.task.stocks.stocks_api.entity.Company;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RequestHandleService {

    private static final String ALL_COMPANIES_SYMBOLS_URL = "https://sandbox.iexapis.com/" +
            "stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";

    private static final String INFO_BY_COMPANY_BY_SYMBOL_URL = "https://sandbox.iexapis.com/" +
            "stable/stock/insert/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571";
    public final RestTemplate restTemplate;

    public RequestHandleService() {
        this.restTemplate = new RestTemplate();
    }

    public List<String> getCompaniesSymbols() {
        Company[] result = Objects
                .requireNonNull(this.restTemplate.getForObject(ALL_COMPANIES_SYMBOLS_URL, Company[].class));
        return Arrays.stream(result).map(Company::getSymbol).collect(Collectors.toList());
    }

    public Company getCompaniesInfoBySymbol(String symbol) throws HttpClientErrorException {
        try {
            String link = INFO_BY_COMPANY_BY_SYMBOL_URL.replaceAll("insert", symbol);
            return this.restTemplate.getForObject(link, Company.class);
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(HttpStatus.MULTI_STATUS);
        }
    }
}
