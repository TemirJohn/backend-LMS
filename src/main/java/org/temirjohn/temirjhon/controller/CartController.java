package org.temirjohn.temirjhon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.temirjohn.temirjhon.request.CartRequest;
import org.temirjohn.temirjhon.entity.Cart;
import org.temirjohn.temirjhon.service.CartService;


@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart addToCart(@RequestBody CartRequest cartRequest) {
        return cartService.addToCart(cartRequest);
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
    }
}