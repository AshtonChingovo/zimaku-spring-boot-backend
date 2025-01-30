package com.zimaku.zimaku.domain.user.repository;

import com.zimaku.zimaku.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID>, JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
