package com.example.rentaldemo.service;

import java.time.LocalDate;

public interface IHolidayService {
    boolean isHoliday(LocalDate date);
}
