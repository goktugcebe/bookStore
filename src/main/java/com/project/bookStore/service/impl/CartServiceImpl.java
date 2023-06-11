package com.project.bookStore.service.impl;

import com.project.bookStore.dataAccess.entities.Cart;
import com.project.bookStore.dataAccess.repositories.CartRepository;
import com.project.bookStore.dtos.CartDTO;
import com.project.bookStore.dtos.ProductDTO;
import com.project.bookStore.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final  ProductServiceImpl productService;

    @Override
    public void add(CartDTO cartDto) {
        Cart cart = this.modelMapper.map(cartDto,Cart.class);
        this.cartRepository.save(cart);
    }

    @Override
    public void delete(int id) {
        this.cartRepository.deleteById(id);

    }

    @Override
    public void deleteAll(List<CartDTO> cartDtos) {
        List<Cart> carts = cartDtos.stream()
                .map(cartDto -> this.modelMapper.map(cartDto, Cart.class))
                .collect(Collectors.toList());
        cartRepository.deleteAll(carts);
    }

    @Override
    public void update(CartDTO cartDto) {
        Cart cart = this.modelMapper.map(cartDto,Cart.class);
        cart.setId(cart.getId());
        this.cartRepository.save(cart);
    }


    @Override
    public List<CartDTO> getAll() {
        List<Cart> carts = this.cartRepository.findAll();
        return carts.stream()
                .map(cart -> this.modelMapper.map(cart,CartDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartDTO getById(int id) {
        Cart cart = this.cartRepository.findById(id).orElseThrow();
        CartDTO cartDto = this.modelMapper.map(cart,CartDTO.class);
        cartDto.setId(cart.getId());
        return cartDto;
    }



    @Override
    public void addToCart(int productId) {
        ProductDTO productDto = productService.getById(productId);


        Cart cart = new Cart();
        cart.setName(productDto.getName());


        CartDTO cartDto = this.modelMapper.map(cart,CartDTO.class);
        this.cartRepository.save(cart);

    }



//
//@Override
//public Cart addItemToCart(Product product, int quantity, User user) {
//    Cart cart = user.getCart();
//
//    if (cart == null) {
//        cart = new Cart();
//    }
//    Set<CartItem> cartItems = cart.getCartItem();
//    CartItem cartItem = findCartItem(cartItems, product.getId());
//    if (cartItems == null) {
//        cartItems = new HashSet<>();
//        if (cartItem == null) {
//            cartItem = new CartItem();
//            cartItem.setProduct(product);
//            cartItem.setTotalPrice(quantity * product.getPrice());
//            cartItem.setQuantity(quantity);
//            cartItem.setCart(cart);
//            cartItems.add(cartItem);
//            cartItemRepository.save(cartItem);
//        }
//    } else {
//        if (cartItem == null) {
//            cartItem = new CartItem();
//            cartItem.setProduct(product);
//            cartItem.setTotalPrice(quantity * product.getPrice());
//            cartItem.setQuantity(quantity);
//            cartItem.setCart(cart);
//            cartItems.add(cartItem);
//            cartItemRepository.save(cartItem);
//        } else {
//            cartItem.setQuantity(cartItem.getQuantity() + quantity);
//            cartItem.setTotalPrice(cartItem.getTotalPrice() + ( quantity * product.getPrice()));
//            cartItemRepository.save(cartItem);
//        }
//    }
//    cart.setCartItem(cartItems);
//
//    int totalItems = totalItems(cart.getCartItem());
//    double totalPrice = totalPrice(cart.getCartItem());
//
//    cart.setTotalPrice(totalPrice);
//    cart.setTotalItems(totalItems);
//    cart.setUser(user);
//
//    return cartRepository.save(cart);
//}
//
//    private CartItem findCartItem(Set<CartItem> cartItems, int id) {
//        if (cartItems == null) {
//            return null;
//        }
//        CartItem cartItem = null;
//
//        for (CartItem item : cartItems) {
//            if (item.getProduct().getId() == id) {
//                cartItem = item;
//            }
//        }
//        return cartItem;
//    }
//
//    @Override
//    public Cart updateItemInCart(Product product, int quantity, User user) {
//        Cart cart = user.getCart();
//
//        Set<CartItem> cartItems = cart.getCartItem();
//
//        CartItem item = findCartItem(cartItems, product.getId());
//
//        item.setQuantity(quantity);
//        item.setTotalPrice(quantity * product.getPrice());
//
//        cartItemRepository.save(item);
//
//        int totalItems = totalItems(cartItems);
//        double totalPrice = totalPrice(cartItems);
//
//        cart.setTotalItems(totalItems);
//        cart.setTotalPrice(totalPrice);
//
//        return cartRepository.save(cart);
//    }
//
//    @Override
//    public Cart deleteItemFromCart(Product product, User user) {
//        Cart cart = user.getCart();
//
//        Set<CartItem> cartItems = cart.getCartItem();
//
//        CartItem item = findCartItem(cartItems, product.getId());
//
//        cartItems.remove(item);
//
//        cartItemRepository.delete(item);
//
//        double totalPrice = totalPrice(cartItems);
//        int totalItems = totalItems(cartItems);
//
//        cart.setCartItem(cartItems);
//        cart.setTotalItems(totalItems);
//        cart.setTotalPrice(totalPrice);
//
//        return cartRepository.save(cart);
//    }
//
//    private CartItem findCartItem(Set<CartItem> cartItems, Long productId) {
//        if (cartItems == null) {
//            return null;
//        }
//        CartItem cartItem = null;
//
//        for (CartItem item : cartItems) {
//            if (item.getProduct().getId() == productId) {
//                cartItem = item;
//            }
//        }
//        return cartItem;
//    }
//
//    private int totalItems(Set<CartItem> cartItems){
//        int totalItems = 0;
//        for(CartItem item : cartItems){
//            totalItems += item.getQuantity();
//        }
//        return totalItems;
//    }
//
//    private double totalPrice(Set<CartItem> cartItems){
//        double totalPrice = 0.0;
//
//        for(CartItem item : cartItems){
//            totalPrice += item.getTotalPrice();
//        }
//
//        return totalPrice;
//    }

//    public void addToCart(int id) {
//    Product product=this.productRepository.findById(id).get();
//    User user=userRepository.findById(id).get();
//    CartProduct cartProduct=cartProductRepository.findById(id).get();
//
//    if (product!=null && user!=null){
//        CartProduct cartProduct=new CartProduct();
//    Cart cart=new Cart(0,user,cartProduct);
//    }
//    }
//
//
//
//    @Override
//    public Cart addToCart(CartDTO cartDTO) {
//        Cart cart = new Cart();
//        cart.setTotalPrice(0);
//        modelMapper.map(cartDTO, cart);
//
//        User user = new User();
//        cart.setUser(user);
//
//        Product product=new Product();
//
//        CartProduct cartProduct = new CartProduct();
//        cartProduct.setCart(cart);
//        cartProduct.setProduct(product);
//        Set<CartProduct> cartProductList = new HashSet<>();
//
//        product.setCartProducts(cartProductList);
//        cart.setCartProducts(cartProductList);
//        cartProductList.add(cartProduct);
//
//        productRepository.save(product);
//        cartProductRepository.save(cartProduct);
//        add(cartDTO);
//        return cart;
//    }
//
//    @Override
//    public List<CartDTO> getAll() {
//        List<Cart> carts = this.cartRepository.findAll();
//
//        return carts.stream().map(order ->
//                        this.modelMapper.map(order, CartDTO.class))
//                .collect(Collectors.toList());
//    }
//
//
//    @Override
//    public CartDTO getById(int id) {
//        Cart cart = this.cartRepository.findById(id).orElseThrow();
//
//        return this.modelMapper.map(cart, CartDTO.class);
//    }
//
//    @Override
//    public void add(CartDTO cartDto) {
////        cartDto.setTotalPrice(0);
////        User user=this.userRepository.findById(id).get();
////        cartDto.setUserId(user.getId());
//        Cart cart = this.modelMapper.map(cartDto, Cart.class);
//        this.cartRepository.save(cart);
//    }
//
//    @Override
//    public void update(CartDTO cartDto) {
//        Cart cart = this.modelMapper.map(cartDto, Cart.class);
//        this.cartRepository.save(cart);
//    }
//
//    @Override
//    public void delete(int id) {
//        this.cartRepository.deleteById(id);
//    }
//
//    @Override
//    public void deleteAll(List<CartDTO> cartDtoList) {
//        List<Cart> carts = cartDtoList.stream()
//                .map(cartDto -> this.modelMapper.map(cartDtoList, Cart.class))
//                .collect(Collectors.toList());
//        this.cartRepository.deleteAll(carts);

//    }

//    @Override
//    public List<ShoppingCart> getOrderByUsername(String username) {
//        User user = userRepository.findByEmail(username);
//        return shoppingCartRepository.findByUser(user);

//    }

    //    @Override
//    public List<ShoppingCartDTO> getShoppingCartByEmail(String username) {
//        User user = userRepository.findByEmail(username);
//        return shoppingCartRepository.findByUser(user);

//    }
//    @Override
//    public CartDTO getShoppingCartByEmail(User user) {
//        Cart cart = cartRepository.findByUser(user)
//                .orElseThrow(() -> new NoSuchElementException("Shopping cart not found for username: " + user.getEmail()));
//        return modelMapper.map(cart, CartDTO.class);
//    }
//
//    @Override
//    public List<CartItemDTO> getAllCartItemsByUser(String email) {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            // Handle user not found
//            return Collections.emptyList();
//        }
//
//        Cart cart = user.getCart();
//        if (cart == null) {
//            // Handle cart not found
//            return Collections.emptyList();
//        }
//
//        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
//        if (cartItems.isEmpty()) {
//            // Handle no cart items found
//            return Collections.emptyList();
//        }
//
//        return cartItems.stream()
//                .map(cartItem -> {
//                    CartItemDTO cartItemDTO = modelMapper.map(cartItem, CartItemDTO.class);
//                    cartItemDTO.setProductId(cartItem.getProduct().getId());
//                    return cartItemDTO;
//                })
//                .collect(Collectors.toList());
//    }
//
//
//    public double calculateTotalPrice(List<CartItemDTO> cartItems) {
//        double totalPrice = 0.0;
//
//        for (CartItemDTO cartItem : cartItems) {
//            Integer productId = cartItem.getProductId();
//            ProductDTO product = productService.getById(productId);
//
//            if (product != null) {
//                double itemPrice = product.getPrice();
//                int quantity = product.getQuantity();
//                double subtotal = itemPrice * quantity;
//                totalPrice += subtotal;
//            }
//        }
//
//        return totalPrice;

//    }

}
