package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.exception.UnauthorizedException;
import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Customer;
import com.cg.model.User;
import com.cg.model.dto.CartItemReqDTO;
import com.cg.model.dto.CartItemResDTO;
import com.cg.model.dto.CartResDTO;
import com.cg.service.cart.ICartService;
import com.cg.service.cartItem.ICartItemService;
import com.cg.service.customer.ICustomerService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;


    @GetMapping
    public ResponseEntity<?> getAllItems() {
        String username = appUtils.getPrincipalUsername();

        User user = userService.findByUsername(username).orElseThrow(() -> {
            throw new UnauthorizedException("Bạn chưa xác thực");
        });

        Customer customer = customerService.findByUser(user).orElseThrow(() -> {
            throw new UnauthorizedException("Bạn chưa xác thực");
        });

        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        if (cartOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CartItem> cartItems = cartItemService.findAllByCartAndCustomer(cartOptional.get(), customer);

        List<CartItemResDTO> cartItemResDTOS = cartItemService.findAllCartItemResDTOByCart(cartOptional.get());

        if (cartItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        CartResDTO cartResDTO = new CartResDTO();
        cartResDTO.setItems(cartItemResDTOS);
        cartResDTO.setTotalAmount(cartOptional.get().getTotalAmount());

        return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
    }

    @PostMapping("/add-item")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<?> addItem(@RequestBody CartItemReqDTO cartItemReqDTO, BindingResult bindingResult) {

        new CartItemReqDTO().validate(cartItemReqDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        String username = appUtils.getPrincipalUsername();

        User user = userService.findByUsername(username).orElseThrow(() -> {
           throw new UnauthorizedException("Bạn chưa xác thực");
        });

        Customer customer = customerService.findByUser(user).orElseThrow(() -> {
            throw new UnauthorizedException("Bạn chưa xác thực");
        });

        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        if (cartOptional.isEmpty()) {
            CartResDTO cartResDTO = cartItemService.create(cartItemReqDTO, customer);
            return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
        }
        else {
            CartResDTO cartResDTO = cartItemService.update(cartItemReqDTO, customer, cartOptional.get());
            return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
        }

    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay() {
        String username = appUtils.getPrincipalUsername();

        User user = userService.findByUsername(username).orElseThrow(() -> {
            throw new UnauthorizedException("Bạn chưa xác thực");
        });

        Customer customer = customerService.findByUser(user).orElseThrow(() -> {
            throw new UnauthorizedException("Bạn chưa xác thực");
        });

        Cart cart = cartService.findByCustomer(customer).orElseThrow(() -> {
           throw new DataInputException("Khách hàng chưa có giỏ hàng");
        });

        cartService.pay(cart, customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
