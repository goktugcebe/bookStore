package com.project.bookStore.service.impl;

import com.project.bookStore.dataAccess.entities.Product;
import com.project.bookStore.dataAccess.repositories.ProductRepository;
import com.project.bookStore.dtos.ProductDTO;
import com.project.bookStore.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;



    @Override
    public ProductDTO getById(int id) {
        Product product = this.productRepository.findById(id).orElseThrow();
        ProductDTO productDto = this.modelMapper.map(product, ProductDTO.class);

        return productDto;
    }


//
//    private List<ProductDTO> transfer(List<Product> products){
//        List<ProductDTO> productDtoList = new ArrayList<>();
//        for(Product product : products){
//            ProductDTO productDto = new ProductDTO();
//            productDto.setId(product.getId());
//            productDto.setName(product.getName());
//            productDto.setDescription(product.getDescription());
//            productDto.setQuantity(product.getQuantity());
//            productDto.setCategory(product.getCategory());
//            productDto.setPrice(product.getPrice());
//            productDtoList.add(productDto);
//        }
//        return productDtoList;
//    }



    @Override
    public List<Product> findByAuthor(String author) {
        return this.productRepository.findByCategory(author);
    }



    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();

        //business rules
        return products.stream().map(movie ->
                        this.modelMapper.map(movie, ProductDTO.class))
                .collect(Collectors.toList());
    }






    @Override
    public void add(ProductDTO productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        this.productRepository.save(product);
    }

    @Override
    public void update(ProductDTO productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        this.productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        this.productRepository.deleteById(id);
    }


    @Override
    public List<ProductDTO> findByName(String keyword) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(keyword);

        return products.stream().map(book ->
                        this.modelMapper.map(book, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCategories() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(book -> this.modelMapper.map(book, ProductDTO.class))
                .collect(Collectors.toList());
    }




    @Override
    public void deleteAll(List<ProductDTO> productDtoList) {
        List<Product> products = productDtoList.stream()
                .map(productDto -> this.modelMapper.map(productDtoList, Product.class))
                .collect(Collectors.toList());
        this.productRepository.deleteAll(products);
    }



}
