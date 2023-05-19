package com.cg.repository;

import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Customer;
import com.cg.model.Product;
import com.cg.model.dto.CartItemResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT NEW com.cg.model.dto.CartItemResDTO (" +
                "ci.id, " +
                "ci.product.title, " +
                "ci.price, " +
                "ci.quantity, " +
                "ci.amount" +
            ") FROM CartItem AS ci " +
            "WHERE ci.cart = :cart"
    )
    List<CartItemResDTO> findAllCartItemResDTOByCart(@Param("cart") Cart cart);


    Optional<CartItem> findByCartAndProduct(@Param("cart") Cart cart, @Param("product") Product product);


    @Query("SELECT ci " +
            "FROM CartItem AS ci " +
            "JOIN Cart AS cart " +
            "ON ci.cart = cart " +
            "AND ci.cart = :cart " +
            "AND ci.cart.customer = :customer"
    )
    List<CartItem> findAllByCartAndCustomer(@Param("cart") Cart cart, @Param("customer") Customer customer);


    @Query("SELECT SUM(ci.amount) FROM CartItem AS ci WHERE ci.cart = :cart")
    BigDecimal getSumAmount(@Param("cart") Cart cart);
}
