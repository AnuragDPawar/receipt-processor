package com.receiptprocessor.repository;

import com.receiptprocessor.exception.EntityNotFoundException;
import com.receiptprocessor.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReceiptRepository {
    private final ConcurrentHashMap<String, Receipt> receiptStore = new ConcurrentHashMap<>();

    public void save(String id, Receipt receipt) {
        receiptStore.put(id, receipt);
    }

    public Receipt findById(String id) {
        Receipt receipt = receiptStore.get(id);
        if (receipt == null) {
            throw new EntityNotFoundException("Receipt", id);
        }
        return receipt;
    }
}
