package dev.zvaryyka.calculatorforneoflex.controllers;

import dev.zvaryyka.calculatorforneoflex.models.VacationPayRequest;
import dev.zvaryyka.calculatorforneoflex.models.VacationPayWithDatesRequest;
import dev.zvaryyka.calculatorforneoflex.services.VacationPayService;
import dev.zvaryyka.calculatorforneoflex.util.VacationPayErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

@RestController
public class CalculateController {

    private final VacationPayService vacationPayService;

    public CalculateController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> getVacationPay(@Validated VacationPayRequest request) {
        double vacationPay = vacationPayService.calculateVacationPay(request);
        return new ResponseEntity<>(vacationPay,
                HttpStatus.OK);
    }

    @GetMapping("/calculateWithDates")
    public ResponseEntity<?> getVacationPayWithDates(@Validated VacationPayWithDatesRequest request) {
        return new ResponseEntity<>(vacationPayService.calculateVacationPayWithDates(request),
                HttpStatus.OK);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<VacationPayErrorResponse> handleValidationExceptions(BindException ex) {
        VacationPayErrorResponse errorResponse = new VacationPayErrorResponse(ex.getAllErrors().get(0).getDefaultMessage(),
                Instant.now().toEpochMilli());
        return new ResponseEntity<>(errorResponse,
                HttpStatus.BAD_REQUEST);
    }
}

