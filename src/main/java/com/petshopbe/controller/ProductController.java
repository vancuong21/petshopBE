package com.petshopbe.controller;

import com.petshopbe.dto.PageDTO;
import com.petshopbe.dto.ProductDTO;
import com.petshopbe.dto.ResponseDTO;
import com.petshopbe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<ProductDTO> add(@RequestBody @Valid ProductDTO productDTO) {
        productService.create(productDTO);
        return ResponseDTO.<ProductDTO>builder().status(200).data(productDTO).build();

    }

    @PutMapping("/")
    public ResponseDTO<Void> update(@RequestBody @Valid ProductDTO productDTO) {
        productService.update(productDTO);
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/{id}") // /10
    public ResponseDTO<ProductDTO> get(@PathVariable("id") int id) {
        ProductDTO productDTO = productService.getById(id);
        return ResponseDTO.<ProductDTO>builder().status(200).data(productDTO).build();
    }

    @DeleteMapping("/{id}") // /1
    public ResponseDTO<Void> deleteByUserId(@PathVariable("id") int id) {
        productService.delete(id);
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/search")
    public ResponseDTO<PageDTO<ProductDTO>> search(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "productCode", required = false) String productCode,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page
    ) {

        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        name = name == null ? "" : name;
        productCode = productCode == null ? "" : productCode;

        PageDTO<ProductDTO> pageRS = productService.search(name, productCode, page, size);

        return ResponseDTO.<PageDTO<ProductDTO>>builder().status(200).data(pageRS).build();
    }
}
