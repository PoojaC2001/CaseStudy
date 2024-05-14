package com.sha.springbootmicroservice2purchase.service;
import com.sha.springbootmicroservice2purchase.exception.PurchaseGetAllException;
import com.sha.springbootmicroservice2purchase.exception.PurchaseSaveException;
import com.sha.springbootmicroservice2purchase.model.Purchase;
import com.sha.springbootmicroservice2purchase.repository.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.time.LocalDateTime;
import java.util.List;
 
@Service
public class PurchaseServiceImpl implements PurchaseService {
 
    @Autowired
    private PurchaseRepository purchaseRepository;
 
    @Override
    public Purchase savePurchase(Purchase purchase) {
        try {
            purchase.setPurchaseTime(LocalDateTime.now());
            return purchaseRepository.save(purchase);
        } catch (Exception ex) {
            throw new PurchaseSaveException("Error occurred while saving purchase: " + ex.getMessage());
        }
    }
 
    @Override
    public List<Purchase> findAllPurchasesOfUser(Long userId) {
        try {
            List<Purchase> purchases = purchaseRepository.findAllByUserId(userId);
            if (purchases.isEmpty()) {
                throw new PurchaseGetAllException("No purchases found for user with ID: " + userId);
            }
            return purchases;
        } catch (Exception ex) {
            throw new PurchaseGetAllException("Error occurred while retrieving purchases: " + ex.getMessage());
        }
    }
}