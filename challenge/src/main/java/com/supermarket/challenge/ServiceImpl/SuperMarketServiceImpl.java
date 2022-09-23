package com.supermarket.challenge.ServiceImpl;

import com.supermarket.challenge.DTO.AddItemDTO;
import com.supermarket.challenge.Model.*;
import com.supermarket.challenge.Service.ProductService;
import com.supermarket.challenge.Service.PromotionService;
import com.supermarket.challenge.Service.SuperMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class SuperMarketServiceImpl implements SuperMarketService {

    private final List<ShoppingCart> baskets;
    private final ProductService productService;
    private final PromotionService promotionService;

     public SuperMarketServiceImpl(List<ShoppingCart> baskets, ProductService productService, PromotionService promotionService) {
        this.baskets = baskets;
        this.productService = productService;
        this.promotionService = promotionService;
    }

    public ShoppingCart create() {
        ShoppingCart basket = new ShoppingCart();
        baskets.add(basket);
        return basket;
    }

    public ShoppingCart getBasket(UUID id) {
        return baskets.stream()
                .filter(b-> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "basket.notfound"));
    }

    public ShoppingCart addItem(UUID idBasket, AddItemDTO addItem) {
        ShoppingCart basket = getBasket(idBasket);

        if (basket.isCheckedOut()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "basket.checkedout");
        }

        Products product = productService.getProductById(addItem.getIdProduct());
        Item item = getItem(basket, product);
        item.setAmount(item.getAmount() + addItem.getAmount());
        int indexItem = basket.getItems().stream()
                .map(i -> i.getProduct().getId())
                .collect(Collectors.toList())
                .indexOf(addItem.getIdProduct());
        if (indexItem == -1) {
            basket.getItems().add(item);
        }
        return basket;
    }

    public Item getItem(ShoppingCart basket, Products product) {
        return basket.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(new Item(product, 0));
    }

    public ShoppingCart addPromotion(UUID idBasket, String promotionCode) {
        ShoppingCart basket = getBasket(idBasket);

        if (basket.isCheckedOut()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "basket.checkedout");
        }

        Promotion promotion = promotionService.getPromotion(promotionCode);
        if (promotion.isExpired()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "promotion.expired");
        }

        if (promotionAlreadyExists(idBasket, promotionCode)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "promotion.alreadyexists");
        }

        basket.getPromotions().add(promotion);

        return basket;
    }

    private boolean promotionAlreadyExists(UUID idBasket, String promotionCode) {
        return getBasket(idBasket).getPromotions().stream().anyMatch(p -> p.getCode().equals(promotionCode));
    }

    public ShoppingCart checkout(UUID idBasket) {
        ShoppingCart basket = getBasket(idBasket);

        if (basket.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "basket.emptycheckout");
        }

        return basket.checkedOut(true);
    }
}
