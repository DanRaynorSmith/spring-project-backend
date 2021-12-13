package com.bae.stores.repo;


import com.bae.stores.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock, Integer> {

    List<Stock> findAllByCategoryIgnoreCase(String category);

}
