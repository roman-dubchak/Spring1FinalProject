package com.gb.hw_lesson12_finalproject.controller;

import com.gb.hw_lesson12_finalproject.dto.ErrorDto;
import com.gb.hw_lesson12_finalproject.dto.ProductInput;
import com.gb.hw_lesson12_finalproject.entities.Product;
import com.gb.hw_lesson12_finalproject.exeption.PersonNotFoundException;
import com.gb.hw_lesson12_finalproject.exeption.ProductNotFoundException;
import com.gb.hw_lesson12_finalproject.repo.ProductRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    private final ProductRepo productRepo;

    public ProductController(ProductRepo repo) {
        this.productRepo = repo;
    }

    @GetMapping("/products/{id}")
    public String findProductById(Model model, @PathVariable("id") Long id){
        model.addAttribute("selectProduct", productRepo.findById(id).orElseThrow(()
                -> new ProductNotFoundException(String.format("Not found Product by this id %s", id))));
        System.out.println("Find by " + id + " is product: " + productRepo.findById(id));
        return "products";
    }

    @GetMapping("/")
    public String findAllProduct(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("products", productRepo.findAll(PageRequest.of(page, 4)));
        System.out.println("Find by all product: " + productRepo.findAll());
        model.addAttribute("currentPage", page);
        return "index";
    }

    @GetMapping("/products/delete/{id}")
//    @PreAuthorize("ROLE_ADMIN")
    public String deleteProductById(Model model, @PathVariable("id") Long id){
        productRepo.deleteById(id);
        System.out.println("Delete product by id: " + id);
        return "redirect:/index";
    }

    @GetMapping("/form")
    public String form(ProductInput productInput, Model model){
        model.addAttribute("product", productInput);
        return "form";
    }

    @PostMapping(value = "/form", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String form(ProductInput productInput){
        Product product = new Product();
        product.setPrice(productInput.getPrice());
        product.setTitle(productInput.getTitle());
        productRepo.save(product);
        System.out.println("POST save product - " + product);
        return "redirect:/";
    }

    @GetMapping("/max/{price}")
    public String findByGreaterPrice(@PathVariable("price") Integer price, Model model) {
//        List<Optional> productRepoByPriceGreaterThan = (Optional) productRepo.findByPriceGreaterThan(price).a();
        model.addAttribute("products", productRepo.findByPriceGreaterThan(price));
        System.out.println("Find by max product: " + productRepo.findByPriceGreaterThan(price));
        return "shop";
    }

    @GetMapping("/min/{price}")
    public String findByLessPrice(@PathVariable("price") Integer price, Model model) {
        model.addAttribute("products", productRepo.findByPriceLessThan(price));
        System.out.println("Find by min product: " + productRepo.findByPriceLessThan(price));
        return "shop";
    }

    @GetMapping("/product/{title}")
    public String findByLessPrice(@PathVariable("title") String title, Model model) {
        model.addAttribute("products", productRepo.findByTitleContains(title));
        System.out.println("Find by title product: " + productRepo.findByTitleContains(title));
        return "shop";
    }

    @ExceptionHandler
    public ErrorDto errorDto(ProductNotFoundException e){
        return new ErrorDto(e.getMessage());
    }

    //
//    @PostMapping("/save") // for Rest
//    public String saveProduct(Product product){
//        productRepo.save(product);
//        System.out.println("Delete product by id: " + product.getId());
//        return "redirect:/index";
//    }

}
