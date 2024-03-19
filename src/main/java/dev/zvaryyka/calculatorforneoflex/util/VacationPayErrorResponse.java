package dev.zvaryyka.calculatorforneoflex.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationPayErrorResponse {
    private String message;
    private long timestamp;
}
