package com.sha.springbootmicroservice2purchase.service;

import com.sha.springbootmicroservice2purchase.model.Purchase;

import java.util.List;


public interface PurchaseService
{
    Purchase savePurchase(Purchase purchase);

    List<Purchase> findAllPurchasesOfUser(Long userId);
}
