package com.petshopbe.service;

import com.petshopbe.dto.PageDTO;
import com.petshopbe.dto.VoucherDTO;
import com.petshopbe.entity.Voucher;
import com.petshopbe.repo.VoucherRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherService {
    @Autowired
    VoucherRepo voucherRepo;

    @Transactional
    public void create(VoucherDTO voucherDTO) {
        Voucher voucher = new ModelMapper().map(voucherDTO, Voucher.class);
        voucherRepo.save(voucher);

        // neu frontend can lay id thi
        voucherDTO.setId(voucher.getId());
    }

    @Transactional
    public void update(VoucherDTO voucherDTO) {
        Voucher voucher = voucherRepo.findById(voucherDTO.getId()).orElseThrow(NoResultException::new);
        voucher.setVoucherCode(voucherDTO.getVoucherCode());
        voucher.setVoucherAmount(voucherDTO.getVoucherAmount());
        voucher.setVoucherDate(voucherDTO.getVoucherDate());

        voucherRepo.save(voucher);
    }

    @Transactional
    public void delete(int id) {
        voucherRepo.deleteById(id);
    }


    public PageDTO<VoucherDTO> search(String voucherCode, Date createdAt, Date voucherDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Voucher> pageRS = null;
        if (StringUtils.hasText(voucherCode))
            pageRS = voucherRepo.searchByVoucherCode(voucherCode, pageable);
        else if (createdAt != null)
            pageRS = voucherRepo.searchByCreatedAt(createdAt, pageable);
        else if (voucherDate != null)
            pageRS = voucherRepo.searchByVoucherDate(voucherDate, pageable);
        else
            pageRS = voucherRepo.findAll(pageable);

        PageDTO<VoucherDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(pageRS.getTotalPages());
        pageDTO.setTotalElements(pageRS.getTotalElements());
        List<VoucherDTO> voucherDTOs = pageRS.get()
                .map(voucher -> new ModelMapper().map(voucher, VoucherDTO.class))
                .collect(Collectors.toList());
        pageDTO.setContents(voucherDTOs);

        return pageDTO;
    }


    public VoucherDTO getById(int id) {
        Voucher voucher = voucherRepo.findById(id).orElseThrow(NoResultException::new);
        return new ModelMapper().map(voucher, VoucherDTO.class);
    }
}
