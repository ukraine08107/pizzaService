package whz.pti.eva.pizza_service_g11;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }


    @Test
    public void testGetCreateUserPage() throws Exception {
        mockMvc.perform(get("/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("user_create"))
                .andDo(print());
    }


    @Test
    public void testGetMyAccount() throws Exception {
        mockMvc.perform(get("/myAccount")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("myAccount"))
//                .andExpect(model().attribute("user", )
                .andDo(print());
    }

    @Test
    public void testHandleUserCreateForm() throws Exception{
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("nickname", "testnickname")
                .param("email", "bah@a")
                .param("name", "TestName")
                .param("lastName", "TestLastName")
                .param("password", "demo")
                .param("passwordRepeated", "demo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/login"));


    }



}
