package com.petshopbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String voucherCode; // ma voucher
    private double voucherAmount; // so tien giam
    private Date voucherDate; // ngay het han
    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;
    @LastModifiedDate
    private Date lastUpdateAt;

}
