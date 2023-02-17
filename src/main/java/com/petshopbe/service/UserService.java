package com.petshopbe.service;

import com.petshopbe.dto.PageDTO;
import com.petshopbe.dto.UserDTO;
import com.petshopbe.entity.User;
import com.petshopbe.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Transactional
    public void create(UserDTO userDTO) {
        User user = new ModelMapper().map(userDTO, User.class);
        userRepo.save(user);

        // neu frontend can lay id thi
        userDTO.setId(user.getId());
    }

    @Transactional
    public void update(UserDTO userDTO) {
        User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        userRepo.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepo.deleteById(id);
    }


    public PageDTO<UserDTO> search(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> pageRS = userRepo.searchByUsername("%" + username + "%", pageable);

        PageDTO<UserDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(pageRS.getTotalPages());
        pageDTO.setTotalElements(pageRS.getTotalElements());
        List<UserDTO> userDTOs = pageRS.get()
                .map(user -> new ModelMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());
        pageDTO.setContents(userDTOs);

        return pageDTO;
    }


    public UserDTO getById(int id) {
        User user = userRepo.findById(id).orElseThrow(NoResultException::new);
        return new ModelMapper().map(user, UserDTO.class);
    }
}
