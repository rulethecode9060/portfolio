package com.portfolio.sns.repository;

import com.portfolio.sns.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Integer> {

}
