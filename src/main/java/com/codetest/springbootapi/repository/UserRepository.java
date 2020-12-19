package com.codetest.springbootapi.repository;

import com.codetest.springbootapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
