package com.petshopbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;
    @ManyToOne
    private Product product;
    private int quantity;
    private double tempTotal;
    @OneToOne
    private Voucher voucher;
    private double total;


}
