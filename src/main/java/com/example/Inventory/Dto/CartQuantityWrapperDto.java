package com.example.Inventory.Dto;

import java.util.ArrayList;
import java.util.List;

public class CartQuantityWrapperDto {
    private List<CartCheckQuantityDto> cartCheckQuantityDtoList=new ArrayList<>();

    public List<CartCheckQuantityDto> getCartCheckQuantityDtoList() {
        return cartCheckQuantityDtoList;
    }

    public void setCartCheckQuantityDtoList(List<CartCheckQuantityDto> cartCheckQuantityDtoList) {
        this.cartCheckQuantityDtoList = cartCheckQuantityDtoList;
    }

    @Override
    public String toString() {
        return "CartQuantityWrapperDto{" +
                "cartCheckQuantityDtoList=" + cartCheckQuantityDtoList +
                '}';
    }
}
