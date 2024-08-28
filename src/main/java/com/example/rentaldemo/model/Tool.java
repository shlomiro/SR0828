package com.example.rentaldemo.model;

import com.example.rentaldemo.enums.ToolType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tools")
@Data
@Builder
public class Tool {
    @Id
    private String code;
    @Column(name = "tool_type")
    private ToolType type;
    private String brand;
}
