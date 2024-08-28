package com.example.rentaldemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.rentaldemo.service.IRentalService;
import org.hamcrest.core.Is;

@WebMvcTest
@AutoConfigureMockMvc
public class RentalControllerTests {

    @MockBean
    private IRentalService rentalServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRentalController_Test1() throws Exception {
        String req = "{\"toolCode\": \"JAKR\", \"rentalDays\" : 5, \"discountPercent\": 101, \"checkoutDate\": \"09/03/15\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/rental")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.discountPercent", Is.is("must be less than or equal to 100")));
    }
}
