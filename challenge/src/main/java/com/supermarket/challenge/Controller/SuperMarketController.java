package com.supermarket.challenge.Controller;

import com.supermarket.challenge.DTO.AddItemDTO;
import com.supermarket.challenge.Model.ShoppingCart;
import com.supermarket.challenge.Service.SuperMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/baskets")
public class SuperMarketController {
    @Autowired
    private final SuperMarketService superMarketService;

    public SuperMarketController(SuperMarketService superMarketService) {
        this.superMarketService = superMarketService;
    }

    @PostMapping
    public ShoppingCart create() {
        return superMarketService.create();
    }

    @GetMapping("/{id}")
    public ShoppingCart getBasket(@PathVariable UUID id) {
        return superMarketService.getBasket(id);
    }

    @PostMapping("/{id}/add-item")
    public ShoppingCart addItem(@PathVariable UUID id,@Valid @RequestBody AddItemDTO addItems) {
        return superMarketService.addItem(id, addItems);
    }

    @PostMapping("/{id}/add-promotion/{code}")
    public ShoppingCart addPromotion(@PathVariable UUID id,
                                                                              @PathVariable("code") String promotionCode) {
        return superMarketService.addPromotion(id, promotionCode);
    }

    @PatchMapping("/{id}/checkout")
    public ShoppingCart checkout(@PathVariable UUID id) {
        return superMarketService.checkout(id);
    }
}