package com.petshopbe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
public class VoucherDTO {
    private Integer id;
    private String voucherCode; // ma voucher
    @Min(0)
    private double voucherAmount; // so tien giam
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date voucherDate; // ngay het han
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date lastUpdateAt;
}
