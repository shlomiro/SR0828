package com.example.rentaldemo.service;

import java.time.LocalDate;

import com.example.rentaldemo.model.RentalAgreement;

public interface IRentalService {
    RentalAgreement generateRentalAgreement(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate);
}
