package com.portfolio.sns.repository;

import com.portfolio.sns.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Auth repository.
 */
public interface AuthRepository extends JpaRepository<User, Integer> {
    /**
     * @param username
     * @return optional
     * @methodName : findByUsername
     * @author : rulethecode9060
     * @date : 2023.07.13
     * @description : 회원 ID로 회원 조회
     */
    Optional<User> findByUsername(String username);
}
