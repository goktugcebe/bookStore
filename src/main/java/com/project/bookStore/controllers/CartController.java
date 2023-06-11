package com.project.bookStore.controllers;

import com.project.bookStore.dtos.CartDTO;
import com.project.bookStore.service.CartService;
import com.project.bookStore.service.impl.ProductServiceImpl;
import com.project.bookStore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {
    private CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public String getCart(Model model, Principal principal) {
        if (principal != null) {
        List<CartDTO> cartDtos = cartService.getAll();
        double totalCartPrice = 0;

        for (CartDTO cart : cartDtos) {
            double productTotalPrice = cart.getQuantity() * cart.getProduct().getPrice();
            cart.setTotalPrice(productTotalPrice);
            totalCartPrice += productTotalPrice;
        }

        model.addAttribute("carts", cartDtos);
        model.addAttribute("totalCartPrice", totalCartPrice);

        return "cart";
        }
        return "login";
    }

    @PostMapping(value = "/update-cart", params = "action=update")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Integer cartId) {

        CartDTO cartDto = cartService.getById(cartId);
        cartDto.setQuantity(quantity);
        cartService.update(cartDto);

        return "redirect:/cart";

    }

    @PostMapping(value = "/delete-cart", params = "action=delete")
    public String deleteCartItem(@RequestParam("id") Integer cartId, Model model) {
        CartDTO cartDto = cartService.getById(cartId);
        cartService.update(cartDto);
        cartService.delete(cartDto.getId());
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }


    @PostMapping(value = "/delete-cart-all", params = "action=delete")
    public String deleteCart() {
        List<CartDTO> cartDTOS = this.cartService.getAll();
        this.cartService.deleteAll(cartDTOS);
        return "redirect:/checkout";
    }
}
