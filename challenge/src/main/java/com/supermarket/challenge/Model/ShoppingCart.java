package com.supermarket.challenge.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class ShoppingCart {

    private final UUID id;
    @JsonProperty
    private final List<Item> items;
    private final List<Promotion> promotions;
    private final ShoppingCartTotal shoppingCartTotal;
    private boolean checkedOut = false;

    private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.UK);

    public ShoppingCart() {
        this.id = UUID.randomUUID();
        this.items = new ArrayList<>();
        this.promotions = new ArrayList<>();
        this.shoppingCartTotal = new ShoppingCartTotal();
    }

    @SneakyThrows
    public ShoppingCartTotal getShoppingCartTotals() {
        Double rawTotal = Optional.ofNullable(items.stream().collect(Collectors.summingDouble(i -> i.getProduct().getPrice() * i.getAmount()))).orElse(0.00);
        Double totalPromos = Optional.ofNullable(promotions.stream().collect(Collectors.summingDouble(Promotion::getDiscount))).orElse(0.00);
        Double totalPayable = rawTotal - totalPromos;

        if (totalPayable < 0) {
            totalPayable = 0.00;
        }

        if (rawTotal > 0) {
            rawTotal /= 100;
        }

        if (totalPromos > 0) {
            totalPromos /= 100;
        }

        if (totalPayable > 0) {
            totalPayable /= 100;
        }

        shoppingCartTotal.setRawTotal(currencyFormatter.format(rawTotal));
        shoppingCartTotal.setTotalPromos(currencyFormatter.format(totalPromos));
        shoppingCartTotal.setTotalPayable(currencyFormatter.format(totalPayable));

        return shoppingCartTotal;
    }

    public ShoppingCart checkedOut(Boolean checkout) {
        this.checkedOut = checkout;
        return this;
    }
}
