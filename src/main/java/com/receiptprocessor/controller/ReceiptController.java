package com.receiptprocessor.controller;

import com.receiptprocessor.model.Receipt;
import com.receiptprocessor.repository.ReceiptRepository;
import com.receiptprocessor.service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ReceiptRepository receiptRepository;

    public ReceiptController(ReceiptService receiptService, ReceiptRepository receiptRepository) {
        this.receiptService = receiptService;
        this.receiptRepository = receiptRepository;
    }

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt){
        String id = receiptService.processReceipt(receipt);
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        return response;
    }

    @GetMapping("/{id}")
    public Receipt getReceipt(@PathVariable String id){
        return receiptRepository.findById(id);
    }

    @GetMapping("/{id}/points")
    public Map<String, Integer> getPoints(@PathVariable("id") String id){
        Receipt receipt = receiptRepository.findById(id);
        int points = receiptService.calculatePoints(receipt);
        Map<String, Integer> response = new HashMap<>();
        response.put("points", points);
        return response;
    }

}
