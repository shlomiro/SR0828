package com.example.rentaldemo.controller;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.rentaldemo.dto.RentalAgreementDTO;
import com.example.rentaldemo.dto.RentalAgreementRequestDTO;
import com.example.rentaldemo.model.RentalAgreement;
import com.example.rentaldemo.service.IRentalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    @PostMapping("rental")
    public RentalAgreementDTO generate(@Valid @RequestBody RentalAgreementRequestDTO request) {
        RentalAgreement r = rentalService.generateRentalAgreement(request.getToolCode(), request.getRentalDays(), request.getDiscountPercent(), request.getCheckoutDate());
        return new RentalAgreementDTO(r);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
