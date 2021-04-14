package com.gb.hw_lesson12_finalproject.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductCartInput {
    @NotNull
    private Long product_id;

       /* dd.MM.yyyy, MM.dd.yyyy
    @NotNull,
    @Size
    @Min
    @Max
     */

//    @AssertTrue (message = "product_id должно быть больше 0")
//    public boolean isTest() {
//        return product_id > 0;
//    }
    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public ProductCartInput(Long product_id) {
        this.product_id = product_id;
    }

    public ProductCartInput() {
    }
}
