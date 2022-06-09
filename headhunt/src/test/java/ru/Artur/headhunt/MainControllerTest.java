package ru.Artur.headhunt;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.Artur.headhunt.controller.MainController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
@WithMockUser(username = "admin", password = "Frpk2015")
//@TestPropertySource("/application-test.properties")   // link to test properties
//@Sql(value = {"/create-user-before.sql", ...}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)  //will run before every test also can use it before method
//@Sql(value = {"/create-user-after.sql", ...}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)  //will run after every test also can use it before method
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController controller;


    @Test
    public void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("Элементов на странице")));
//                .andExpect(xpath("").string("")) need to correct templates to make this work
    }





}
