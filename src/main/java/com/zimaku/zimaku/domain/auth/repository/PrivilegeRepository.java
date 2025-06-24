package com.zimaku.zimaku.domain.user.repository;

import com.zimaku.zimaku.domain.user.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
