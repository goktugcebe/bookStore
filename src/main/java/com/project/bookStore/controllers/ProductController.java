package com.project.bookStore.controllers;

import com.project.bookStore.dataAccess.entities.Author;
import com.project.bookStore.dataAccess.entities.Product;
import com.project.bookStore.dataAccess.entities.User;
import com.project.bookStore.dtos.AuthorDTO;
import com.project.bookStore.dtos.CartDTO;
import com.project.bookStore.dtos.ProductDTO;
import com.project.bookStore.dtos.UserDTO;
import com.project.bookStore.service.AuthorService;
import com.project.bookStore.service.CartService;
import com.project.bookStore.service.impl.ProductServiceImpl;
import com.project.bookStore.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    private ProductServiceImpl productService;
    private CartService cartService;
    private AuthorService authorService;
    private UserServiceImpl userService;

    @Autowired
    public ProductController(ProductServiceImpl productService, CartService cartService, AuthorService authorService, UserServiceImpl userService) {
        this.productService = productService;
        this.cartService = cartService;
        this.authorService = authorService;
        this.userService = userService;
    }

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


    @GetMapping("/products")
    public String viewProductList(Model model) {
        List<ProductDTO> productDTOS = productService.getAll();
        model.addAttribute("products", productDTOS);
        return "products";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(Model model, @PathVariable Integer productId,Principal principal) {

        String email = principal.getName();
        User user = userService.findByEmail(email);
        UserDTO userDto = userService.getById(user.getId());
        ProductDTO productDto = productService.getById(productId);

        CartDTO cartDto = new CartDTO();

        if (cartDto == null) {
            cartDto.setProduct(productDto);
            cartDto.setUserDTO(userDto);
            cartDto.setQuantity(1);
            cartService.add(cartDto);
            model.addAttribute("carts", cartDto);
        } else {
            cartDto = userDto.getCartDTO();
            cartDto.setProduct(productDto);
            cartService.update(cartDto);
            model.addAttribute("carts", cartDto);
        }

        return "redirect:/cart";

    }
//        if (userDto.getCartDTO().getProduct().getId() == productId) {
//            // The product is exist in SC
//            return "redirect:/cart";
//        }

//        if (userDto.getCartDTO()==null) {
//
//            CartDTO cartDto = new CartDTO();
//
//        cartDto.setProduct(productDto);
//
//        cartDto.setId(userDto.getId());
//
//        cartDto.setUserDTO(userDto);
//
//        cartDto.setQuantity(1);
//
//        cartService.add(cartDto);
//
//        model.addAttribute("carts", cartDto);
//
//        return "redirect:/cart";
//    }
//
//        CartDTO cartDto = userDto.getCartDTO();
//
//
//
//        model.addAttribute("carts", cartDto);
//
//        return "redirect:/cart";
//    }

}
