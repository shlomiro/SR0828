package com.example.rentaldemo.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.DayOfWeek;

@Service
public class HolidayService implements IHolidayService {
    public boolean isHoliday(LocalDate date) {
        LocalDate fourthOfJuly = LocalDate.of(date.getYear(), 7, 4);
        if (fourthOfJuly.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return date == fourthOfJuly.minusDays(1);
        } else if (fourthOfJuly.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return date == fourthOfJuly.plusDays(1);
        }

        LocalDate laborDay = LocalDate.of(date.getYear(), 9, 1);
        if (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay.plusDays(8 - laborDay.getDayOfWeek().getValue());
        }
        return date == laborDay;
    }
}
