package com.example.rentaldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentaldemo.enums.ToolType;
import com.example.rentaldemo.model.ToolCharge;

public interface ToolChargeRepository extends JpaRepository<ToolCharge, ToolType> {
    ToolCharge findByType(ToolType type);
}
