package com.petshopbe.repo;

import com.petshopbe.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {
    @Modifying
    @Query("delete from UserRole ur where ur.user.id = :uid")
    public void deleteByUserId(@Param("uid") int userId);

    @Query("select ur from UserRole ur join ur.user u where u.id = :uid")
    Page<UserRole> searchByUserId(@Param("uid") int userId, Pageable pageable);

    @Query("select ur from UserRole ur where ur.role like :role")
    Page<UserRole> searchByRole(@Param("role") String role, Pageable pageable);
}
