package com.bae.stores.service;

import com.bae.stores.domain.Stock;
import com.bae.stores.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private StockRepo repo;

    @Autowired
    public StockService (StockRepo repo) {
        this.repo = repo;
    }

    public Stock createStock(Stock stock) {
        Stock created = this.repo.save(stock);
        return created;
    }

    public List<Stock> getAllStock(){
        return this.repo.findAll();
    }

    public Stock getStock(Integer id) {
        return this.repo.findById(id).get();
    }

    public List<Stock> findAllByCategoryIgnoreCase(String category) {
        return this.repo.findAllByCategoryIgnoreCase(category);
    }

    public Stock replaceStock(Integer id, Stock newStock) {
        Stock replaced = this.repo.findById(id).get();

        replaced.setDescription(newStock.getDescription());
        replaced.setCategory(newStock.getCategory());
        replaced.setStockCount(newStock.getStockCount());

        Stock updated = this.repo.save(replaced);
        return updated;
    }

    public void removeStock(Integer id) {
        this.repo.deleteById(id);
    }

}
