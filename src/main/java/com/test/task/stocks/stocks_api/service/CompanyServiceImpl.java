package com.test.task.stocks.stocks_api.service;

import com.test.task.stocks.stocks_api.entity.Company;
import com.test.task.stocks.stocks_api.repository.CompanyRepository;
import com.test.task.stocks.stocks_api.storage.TemporaryCompanyStorage;
import com.test.task.stocks.stocks_api.util.CompanyChangePercentComparator;
import com.test.task.stocks.stocks_api.util.ConcurrentRequesttExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final RequestHandleService requestHandleService;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(RequestHandleService requestHandleService, CompanyRepository companyRepository) {
        this.requestHandleService = requestHandleService;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> uploadCompaniesInfoBySymbols(List<String> symbols) throws InterruptedException {
        int startPosition = 0;
        int step = symbols.size() / 4;
        ConcurrentRequesttExecutor thread = new ConcurrentRequesttExecutor(symbols
                .subList(0, step), requestHandleService, 0);
        thread.start();
        startPosition = startPosition + step;
        ConcurrentRequesttExecutor thread2 = new ConcurrentRequesttExecutor(symbols
                .subList(startPosition, startPosition + step), requestHandleService, startPosition);
        thread2.start();
        startPosition = startPosition + step;
        ConcurrentRequesttExecutor thread3 = new ConcurrentRequesttExecutor(symbols.
                subList(startPosition, startPosition + step), requestHandleService, startPosition);
        thread3.start();
        startPosition = startPosition + step;
        for (int i = startPosition; i < symbols.size(); i++) {
            try {
                String sym = symbols.get(i);
                TemporaryCompanyStorage.add(requestHandleService.getCompaniesInfoBySymbols(sym), i);
            } catch (HttpClientErrorException e) {
                --i;
            }
        }
        while (thread.isAlive() || thread2.isAlive() || thread3.isAlive()) {
            Thread.sleep(12);
        }
        return Arrays.asList(TemporaryCompanyStorage.getCompanies()).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Company> getStatisticByHighestStock() {
        return companyRepository.findTop5ByOrderByLatestPriceDescCompanyNameAsc();
    }

    @Override
    public List<Company> getTopCompaniesWithHigherChangeActionsPercent() {
        List<Company> result = new ArrayList<>();
        CompanyChangePercentComparator comparator = new CompanyChangePercentComparator();
        result.addAll(companyRepository.findBiggerChangePercent());
        result.addAll(companyRepository.findTop5ByOrderByChangePercentDesc());
        return result.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getCompaniesSymbols() {
        return requestHandleService.getCompaniesSymbols();
    }

    @Override
    public void saveAll(List<Company> companies) {
        companyRepository.saveAll(companies);
    }
}
