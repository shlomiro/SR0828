package com.example.rentaldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentaldemo.model.Tool;

public interface ToolRepository extends JpaRepository<Tool, String> {
    Tool findByCode(String code);
}
