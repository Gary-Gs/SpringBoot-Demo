package com.codetest.springbootapi.entity;

import com.codetest.springbootapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
