package com.example.rentaldemo.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalAgreementRequestDTO {
    @NotBlank
    private String toolCode;
    @Min(1)
    @NotNull
    private int rentalDays;
    @Min(0)
    @Max(100)
    @NotNull
    private int discountPercent;
    @JsonFormat(pattern = "MM/dd/yy")
    private LocalDate checkoutDate;
}
