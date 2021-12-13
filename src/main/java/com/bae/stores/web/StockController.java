package com.bae.stores.web;

import com.bae.stores.domain.Stock;
import com.bae.stores.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

class StockController {

    private StockService service;

    @Autowired
    public StockController(StockService service) {
        this.service = service;
    }

    @PostMapping("/create") //201
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock created = this.service.createStock(stock);
        ResponseEntity<Stock> response = new ResponseEntity<Stock>(created, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/getAll") //200
    public ResponseEntity<List<Stock>> getAllStock(){
        return ResponseEntity.ok(this.service.getAllStock());
    }

    @GetMapping("/get/{id}") //200
    public Stock getStock(@PathVariable Integer id) {
        return this.service.getStock(id);
    }

    @GetMapping("/getAllCategory/{category}")
    public ResponseEntity<List<Stock>> findAllByCategory(@PathVariable String category) {
        List<Stock> found = this.service.findAllByCategoryIgnoreCase(category);
        return ResponseEntity.ok(found);
    }

    @PutMapping("/replace/{id}") //202
    public ResponseEntity<Stock> replaceStock(@PathVariable Integer id, @RequestBody Stock newStock) {
        Stock body = this.service.replaceStock(id, newStock);
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/remove/{id}") //204
    public ResponseEntity<?> removeStock(@PathVariable Integer id) {
        this.service.removeStock(id);
        return new ResponseEntity<Stock>(HttpStatus.NO_CONTENT);
    }

}
