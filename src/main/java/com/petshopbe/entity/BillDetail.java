package com.petshopbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstNameCustomer; // ho
    private String lastNameCustomer; // ten
    private String address;
    private String phoneNumber;
    private String note;
    @OneToOne
    //   @PrimaryKeyJoinColumn // BillDetail_id === Cart_id
    private Cart cart;
    @CreatedDate
    private Date createdAt;
}
