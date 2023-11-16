package com.bancolombia.TalentoB.services;

import com.bancolombia.TalentoB.entities.Product;
import com.bancolombia.TalentoB.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;

    public Product Save (Product newProduct){
        return this.productRepository.save(newProduct);
    }

    public List<Product> GetAll (){
        return this.productRepository.findAll();
    }

    public Optional<Product> GetById ( Long id ){
        return this.productRepository.findById(id);
    }

    public Optional<Product> Update (Long id, Product product){
        if(this.productRepository.findById(id).isPresent()){
            product.setId(id);
            this.productRepository.save(product);
        }
        return null;
    }
    public void Delete (Product product){
        this.productRepository.delete(product);
    }
 }
