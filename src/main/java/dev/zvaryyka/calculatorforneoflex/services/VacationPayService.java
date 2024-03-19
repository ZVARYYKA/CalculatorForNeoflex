package dev.zvaryyka.calculatorforneoflex.services;

import dev.zvaryyka.calculatorforneoflex.models.VacationPayRequest;
import dev.zvaryyka.calculatorforneoflex.models.VacationPayWithDatesRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class VacationPayService {

    public Double calculateVacationPay(VacationPayRequest vacationPayRequest) {
        double result =
                (vacationPayRequest.getSalary() / 29.3) * vacationPayRequest.getNumberOfVacationDays();
        return Math.round(result * 100.0) / 100.0; // Округляем до двух знаков после запятой
    }

    public Double calculateVacationPayWithDates(VacationPayWithDatesRequest request) {
        Set<LocalDate> holidays = getHolidays(request.getStartDate().getYear());

        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        // Переменная для хранения общего количества дней отпуска, включая праздники и выходные
        int totalVacationDays = 0;

        // Проверяем каждый день в отпуске
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            // Если текущий день не является выходным (суббота или воскресенье) и не является праздником
            if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                    currentDate.getDayOfWeek() != DayOfWeek.SUNDAY &&
                    !holidays.contains(currentDate)) {
                // Увеличиваем общее количество дней отпуска
                totalVacationDays++;
            }

            // Переходим к следующему дню
            currentDate = currentDate.plusDays(1);
        }

        // Рассчитываем отпускные с учетом общего количества дней отпуска и заработной платы
        double result = (request.getSalary() / 29.3) * totalVacationDays;
        return Math.round(result * 100.0) / 100.0; // Округляем до двух знаков после запятой
    }

    public static Set<LocalDate> getHolidays(int year) { //Какие-то праздники для примера
        Set<LocalDate> holidays = new HashSet<>();

        // Новый год
        holidays.add(LocalDate.of(year, 1, 1));
        holidays.add(LocalDate.of(year, 1, 2));
        holidays.add(LocalDate.of(year, 1, 3));
        holidays.add(LocalDate.of(year, 1, 4));
        holidays.add(LocalDate.of(year, 1, 5));
        holidays.add(LocalDate.of(year, 1, 6));
        holidays.add(LocalDate.of(year, 1, 7));
        holidays.add(LocalDate.of(year, 1, 8));

        // День защитника Отечества
        holidays.add(LocalDate.of(year, 2, 23));

        // Международный женский день
        holidays.add(LocalDate.of(year, 3, 8));

        // Праздник Весны и Труда
        holidays.add(LocalDate.of(year, 5, 1));

        // День Победы
        holidays.add(LocalDate.of(year, 5, 9));

        // День России
        holidays.add(LocalDate.of(year, 6, 12));

        // День народного единства
        holidays.add(LocalDate.of(year, 11, 4));

        // День Конституции Российской Федерации
        holidays.add(LocalDate.of(year, 12, 12));

        return holidays;
    }

}



