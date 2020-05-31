package com.test.task.stocks.stocks_api.repository;

import com.test.task.stocks.stocks_api.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    List<Company> findTop5ByOrderByLatestPriceDescCompanyNameAsc();

    @Query(value = "SELECT * from Company c where c.change_percent" +
            " is not null order by c.change_percent desc limit 5",
            nativeQuery = true)
    List<Company> findBiggerChangePercent();

    @Query(value = "SELECT * from Company c where c.change_percent " +
            "is not null order by c.change_percent asc limit 5",
            nativeQuery = true)
    List<Company> findTop5ByOrderByChangePercentDesc();
}
