package com.gb.hw_lesson12_finalproject.repo;

import com.gb.hw_lesson12_finalproject.entities.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartProduct, Long> {
}
