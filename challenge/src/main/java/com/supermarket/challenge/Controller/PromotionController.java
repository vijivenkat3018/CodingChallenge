package com.supermarket.challenge.Controller;

import com.supermarket.challenge.Model.Promotion;
import com.supermarket.challenge.Service.PromotionService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

      private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {

        this.promotionService = promotionService;
    }

    @PostMapping
    public Promotion save(@Valid @RequestBody Promotion promotion) {

        return promotionService.save(promotion);
    }

    @GetMapping
    public List<Promotion> getPromotions() {

        return promotionService.getPromotions();
    }
}
