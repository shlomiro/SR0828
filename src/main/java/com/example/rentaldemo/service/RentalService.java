package com.example.rentaldemo.service;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentaldemo.model.RentalAgreement;
import com.example.rentaldemo.model.Tool;
import com.example.rentaldemo.model.ToolCharge;
import com.example.rentaldemo.repository.ToolChargeRepository;
import com.example.rentaldemo.repository.ToolRepository;

@Service
public class RentalService implements IRentalService {
    
    @Autowired
    private IHolidayService holidayService; 

    @Autowired
    private ToolRepository toolRepository;
    
    @Autowired
    private ToolChargeRepository toolChargeRepository;

    @Override
    public RentalAgreement generateRentalAgreement(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        Tool tool = toolRepository.findByCode(toolCode);
        ToolCharge toolCharge = toolChargeRepository.findByType(tool.getType());
        int chargeDays = calculateChargeDays(toolCharge, checkoutDate, rentalDays);
        double preDiscountCharge = toolCharge.getDaily() * chargeDays;
        double discountAmount = roundHalfUp(preDiscountCharge * discountPercent / 100);
        double finalCharge = roundHalfUp(preDiscountCharge - discountAmount);

        RentalAgreement rentalAgreement = RentalAgreement.builder()
                .tool(tool)
                .rentalDays(rentalDays)
                .checkoutDate(checkoutDate)
                .dueDate(checkoutDate.plusDays(rentalDays))
                .dailyRentalCharge(toolCharge.getDaily())
                .chargeDays(chargeDays)
                .preDiscountCharge(preDiscountCharge)
                .discountPercent(discountPercent)
                .discountAmount(discountAmount)
                .finalCharge(finalCharge)
                .build();


        return rentalAgreement;
    }

    private int calculateChargeDays(ToolCharge toolCharge, LocalDate checkoutDate, int rentalDays) {
        int chargeDays = 0;

        for (LocalDate date = checkoutDate; date.isBefore(checkoutDate.plusDays(rentalDays)); date = date.plusDays(1)) {
            if (holidayService.isHoliday(date)) {
                if (toolCharge.isHoliday()) {
                    chargeDays++;
                } else continue;
            } else if (isWeekend(date) && toolCharge.isWeekend()) {
                chargeDays++;
            } else if (!isWeekend(date) && toolCharge.isWeekday()) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private double roundHalfUp(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
