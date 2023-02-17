package com.petshopbe.service;

import com.petshopbe.dto.PageDTO;
import com.petshopbe.dto.UserRoleDTO;
import com.petshopbe.entity.User;
import com.petshopbe.entity.UserRole;
import com.petshopbe.repo.UserRepo;
import com.petshopbe.repo.UserRoleRepo;
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
public class UserRoleService {
    @Autowired
    UserRoleRepo userRoleRepo;
    @Autowired
    UserRepo userRepo;

    @Transactional
    public void create(UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        userRole.setRole(userRoleDTO.getRole());
        // tim user id trong db user de set user role
        User user = userRepo.findById(userRoleDTO.getUserId()).orElseThrow(NoResultException::new);
        userRole.setUser(user);

        userRoleRepo.save(userRole);
    }

    @Transactional
    public void update(UserRoleDTO userRoleDTO) {
        UserRole userRole = userRoleRepo.findById(userRoleDTO.getId()).orElseThrow(NoResultException::new);
        userRole.setRole(userRoleDTO.getRole());

        User user = userRepo.findById(userRoleDTO.getUserId()).orElseThrow(NoResultException::new);
        userRole.setUser(user);

        userRoleRepo.save(userRole);
    }

//    @Transactional
//    public void delete(int id) {
//        userRoleRepo.deleteById(id);
//    }

    @Transactional
    public void deleteByUserId(int userId) {
        userRoleRepo.deleteByUserId(userId);
    }


    public PageDTO<UserRoleDTO> search(Integer userId, String role, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<UserRole> pageRS = null;
        if (userId != null)
            pageRS = userRoleRepo.searchByUserId(userId, pageable);
        else
            pageRS = userRoleRepo.searchByRole(role, pageable);

        PageDTO<UserRoleDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(pageRS.getTotalPages());
        pageDTO.setTotalElements(pageRS.getTotalElements());
        List<UserRoleDTO> userRoleDTOs = pageRS.get()
                .map(userRole -> new ModelMapper().map(userRole, UserRoleDTO.class))
                .collect(Collectors.toList());
        pageDTO.setContents(userRoleDTOs);

        return pageDTO;
    }


    public UserRoleDTO getById(int id) {
        UserRole userRole = userRoleRepo.findById(id).orElseThrow(NoResultException::new);
        return new ModelMapper().map(userRole, UserRoleDTO.class);
    }
}
