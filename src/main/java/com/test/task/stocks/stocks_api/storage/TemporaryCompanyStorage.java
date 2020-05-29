package com.test.task.stocks.stocks_api.storage;

import com.test.task.stocks.stocks_api.entity.Company;

public class TemporaryCompanyStorage {

    private static final Company[] companies = new Company[500000];

    public static void add(Company company, int index) {
        companies[index] = company;
    }

    public static Company[] getCompanies() {
        return companies;
    }

    public static void clearStorage() {
    }
}
