package dev.zvaryyka.calculatorforneoflex;

import dev.zvaryyka.calculatorforneoflex.models.VacationPayRequest;
import dev.zvaryyka.calculatorforneoflex.models.VacationPayWithDatesRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculateVacationPayTest() throws Exception {
        double expectedVacationPay = 46075.09;

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("salary", "90000")
                        .param("numberOfVacationDays", "15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedVacationPay)));
    }

    @Test
    public void calculateVacationPayWithInvalidSalaryTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("salary", "0")
                        .param("numberOfVacationDays", "20"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void calculateVacationPayWithInvalidNumberOfVacationDaysTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("salary", "50000")
                        .param("numberOfVacationDays", "0"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void calculateVacationPayWithDatesTest() throws Exception {
        double expectedVacationPay = 46075.09;

        mockMvc.perform(MockMvcRequestBuilders.get("/calculateWithDates")
                        .param("salary", "90000")
                        .param("startDate", "2024-04-02")
                        .param("endDate", "2024-04-22"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedVacationPay)));
    }
    @Test
    public void calculateVacationPayWithDatesWithInvalidDatesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculateWithDates")
                        .param("salary", "6000")
                        .param("startDate", "2024-04-01")
                        .param("endDate", "2024-04-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
    @Test
    public void calculateVacationPayWithDatesWithInvalidSalaryTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculateWithDates")
                        .param("salary", "0")
                        .param("startDate", "2024-04-01")
                        .param("endDate", "2024-04-06"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }




}
