package com.portfolio.sns.repository;

import com.portfolio.sns.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
