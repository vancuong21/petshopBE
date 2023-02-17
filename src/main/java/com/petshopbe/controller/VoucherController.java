package com.petshopbe.controller;

import com.petshopbe.dto.PageDTO;
import com.petshopbe.dto.ResponseDTO;
import com.petshopbe.dto.VoucherDTO;
import com.petshopbe.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<VoucherDTO> add(@RequestBody @Valid VoucherDTO voucherDTO) {
        voucherService.create(voucherDTO);
        return ResponseDTO.<VoucherDTO>builder().status(200).data(voucherDTO).build();

    }

    @PutMapping("/")
    public ResponseDTO<Void> update(@RequestBody @Valid VoucherDTO voucherDTO) {
        voucherService.update(voucherDTO);
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/{id}") // /10
    public ResponseDTO<VoucherDTO> get(@PathVariable("id") int id) {
        VoucherDTO voucherDTO = voucherService.getById(id);
        return ResponseDTO.<VoucherDTO>builder().status(200).data(voucherDTO).build();
    }

    @DeleteMapping("/{id}") // /1
    public ResponseDTO<Void> deleteByUserId(@PathVariable("id") int id) {
        voucherService.delete(id);
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/search")
    public ResponseDTO<PageDTO<VoucherDTO>> search(
            @RequestParam(name = "voucherCode", required = false) String voucherCode,
            @RequestParam(name = "createdAt", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date createdAt,
            @RequestParam(name = "voucherDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date voucherDate,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page
    ) {

        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        voucherCode = voucherCode == null ? "" : voucherCode;


        PageDTO<VoucherDTO> pageRS = voucherService.search(voucherCode, createdAt, voucherDate, page, size);

        return ResponseDTO.<PageDTO<VoucherDTO>>builder().status(200).data(pageRS).build();
    }
}
