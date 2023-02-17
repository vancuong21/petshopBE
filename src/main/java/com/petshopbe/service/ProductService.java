package com.petshopbe.service;

import com.petshopbe.dto.PageDTO;
import com.petshopbe.dto.ProductDTO;
import com.petshopbe.entity.Category;
import com.petshopbe.entity.Product;
import com.petshopbe.repo.CategoryRepo;
import com.petshopbe.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Transactional
    public void create(ProductDTO productDTO) {
        Category category = categoryRepo.findById(productDTO.getCategory().getId()).orElseThrow(NoResultException::new);

        Product product = new ModelMapper().map(productDTO, Product.class);
        productRepo.save(product);

        // neu frontend can lay id thi
        productDTO.setId(product.getId());
    }

    @Transactional
    public void update(ProductDTO productDTO) {
        Product product = productRepo.findById(productDTO.getId()).orElseThrow(NoResultException::new);

        product.setProductCode(productDTO.getProductCode());
        product.setName(productDTO.getName());
        if (product.getImageProduct() != null) {
            product.setImageProduct(productDTO.getImageProduct());
        }
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        Category category = categoryRepo.findById(productDTO.getCategory().getId()).orElseThrow(NoResultException::new);
        product.setCategory(category);

        productRepo.save(product);
    }

    @Transactional
    public void delete(int id) {
        productRepo.deleteById(id);
    }


    public PageDTO<ProductDTO> search(String name, String productCode, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> pageRS = null;
        if (name != null)
            pageRS = productRepo.searchByName(name, pageable);
        else
            pageRS = productRepo.searchByProductCode(productCode, pageable);

        PageDTO<ProductDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(pageRS.getTotalPages());
        pageDTO.setTotalElements(pageRS.getTotalElements());
        List<ProductDTO> productDTOs = pageRS.get()
                .map(product -> new ModelMapper().map(product, ProductDTO.class))
                .collect(Collectors.toList());
        pageDTO.setContents(productDTOs);

        return pageDTO;
    }


    public ProductDTO getById(int id) {
        Product product = productRepo.findById(id).orElseThrow(NoResultException::new);
        return new ModelMapper().map(product, ProductDTO.class);
    }

}
