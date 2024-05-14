package com.sha.springbootmicroservice2purchase;

import com.sha.springbootmicroservice2purchase.exception.PurchaseGetAllException;
import com.sha.springbootmicroservice2purchase.exception.PurchaseSaveException;
import com.sha.springbootmicroservice2purchase.model.Purchase;
import com.sha.springbootmicroservice2purchase.repository.PurchaseRepository;
import com.sha.springbootmicroservice2purchase.service.PurchaseServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.ArrayList;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
public class PurchaseServiceImplTest {
 
    @Mock
    private PurchaseRepository purchaseRepository;
 
    @InjectMocks
    private PurchaseServiceImpl purchaseService;
 
    @Test
    public void testSavePurchase_Success() {
        // Arrange
        Purchase purchase = new Purchase();
        purchase.setUserId(1L);
        purchase.setCourseId(1L);
        purchase.setTitle("Sample Title");
        purchase.setPrice(10.0);
 
        // Act
        when(purchaseRepository.save(purchase)).thenReturn(purchase);
        Purchase savedPurchase = purchaseService.savePurchase(purchase);
 
        // Assert
        assertNotNull(savedPurchase);
        assertNotNull(savedPurchase.getPurchaseTime());
        verify(purchaseRepository, times(1)).save(purchase);
    }
 
    @Test
    public void testSavePurchase_Failure() {
        // Arrange
        Purchase purchase = new Purchase();
 
        // Act
        when(purchaseRepository.save(purchase)).thenThrow(new RuntimeException("Test exception"));
 
        // Assert
        PurchaseSaveException exception = assertThrows(PurchaseSaveException.class, () -> purchaseService.savePurchase(purchase));
        assertEquals("Error occurred while saving purchase: Test exception", exception.getMessage());
        verify(purchaseRepository, times(1)).save(purchase);
    }
 
    @Test
    public void testFindAllPurchasesOfUser_Success() {
        // Arrange
        Long userId = 1L;
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase());
 
        // Act
        when(purchaseRepository.findAllByUserId(userId)).thenReturn(purchases);
        List<Purchase> result = purchaseService.findAllPurchasesOfUser(userId);
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(purchaseRepository, times(1)).findAllByUserId(userId);
    }
 
 
 
    @Test
    public void testFindAllPurchasesOfUser_Failure() {
        // Arrange
        Long userId = 1L;
 
        // Act
        when(purchaseRepository.findAllByUserId(userId)).thenThrow(new RuntimeException("Test exception"));
 
        // Assert
        PurchaseGetAllException exception = assertThrows(PurchaseGetAllException.class, () -> purchaseService.findAllPurchasesOfUser(userId));
        assertEquals("Error occurred while retrieving purchases: Test exception", exception.getMessage());
        verify(purchaseRepository, times(1)).findAllByUserId(userId);
    }
}