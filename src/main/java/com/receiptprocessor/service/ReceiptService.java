package com.receiptprocessor.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.receiptprocessor.model.Receipt;
import com.receiptprocessor.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptService {
    private static final Logger logger = LoggerFactory.getLogger(ReceiptService.class);
    private final int pointsForFullAmount = 50;
    private final int pointsForQuarter = 25;
    private final int pointsForOddDay = 6;
    private final int pointsForSpecificTime = 10;
    private final ReceiptRepository repository;

    public ReceiptService(ReceiptRepository repository) {
        this.repository = repository;
    }

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        repository.save(id, receipt);
        return id;
    }

    public int calculatePoints(Receipt receipt) {
        logger.debug("Calculating points for receipt: {}", receipt);
        int points = 0;

        // adding points based on the alphanumeric characters
        points += receipt.getRetailer().replaceAll("[^A-Za-z0-9]", " ").length();

        // adding points based on total amount
        double total = Double.parseDouble(receipt.getTotal());
        if (total % 1 == 0) {
            points += this.pointsForFullAmount;
        }
        if (total % 0.25 == 0) {
            points += this.pointsForQuarter;
        }

        // adding points based on the no. of items
        points += (receipt.getItems().size() / 2) * 5;

        // adding points based on the  item description is a multiple of 3
        for (var item: receipt.getItems()) {
            String description = item.getShortDescription().trim();
            if (description.length() % 3 == 0) {
                points += (int) Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }

        // adding points if the day of the purchase date is odd
        String[] dateSplit = receipt.getPurchaseDate().split("-");
        int day = Integer.parseInt(dateSplit[2]);
        if (day % 2 != 0) {
            points += this.pointsForOddDay;
        }

        String[] timeSplit = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(timeSplit[0]);
        if (hour >= 14 && hour < 16) {
            points += this.pointsForSpecificTime;
        }
        return points;
    }

}
