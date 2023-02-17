package com.petshopbe.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryDTO {
    private Integer id;
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;
}
