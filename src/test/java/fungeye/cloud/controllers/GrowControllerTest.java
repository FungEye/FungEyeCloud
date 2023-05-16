package fungeye.cloud.controllers;

import fungeye.cloud.service.GrowService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = GrowController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class GrowControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GrowService service;

    @Test
    void testCreateGrow() throws Exception {
        // todo
    }

    @Test
    void testGetAllGrowsByUsername() throws Exception {
        // todo
    }

    @Test
    void testUpdateGrow() throws Exception {
        // todo
    }
}
