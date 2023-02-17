package com.petshopbe.repo;

import com.petshopbe.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username like :x")
    Page<User> searchByUsername(@Param("x") String s, Pageable pageable);
}
