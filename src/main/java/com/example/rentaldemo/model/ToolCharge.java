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
@Table(name = "tool_charges")
@Data
@Builder
public class ToolCharge {
    @Id
    @Column(name = "tool_type")
    private ToolType type;
    private double daily;
    @Column(name = "weekday_charge")
    private boolean weekday;
    private boolean weekend;
    private boolean holiday;
}
