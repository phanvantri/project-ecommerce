package com.example.ecommerce.repository;

import com.example.ecommerce.domain.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {
}
