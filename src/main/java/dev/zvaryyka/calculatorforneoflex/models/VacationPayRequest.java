package dev.zvaryyka.calculatorforneoflex.models;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationPayRequest {

    @Min(value = 1, message = "Salary must be greater than 0")
    private double salary;

    @Min(value = 1, message = "Number of vacation days must be greater than 0")
    private int numberOfVacationDays;
}
