package com.shopping.cart.service;

import com.shopping.cart.converter.Converter;
import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.dto.request.RemoveItemRequest;
import com.shopping.cart.dto.response.*;
import com.shopping.cart.exception.ItemNotFoundException;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.model.VasItem;
import com.shopping.cart.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CartService {

    private final Cart cart;
    private final Converter converter;
    private final ValidationService validationService;
    private final PromotionService promotionService;

    @Autowired
    public CartService(Cart cart, Converter converter, ValidationService validationService, PromotionService promotionService) {
        this.cart = cart;
        this.converter = converter;
        this.validationService = validationService;
        this.promotionService = promotionService;
    }

    public DisplayCartResponse displayCart() {
        DisplayCartResponse response = new DisplayCartResponse();
        response.setResult(Constants.TRUE);
        response.setCart(cart);

        return response;
    }

    public AddItemResponse addItem(AddItemRequest request) {
        Item newItem = converter.addItemRequestConvertToItem(request);
        validationService.validateAddItem(cart, request);

        Item existingItem = findItemByItemId(request.getItemId());

        if (existingItem != null) {

            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
        } else {

            cart.getItems().add(newItem);
        }
        promotionService.calculateCart();
        promotionService.checkTotalPriceAfterAddingItem(newItem);

        AddItemResponse response = new AddItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.ADD_ITEM_MESSAGE);

        return response;
    }

    public AddVasItemResponse addVasItem(AddVasItemRequest request) {
        VasItem newVasItem = converter.addVasItemRequestConvertToVasItem(request);
        Item existingItem = findItemByItemId(request.getItemId());

        if (existingItem != null) {
            VasItem existingVasItem = findVasItemByVasItemId(existingItem, request.getVasItemId());
            validationService.validateAddVasItem(cart, existingItem, existingVasItem, request);

            if (existingVasItem != null) {
                existingVasItem.setQuantity(existingVasItem.getQuantity() + request.getQuantity());
            } else {
                existingItem.getVasItems().add(newVasItem);
            }
        } else {
            throw new ItemNotFoundException();
        }

        promotionService.calculateCart();
        promotionService.checkTotalPriceAfterAddingVasItem(existingItem, newVasItem);

        AddVasItemResponse response = new AddVasItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.ADD_ITEM_MESSAGE);

        return response;
    }

    public RemoveItemResponse removeItem(RemoveItemRequest request) {

        Item itemToRemove = cart.getItems().stream()
                .filter(item -> item.getItemId().equals(request.getItemId()))
                .findFirst().
                orElseThrow(ItemNotFoundException::new);

        cart.getItems().remove(itemToRemove);
        promotionService.calculateCart();

        RemoveItemResponse response = new RemoveItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.REMOVE_ITEM_MESSAGE);

        return response;
    }

    public ResetCartResponse resetCart() {
        cart.getItems().clear();
        promotionService.calculateCart();

        ResetCartResponse response = new ResetCartResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.RESET_CART_MESSAGE);

        return response;
    }


    private Item findItemByItemId(Integer itemId) {

        return cart.getItems().stream().filter(item ->
                Objects.equals(item.getItemId(), itemId)).findFirst().orElse(null);
    }

    private VasItem findVasItemByVasItemId(Item item, Integer vasItemId) {
        return item.getVasItems().stream().filter(vasItem ->
                Objects.equals(vasItem.getVasItemId(), vasItemId)).findFirst().orElse(null);
    }


}
