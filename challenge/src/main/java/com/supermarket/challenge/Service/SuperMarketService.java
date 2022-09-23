package com.supermarket.challenge.Service;


import com.supermarket.challenge.DTO.AddItemDTO;
import com.supermarket.challenge.Model.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface SuperMarketService {

    public ShoppingCart create();
    public ShoppingCart getBasket(UUID id);
    public ShoppingCart addItem(UUID idBasket, AddItemDTO addItem);
    public Item getItem(ShoppingCart basket, Products product);
    public ShoppingCart addPromotion(UUID idBasket, String promotionCode);
    public ShoppingCart checkout(UUID idBasket);

}
