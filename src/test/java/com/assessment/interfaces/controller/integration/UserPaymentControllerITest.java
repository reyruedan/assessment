package com.assessment.interfaces.controller.integration;


import com.assessment.adapter.starter.JsonDataLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserPaymentControllerITest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonDataLoader jsonDataLoader;

    @Test
    void testGetAmountPaidByUsers() throws Exception {
        mockMvc.perform(get("/users/paid"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userId").exists())
                .andExpect(jsonPath("$[0].amountPaid").exists());
    }

    @Test
    void testGetAmountOwedByUsers() throws Exception {
        mockMvc.perform(get("/users/owed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("diana"))
                .andExpect(jsonPath("$[0].amountOwed").value(3.5))
                .andExpect(jsonPath("$[1].userId").value("ellis"))
                .andExpect(jsonPath("$[1].amountOwed").value(11.00));
    }

    @Test
    void testReceivePayment() throws Exception {
        String jsonPayment = """
            {
                "userId": 3,
                "amount": 75.00
            }
            """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayment))
                .andExpect(status().isOk());
    }
}