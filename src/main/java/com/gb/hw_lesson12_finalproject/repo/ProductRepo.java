package com.gb.hw_lesson12_finalproject.repo;

import com.gb.hw_lesson12_finalproject.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

//    default Page<Product> findPaginated(int pageNo, int pageSize){
//        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
//        return this.findAll(pageable);
//    };

    List<Product> findByPriceGreaterThan (Integer maxPrice);
    List<Product> findByPriceLessThan (Integer price);
    List<Product> findByTitleContains(String title);
}
