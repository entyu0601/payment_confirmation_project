package com.example.payment_confirmation_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment_confirmation_project.entity.Personal;

@Repository
public interface PersonalDao extends JpaRepository<Personal, String> {

}
