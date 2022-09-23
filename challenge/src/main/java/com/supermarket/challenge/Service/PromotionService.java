package com.supermarket.challenge.Service;

import com.supermarket.challenge.Model.Promotion;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class PromotionService {
     public static final List<Promotion> promotions = null;
//    public PromotionService() {
//
//        this.promotions = new ArrayList<>();
//    }
       public abstract List<Promotion> getPromotions();
    public abstract Promotion getPromotion(String promotionCode);

    public abstract Promotion save(Promotion newPromotion);

    public abstract boolean promotionExists(Promotion promotion);
}
