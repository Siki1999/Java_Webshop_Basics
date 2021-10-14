package com.example.zadatak.Zadatak.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<ProductModel> getProductByID(long Id){
        return productRepository.findById(Id);
    }

    public ProductModel updateProduct(ProductModel product){
        return productRepository.save(product);
    }

    public void deleteProductById(long Id){
        productRepository.deleteById(Id);
    }

    public ProductModel addProduct(ProductModel Product){
        return productRepository.save(Product);
    }
}
