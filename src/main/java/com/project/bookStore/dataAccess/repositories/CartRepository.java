package com.project.bookStore.dataAccess.repositories;

import com.project.bookStore.dataAccess.entities.Cart;
import com.project.bookStore.dataAccess.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);

}
