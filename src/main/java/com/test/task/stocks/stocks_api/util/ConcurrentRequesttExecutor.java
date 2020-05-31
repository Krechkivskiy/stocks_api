package com.test.task.stocks.stocks_api.util;

import com.test.task.stocks.stocks_api.storage.TemporaryCompanyStorage;
import com.test.task.stocks.stocks_api.service.RequestHandleService;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public class ConcurrentRequesttExecutor extends Thread {

    private final RequestHandleService requestHandleService;

    private final List<String> symbols;

    private int startPosition;

    public ConcurrentRequesttExecutor(List<String> symbols,
                                      RequestHandleService requestHandleService,
                                      int startPosition) {
        this.symbols = symbols;
        this.requestHandleService = requestHandleService;
        this.startPosition = startPosition;
    }

    @Override
    public void run() {
        for (int i = 0; i < symbols.size(); i++) {
            String symbol = symbols.get(i);
            try {
                TemporaryCompanyStorage.add(requestHandleService.getCompaniesInfoBySymbol(symbol), startPosition);
                startPosition++;
            } catch (HttpClientErrorException e) {
                --i;
            }
        }
    }
}
