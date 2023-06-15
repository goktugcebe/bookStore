package com.project.bookStore.controllers;

import com.project.bookStore.dataAccess.entities.Author;
import com.project.bookStore.dataAccess.entities.Product;
import com.project.bookStore.dtos.AuthorDTO;
import com.project.bookStore.dtos.CartDTO;
import com.project.bookStore.dtos.ProductDTO;
import com.project.bookStore.dtos.UserDTO;
import com.project.bookStore.service.AuthorService;
import com.project.bookStore.service.CartService;
import com.project.bookStore.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("product")
public class ProductController {
    private ProductServiceImpl productService;
    private CartService cartService;
    private AuthorService authorService;

    @GetMapping("/categories")
    public String getCategories(Model model) {
        List<String> categories = productService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/products")
    public String productsByCategory(@RequestParam("category") String categoryName, Model model) {
        List<ProductDTO> products = productService.findByCategory(categoryName);
        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryName);
        return "products-by-category";
    }

//    @GetMapping("/products/{category}")
//    public String getBooksByCategory(Model model, @PathVariable String category) {
//        List<ProductDTO> products = productService.findByCategory(category);
//        model.addAttribute("category", category);
//        model.addAttribute("products", products);
//        return "products-by-category";
//    }


    @GetMapping("/search")
    public String searchProducts(Model model, @RequestParam("keyword") String keyword) {
        List<ProductDTO> productDTOS = productService.findByName(keyword);
        model.addAttribute("products", productDTOS);
        return "product-list";
    }


    @GetMapping("by-author/{authorId}")
    public String getProductsByAuthor(Model model, @PathVariable Integer authorId) {
        AuthorDTO authorDTO=authorService.getById(authorId);
        model.addAttribute("products", authorDTO.getProducts());
        return "productbyauthor";
    }

    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        List<Author> authors=authorService.getAll();
        model.addAttribute("authors", authors);
        return "authors";
    }


    @GetMapping("/products-by-author/{author}")
    public String findByCategory(@PathVariable String author, Model model){
        List<ProductDTO> productDtos = productService.findByCategory(author);
        productDtos.forEach(productDto -> productDto.getAuthor().getName());
        model.addAttribute("products", productDtos);
        return "authors";
    }


//    @GetMapping("/products/{productId}")
//    public String getProductById(Model model, @PathVariable Integer productId) {
//        ProductDTO product = productService.getById(productId);
//        model.addAttribute("products", product);
//        return "productdetails";
//    }

    @GetMapping("/products")
    public String viewProductList(Model model) {
        List<ProductDTO> productDTOS = productService.getAll();
        model.addAttribute("products", productDTOS);
        return "products";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(Model model, @PathVariable Integer productId){
        CartDTO cartDto = new CartDTO();

        ProductDTO productDto = productService.getById(productId);

        cartDto.setProduct(productDto);

        UserDTO userDTODto = new UserDTO();
        int customerId = 1;
        userDTODto.setId(customerId);

        cartDto.setUserDTO(userDTODto);

        cartDto.setQuantity(1);

        cartService.add(cartDto);

        model.addAttribute("carts", cartDto);

        return "redirect:/cart";
    }




//    @PostMapping("/books")
//    public String addBookToCart(@Valid CartDTO cartDTO) {
//        cartService.addToCart(cartDTO);
//        return "redirect:/books";
//    }


}
