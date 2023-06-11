package com.project.bookStore.service;

import com.project.bookStore.dataAccess.entities.Cart;
import com.project.bookStore.dataAccess.entities.Product;
import com.project.bookStore.dataAccess.entities.User;
import com.project.bookStore.dtos.CartDTO;

import java.util.List;

public interface CartService {

    void add(CartDTO cartDto);

    void delete(int id);
    void deleteAll(List<CartDTO> cartDtos);
    void update(CartDTO cartDto);

    List<CartDTO> getAll();
    CartDTO getById(int id);

    void addToCart(int productId);

}
