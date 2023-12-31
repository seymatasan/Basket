package com.shopping.cart.helper;

import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.model.VasItem;
import com.shopping.cart.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestHelper {

    //CART
    public static Cart createSampleCart() {
        Cart cart = new Cart();
        cart.setItems(new ArrayList<>());
        cart.setTotalAmount(0.0);
        cart.setAppliedPromotionId(0);
        cart.setTotalDiscount(0.0);
        return cart;
    }

    public static Item createSampleItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setCategoryId(Constants.FURNITURE_CATEGORY_ID);
        item.setSellerId(3);
        item.setPrice(10.0);
        item.setQuantity(1);
        item.setVasItems(new ArrayList<>());
        return item;
    }
    public static Item createSampleItem2() {
        Item item = new Item();
        item.setItemId(2);
        item.setCategoryId(Constants.FURNITURE_CATEGORY_ID);
        item.setSellerId(3);
        item.setPrice(10.0);
        item.setQuantity(1);
        item.setVasItems(new ArrayList<>());
        return item;
    }

    public static Item createSampleItem3() {
        Item item = new Item();
        item.setItemId(1);
        item.setCategoryId(1234);
        item.setSellerId(3);
        item.setPrice(10.0);
        item.setQuantity(1);
        item.setVasItems(new ArrayList<>());
        return item;
    }

    public static Item createSampleItem4() {
        Item item = new Item();
        item.setItemId(1);
        item.setCategoryId(2);
        item.setSellerId(3);
        item.setPrice(500_005.0);
        item.setQuantity(1);
        item.setVasItems(new ArrayList<>());
        return item;
    }

    public static Item createSampleHighPriceItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setCategoryId(2);
        item.setSellerId(3);
        item.setPrice(500_005.0);
        item.setQuantity(1);
        item.setVasItems(new ArrayList<>());
        return item;
    }

    public static VasItem createSampleHighPriceVasItem() {
        VasItem vasItem = new VasItem();
        vasItem.setItemId(1);
        vasItem.setVasItemId(2);
        vasItem.setVasCategoryId(Constants.VAS_ITEM_CATEGORY_ID);
        vasItem.setVasSellerId(Constants.VAS_ITEM_SELLER_ID);
        vasItem.setPrice(500_000.0);
        vasItem.setQuantity(1);
        return vasItem;
    }




    public static List<Item> createUniqueItems() {

        return IntStream.range(1, 11)
                .mapToObj(i -> new Item(i, 100 + i, 200 + i, 100.00, 1, new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public static List<Item> createSameSellerIdItems() {

        return IntStream.range(1, 11)
                .mapToObj(i -> new Item(i, 100 + i, 200, 10.00, 1, new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public static List<Item> createSameCategoryIdItems() {

        return IntStream.range(1, 11)
                .mapToObj(i -> new Item(i, 100, 200 + i, 10.00, 1, new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public static List<Item> createMaxItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(IntStream.range(1, 11)
                .mapToObj(i -> new Item(1, 100 + i, 200 + i, 19.99 * i, 1, new ArrayList<>()))
                .collect(Collectors.toList()));

        items.addAll(IntStream.range(1, 11)
                .mapToObj(i -> new Item(2, 100 + i, 200 + i, 19.99 * i, 1, new ArrayList<>()))
                .collect(Collectors.toList()));
        items.addAll(IntStream.range(1, 11)
                .mapToObj(i -> new Item(3, 100 + i, 200 + i, 19.99 * i, 1, new ArrayList<>()))
                .collect(Collectors.toList()));

        return items;
    }

    //ADD ITEM
    public static AddItemRequest createValidItemRequest() {
        AddItemRequest request = new AddItemRequest();
        request.setItemId(1);
        request.setCategoryId(1234);
        request.setSellerId(3);
        request.setPrice(10.0);
        request.setQuantity(1);
        return request;
    }

    public static AddItemRequest createNotValidPriceItemRequest() {
        AddItemRequest request = new AddItemRequest();
        request.setItemId(1);
        request.setCategoryId(2);
        request.setSellerId(3);
        request.setPrice(500_005.0);
        request.setQuantity(1);
        return request;
    }

    public static AddItemRequest createNotValidQuantityDefaultItemRequest() {
        AddItemRequest request = new AddItemRequest();
        request.setItemId(1);
        request.setCategoryId(2);
        request.setSellerId(3);
        request.setPrice(10.0);
        request.setQuantity(11);
        return request;
    }

    public static AddItemRequest createNotValidQuantityDigitalItemRequest() {
        AddItemRequest request = new AddItemRequest();
        request.setItemId(1);
        request.setCategoryId(Constants.DIGITAL_CATEGORY_ID);
        request.setSellerId(3);
        request.setPrice(10.0);
        request.setQuantity(6);
        return request;
    }

    public static AddItemRequest createNotAllowedTotalQuantityItemRequest() {
        AddItemRequest request = new AddItemRequest();
        request.setItemId(15);
        request.setCategoryId(1234);
        request.setSellerId(3);
        request.setPrice(10.0);
        request.setQuantity(1);
        return request;
    }

    public static AddItemRequest createNotValidSellerIdItemRequest() {
        AddItemRequest request = new AddItemRequest();
        request.setItemId(1);
        request.setCategoryId(2);
        request.setSellerId(Constants.VAS_ITEM_SELLER_ID);
        request.setPrice(10.0);
        request.setQuantity(1);
        return request;
    }

    public static AddItemRequest createPromotedCategoryIdItemRequest() {
        AddItemRequest request = new AddItemRequest();
        request.setItemId(1);
        request.setCategoryId(Constants.CATEGORY_PROMOTION_ID);
        request.setSellerId(3);
        request.setPrice(10.0);
        request.setQuantity(1);
        return request;
    }

    //ADD VAS ITEM

    public static AddVasItemRequest createValidVasItemRequest() {
        AddVasItemRequest request = new AddVasItemRequest();
        request.setItemId(2);
        request.setVasItemId(2);
        request.setVasCategoryId(Constants.VAS_ITEM_CATEGORY_ID);
        request.setVasSellerId(Constants.VAS_ITEM_SELLER_ID);
        request.setPrice(10.0);
        request.setQuantity(1);
        return request;
    }

    public static AddVasItemRequest createValidVasItemRequestForAddVasItem() {
        AddVasItemRequest request = new AddVasItemRequest();
        request.setItemId(1);
        request.setVasItemId(2);
        request.setVasCategoryId(Constants.VAS_ITEM_CATEGORY_ID);
        request.setVasSellerId(Constants.VAS_ITEM_SELLER_ID);
        request.setPrice(10.0);
        request.setQuantity(1);
        return request;
    }


    public static AddVasItemRequest createNotValidCategoryIdVasItemRequest() {
        AddVasItemRequest request = new AddVasItemRequest();
        request.setItemId(1);
        request.setVasItemId(2);
        request.setVasCategoryId(1234);
        request.setVasSellerId(Constants.VAS_ITEM_SELLER_ID);
        request.setPrice(15.0);
        request.setQuantity(4);
        return request;
    }

    public static AddVasItemRequest createNotValidSellerIdVasItemRequest() {
        AddVasItemRequest request = new AddVasItemRequest();
        request.setItemId(1);
        request.setVasItemId(2);
        request.setVasCategoryId(Constants.VAS_ITEM_CATEGORY_ID);
        request.setVasSellerId(5005);
        request.setPrice(15.0);
        request.setQuantity(1);
        return request;
    }

    public static AddVasItemRequest createHighPriceVasItemRequest() {
        AddVasItemRequest request = new AddVasItemRequest();
        request.setItemId(1);
        request.setVasItemId(2);
        request.setVasCategoryId(Constants.VAS_ITEM_CATEGORY_ID);
        request.setVasSellerId(Constants.VAS_ITEM_SELLER_ID);
        request.setPrice(500.0);
        request.setQuantity(2);
        return request;
    }

    public static AddVasItemRequest createNotValidQuantityVasItemRequest() {
        AddVasItemRequest request = new AddVasItemRequest();
        request.setItemId(1);
        request.setVasItemId(2);
        request.setVasCategoryId(Constants.VAS_ITEM_CATEGORY_ID);
        request.setVasSellerId(Constants.VAS_ITEM_SELLER_ID);
        request.setPrice(15.0);
        request.setQuantity(4);
        return request;
    }
}

