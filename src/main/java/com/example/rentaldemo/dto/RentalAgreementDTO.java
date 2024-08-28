package com.example.rentaldemo.dto;

import com.example.rentaldemo.model.RentalAgreement;
import lombok.Data;
import java.time.format.DateTimeFormatter;

@Data
public class RentalAgreementDTO {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private String checkoutDate;
    private String dueDate;
    private String dailyRentalCharge;
    private int chargeDays;
    private String preDiscountCharge;
    private String discountPercent;
    private String discountAmount;
    private String finalCharge;

    public RentalAgreementDTO(RentalAgreement rentalAgreement) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        this.toolCode = rentalAgreement.getTool().getCode();
        this.toolType = rentalAgreement.getTool().getType().toString();
        this.toolBrand = rentalAgreement.getTool().getBrand();
        this.rentalDays = rentalAgreement.getRentalDays();
        this.checkoutDate = rentalAgreement.getCheckoutDate().format(formatter).toString();
        this.dueDate = rentalAgreement.getDueDate().format(formatter).toString();
        this.dailyRentalCharge = dollarAmount(rentalAgreement.getDailyRentalCharge());
        this.chargeDays = rentalAgreement.getChargeDays();
        this.preDiscountCharge = dollarAmount(rentalAgreement.getPreDiscountCharge());
        this.discountPercent = String.valueOf(rentalAgreement.getDiscountPercent()).concat("%");
        this.discountAmount = dollarAmount(rentalAgreement.getDiscountAmount());
        this.finalCharge = dollarAmount(rentalAgreement.getFinalCharge());
    }

    private String dollarAmount(double amount) {
        return String.format("$%.2f", amount);
    }
}
