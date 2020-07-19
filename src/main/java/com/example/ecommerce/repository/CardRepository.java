package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, String> {
}
