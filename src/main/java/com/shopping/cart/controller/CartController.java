package com.shopping.cart.controller;


import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.dto.request.RemoveItemRequest;
import com.shopping.cart.dto.response.*;
import com.shopping.cart.exception.ItemNotFoundException;
import com.shopping.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cart", produces = "application/json")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @GetMapping("/display-cart")
    public ResponseEntity<DisplayCartResponse> displayCart() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.displayCart());
    }

    @PostMapping("/add-item")
    public ResponseEntity<AddItemResponse> addItem(@RequestBody AddItemRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.addItem(request));
    }

    @PostMapping("/add-vas-item")
    public ResponseEntity<AddVasItemResponse> addVasItem(@RequestBody AddVasItemRequest request) throws ItemNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.addVasItem(request));
    }

    @PostMapping("/remove-item")
    public ResponseEntity<RemoveItemResponse> removeItem(@RequestBody RemoveItemRequest request) throws ItemNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.removeItem(request));
    }

    @PostMapping("/reset-cart")
    public ResponseEntity<ResetCartResponse> resetCart() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.resetCart());
    }
}
