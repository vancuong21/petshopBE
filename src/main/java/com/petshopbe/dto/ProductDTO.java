package com.petshopbe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ProductDTO {
    private Integer id;

    private CategoryDTO category;
    private String productCode; // ma san pham
    @NotBlank
    private String name;
    private String imageProduct; // save url
    private String description;

    @Min(0)
    private double price;
    @JsonIgnore
    @Transient // field is not persistent
    private MultipartFile file;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date lastUpdateAt;
}
