package com.gb.hw_lesson12_finalproject.controller;

import com.gb.hw_lesson12_finalproject.dto.ProductCartInput;
import com.gb.hw_lesson12_finalproject.dto.ProductInput;
import com.gb.hw_lesson12_finalproject.entities.CartProduct;
import com.gb.hw_lesson12_finalproject.entities.Product;
import com.gb.hw_lesson12_finalproject.exeption.ProductNotFoundException;
import com.gb.hw_lesson12_finalproject.repo.CartRepository;
import com.gb.hw_lesson12_finalproject.repo.ProductRepo;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartRepository cartRepository;
    private final ProductRepo productRepo;

    public CartController(CartRepository cartRepository, ProductRepo productRepo) {
        this.cartRepository = cartRepository;
        this.productRepo = productRepo;
    }

    @GetMapping("/")
    public String findAllProductInCart(Model model){
        model.addAttribute("productincart", cartRepository.findAll());
        System.out.println("In Cart contains:  " + cartRepository.findAll());
        return"cart";
    }

    @GetMapping("/{id}")
    public String findProductByIdInCart(@PathVariable("id") Long id, Model model){
         model.addAttribute("products", cartRepository.findById(id).orElseThrow(()
                -> new ProductNotFoundException(String.format("Not found Product by this id %s", id))));
        return "redirect:/cart/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductByIdInCart(@PathVariable("id") Long id){
        System.out.println("Del cart: " + cartRepository.findById(id));
        cartRepository.deleteById(id);
        return "redirect:/cart/";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id, Model model) {
        Optional<Product> productOpt = productRepo.findById(id);
        if (productOpt.isEmpty()) {
            throw new NoSuchElementException("Product_id not found");
        } else {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setProduct(productOpt.get());
            model.addAttribute("productincart", cartRepository.save(cartProduct));
            return "redirect:/";
        }
    }
}
