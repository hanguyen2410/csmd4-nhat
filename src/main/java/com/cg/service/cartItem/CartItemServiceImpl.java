package com.cg.service.cartItem;

import com.cg.exception.DataInputException;
import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Customer;
import com.cg.model.Product;
import com.cg.model.dto.CartItemReqDTO;
import com.cg.model.dto.CartItemResDTO;
import com.cg.model.dto.CartResDTO;
import com.cg.repository.CartItemRepository;
import com.cg.repository.CartRepository;
import com.cg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartItemServiceImpl implements ICartItemService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CartItem> findAllByCartAndCustomer(Cart cart, Customer customer) {
        return cartItemRepository.findAllByCartAndCustomer(cart, customer);
    }

    @Override
    public List<CartItemResDTO> findAllCartItemResDTOByCart(Cart cart) {
        return cartItemRepository.findAllCartItemResDTOByCart(cart);
    }

    @Override
    public CartResDTO create(CartItemReqDTO cartItemReqDTO, Customer customer) {

        Product product = productRepository.findById(Long.parseLong(cartItemReqDTO.getProductId())).orElseThrow(() -> {
            throw new DataInputException("Sản phẩm không hợp lệ");
        });

        BigDecimal productPrice = product.getPrice();
        int quantity = Integer.parseInt(cartItemReqDTO.getQuantity());
        BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(quantity));

        Cart cart = new Cart();
        cart.setTotalAmount(productAmount);
        cart.setCustomer(customer);
        cartRepository.save(cart);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setProductTitle(product.getTitle());
        cartItem.setPrice(productPrice);
        cartItem.setQuantity(quantity);
        cartItem.setAmount(productAmount);
        cartItemRepository.save(cartItem);

        List<CartItemResDTO> cartItemResDTOS = cartItemRepository.findAllCartItemResDTOByCart(cart);

        CartResDTO cartResDTO = new CartResDTO();
        cartResDTO.setTotalAmount(productAmount);
        cartResDTO.setFullName(customer.getFullName());
        cartResDTO.setItems(cartItemResDTOS);

        return cartResDTO;
    }

    @Override
    public CartResDTO update(CartItemReqDTO cartItemReqDTO, Customer customer, Cart cart) {
        Product product = productRepository.findById(Long.parseLong(cartItemReqDTO.getProductId())).orElseThrow(() -> {
            throw new DataInputException("Sản phẩm không hợp lệ");
        });

        Optional<CartItem> cartItemOptional = cartItemRepository.findByCartAndProduct(cart, product);

        if (cartItemOptional.isEmpty()) {
            BigDecimal productPrice = product.getPrice();
            int quantity = Integer.parseInt(cartItemReqDTO.getQuantity());
            BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(quantity));

            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setProductTitle(product.getTitle());
            cartItem.setPrice(productPrice);
            cartItem.setQuantity(quantity);
            cartItem.setAmount(productAmount);
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = cartItemOptional.get();

            BigDecimal productPrice = product.getPrice();
            Integer currentQuantity = cartItem.getQuantity();
            int newQuantity = currentQuantity + Integer.parseInt(cartItemReqDTO.getQuantity());
            BigDecimal newAmount = productPrice.multiply(BigDecimal.valueOf(newQuantity));

            cartItem.setPrice(productPrice);
            cartItem.setQuantity(newQuantity);
            cartItem.setAmount(newAmount);
            cartItemRepository.save(cartItem);
        }

        List<CartItemResDTO> cartItemResDTOS = cartItemRepository.findAllCartItemResDTOByCart(cart);

        BigDecimal totalAmount = cartItemRepository.getSumAmount(cart);

        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

        CartResDTO cartResDTO = new CartResDTO();
        cartResDTO.setTotalAmount(totalAmount);
        cartResDTO.setFullName(customer.getFullName());
        cartResDTO.setItems(cartItemResDTOS);

        return cartResDTO;
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return null;
    }

    @Override
    public void delete(CartItem cartItem) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
