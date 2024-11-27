package com.zimaku.zimaku.domain.user.repository;

import com.zimaku.zimaku.domain.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Collection<Role> findByTitle(String roleUser);
}
