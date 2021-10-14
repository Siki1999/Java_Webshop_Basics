package com.example.zadatak.Zadatak.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    public ResponseEntity<Optional<ProductModel>> getProductById(@PathVariable Long id){
        Optional<ProductModel> model = productService.getProductByID(id);
        if (model.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(productService.getProductByID(id), HttpStatus.OK);
        }
    }

    @GetMapping()
    public List<ProductModel> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductModel newProduct){
        Optional<ProductModel> oldProduct = productService.getProductByID(id);

        if(oldProduct.isPresent()){
            ProductModel product = oldProduct.get();
            product.setCode(newProduct.getCode());
            product.setDescription(newProduct.getDescription());
            product.setName(newProduct.getName());
            product.setPrice_hrk(newProduct.getPrice_hrk());
            product.setIs_available(newProduct.isIs_available());
            try {
                return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<ProductModel> addProduct(@RequestBody @Valid ProductModel model){
        try {
            long code = Long.parseLong(model.getCode());
            return new ResponseEntity<>(productService.addProduct(model), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
