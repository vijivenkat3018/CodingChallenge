package com.supermarket.challenge.Controller;

import com.supermarket.challenge.Model.Products;
import com.supermarket.challenge.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
    @RequestMapping("/api/products")
    public class ProductController {

       @Autowired
        private ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }
    @RequestMapping(value="/products/{id}",method= RequestMethod.GET)
    public Products getProduct(@PathVariable("id")String id) {
        return productService.getProductById(id);
    }

          @RequestMapping(value="/products/",method=RequestMethod.GET)
    public List<Products> getProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping(value="/products/",method=RequestMethod.POST)
    public Products createProduct(@RequestBody Products products)
    {
        return productService.save(products);
    }
    @RequestMapping(value="/products/",method=RequestMethod.PUT)
    public Products updateProduct(@RequestBody Products products)
    {
        return productService.save(products);
    }
}


