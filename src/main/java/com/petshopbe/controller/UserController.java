package com.petshopbe.controller;

import com.petshopbe.dto.PageDTO;
import com.petshopbe.dto.ResponseDTO;
import com.petshopbe.dto.UserDTO;
import com.petshopbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<UserDTO> add(@RequestBody @Valid UserDTO userDTO) {
        userService.create(userDTO);
        return ResponseDTO.<UserDTO>builder().status(200).data(userDTO).build();

    }

    @PutMapping("/")
    public ResponseDTO<Void> update(@RequestBody @Valid UserDTO userDTO) {
        userService.update(userDTO);
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/{id}") // /10
    public ResponseDTO<UserDTO> get(@PathVariable("id") int id) {
        UserDTO userDTO = userService.getById(id);
        return ResponseDTO.<UserDTO>builder().status(200).data(userDTO).build();
    }

    @DeleteMapping("/{id}") // /1
    public ResponseDTO<Void> delete(@PathVariable("id") int id) {
        userService.delete(id);
        return ResponseDTO.<Void>builder().status(200).build();
    }

    @GetMapping("/search")
    public ResponseDTO<PageDTO<UserDTO>> search(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page
    ) {

        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        username = username == null ? "" : username;

        PageDTO<UserDTO> pageRS =
                userService.search("%" + username + "%", page, size);

        return ResponseDTO.<PageDTO<UserDTO>>builder().status(200).data(pageRS).build();
    }

}
