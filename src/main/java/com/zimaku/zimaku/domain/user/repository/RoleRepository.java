package com.zimaku.zimaku.domain.user.repository;

import com.zimaku.zimaku.domain.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Collection<Role> findByTitle(String roleUser);

    @Query("SELECT role FROM Role role WHERE title = :title")
    Optional<Role> findByTitleOneRole(@Param("title") String title);
}
