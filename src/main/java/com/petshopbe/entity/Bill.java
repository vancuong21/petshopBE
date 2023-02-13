package com.petshopbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
   // @PrimaryKeyJoinColumn // Bill_id === BillDetail_id
    private BillDetail billDetail; // total, createdAt
    private String status; // thanh toan, chua thanh toan
}
