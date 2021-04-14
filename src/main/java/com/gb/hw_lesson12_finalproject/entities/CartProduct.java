package com.gb.hw_lesson12_finalproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "cart_product")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public CartProduct(Long id, Product productId) {
        this.id = id;
        this.product = productId;
    }

    public CartProduct() {
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("CartProduct id %s, product %s", id, product);
    }
}
