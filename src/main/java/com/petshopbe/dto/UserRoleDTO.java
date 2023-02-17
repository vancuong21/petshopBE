package com.petshopbe.dto;

import lombok.Data;

@Data
public class UserRoleDTO {
    private Integer id;
//    @JsonBackReference
//    private UserDTO user;

    private Integer userId;
    private String userName;
    private String role;
}
