package com.project.bookStore.service;

import com.project.bookStore.dataAccess.entities.Product;
import com.project.bookStore.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    ProductDTO getById(int id);
    void add(ProductDTO productDto);
    void update(ProductDTO productDto);
    void deleteAll(List<ProductDTO> productDtoList);
    void delete(int id);

    List<Product> findByAuthor(String author);


//
//    Product getProductById(int id);
//
    List<ProductDTO> findByCategory(String category);
//
    List<ProductDTO> findByName(String name);
//
    List<String> getAllCategories();
//
//
//    Product getProductById(int id);

//    List<Product> getRelatedProducts(int categoryId);

//    List<Product> getProductsInCategory(int categoryId);

//    List<Product> filterHighPrice();
//
//    List<Product> filterLowPrice();


}
