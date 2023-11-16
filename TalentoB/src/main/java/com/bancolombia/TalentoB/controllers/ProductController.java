package com.bancolombia.TalentoB.controllers;

import com.bancolombia.TalentoB.entities.Product;
import com.bancolombia.TalentoB.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @PostMapping
    public ResponseEntity<Product> Save (@RequestBody Product newProduct){
        if(newProduct.equals(null))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.productServices.Save(newProduct);
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> GetById (@PathVariable(name = "id")Long id){
        Optional<Product> product = this.productServices.GetById(id);
        if(product.isPresent()){
            return ResponseEntity.ok().body(product.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Product>> GetALL (){
        List<Product> productList = this.productServices.GetAll();
        return ResponseEntity.ok().body(productList);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> Update (@PathVariable(name = "id")Long id, @RequestBody Product product){
        Optional<Product> updateProduct = this.productServices.GetById(id);
        if(updateProduct.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        this.productServices.Update(id,product);
        return ResponseEntity.ok().body( updateProduct.get() );

    }

    @DeleteMapping
    public void Delete (@RequestBody Product product){
        this.productServices.Delete(product);
    }


}
