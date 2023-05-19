package com.cg.service.cart;

import com.cg.model.Cart;
import com.cg.model.Customer;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ICartService extends IGeneralService<Cart, Long> {

    Optional<Cart> findByCustomer(Customer customer);

    void pay(Cart cart, Customer customer);
}
