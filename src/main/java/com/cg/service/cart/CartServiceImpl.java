package com.cg.service.cart;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.repository.BillDetailRepository;
import com.cg.repository.BillRepository;
import com.cg.repository.CartItemRepository;
import com.cg.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cart> findByCustomer(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    @Override
    public void pay(Cart cart, Customer customer) {
        List<CartItem> cartItems = cartItemRepository.findAllByCartAndCustomer(cart, customer);

        if (cartItems.isEmpty()) {
            throw new DataInputException("Khách hàng chưa có sản phẩm trong giỏ hàng");
        }

        Bill bill = cart.toBill();
        bill.setId(null);
        billRepository.save(bill);

        for (CartItem cartItem : cartItems) {
            BillDetail billDetail = cartItem.toBillDetail(bill);
            billDetail.setId(null);
            billDetailRepository.save(billDetail);
            cartItemRepository.delete(cartItem);
        }

        cartRepository.delete(cart);
    }

    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public void delete(Cart cart) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
