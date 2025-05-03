package org.temirjohn.temirjhon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {
}

