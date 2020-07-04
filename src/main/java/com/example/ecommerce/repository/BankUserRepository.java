package com.example.ecommerce.repository;

import com.example.ecommerce.domain.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {
    @Query("select  e from BankUser  e where e.user.id = ?1")
    BankUser getBankUserByUserBank(Long id);
}
