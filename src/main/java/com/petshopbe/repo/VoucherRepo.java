package com.petshopbe.repo;

import com.petshopbe.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface VoucherRepo extends JpaRepository<Voucher, Integer> {

    @Query("select  v from Voucher v where v.voucherCode like :vc")
    Page<Voucher> searchByVoucherCode(@Param("vc") String voucherCode, Pageable pageable);

    @Query("select v from Voucher  v where v.createdAt = :ca")
    Page<Voucher> searchByCreatedAt(@Param("ca") Date createdAt, Pageable pageable);

    @Query("select v from Voucher v where v.voucherDate = :vd")
    Page<Voucher> searchByVoucherDate(@Param("vd") Date voucherDate, Pageable pageable);
}
