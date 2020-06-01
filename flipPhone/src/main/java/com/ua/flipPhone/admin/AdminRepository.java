package com.ua.flipPhone.admin;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByEmail(String email);
}