package com.petshopbe.controller;

import com.petshopbe.dto.PageDTO;
import com.petshopbe.dto.ResponseDTO;
import com.petshopbe.dto.UserRoleDTO;
import com.petshopbe.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userrole")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<UserRoleDTO> add(@RequestBody @Valid UserRoleDTO userRoleDTO) {
        userRoleService.create(userRoleDTO);
        return ResponseDTO.<UserRoleDTO>builder().status(200).data(userRoleDTO).build();

    }

    @PutMapping("/")
    public ResponseDTO<Void> update(@RequestBody @Valid UserRoleDTO userRoleDTO) {
        userRoleService.update(userRoleDTO);
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/{id}") // /10
    public ResponseDTO<UserRoleDTO> get(@PathVariable("id") int id) {
        UserRoleDTO userRoleDTO = userRoleService.getById(id);
        return ResponseDTO.<UserRoleDTO>builder().status(200).data(userRoleDTO).build();
    }

    @DeleteMapping("/{id}") // /1
    public ResponseDTO<Void> deleteByUserId(@PathVariable("userId") int userId) {
        userRoleService.deleteByUserId(userId);
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/search")
    public ResponseDTO<PageDTO<UserRoleDTO>> search(
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "role", required = false) String role,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page
    ) {

        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        role = role == null ? "" : role;

        PageDTO<UserRoleDTO> pageRS = userRoleService.search(userId, role, size, page);

        return ResponseDTO.<PageDTO<UserRoleDTO>>builder().status(200).data(pageRS).build();
    }
}
