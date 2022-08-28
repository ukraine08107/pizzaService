package whz.pti.eva.pizza_service_g11;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.eva.pizza_service_g11.item.service.ItemService;
import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;
import whz.pti.eva.pizza_service_g11.pizza.service.PizzaService;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private PizzaService pizzaService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testAddItemToCart() throws Exception {
        Pizza pizza = new Pizza("Margo", new BigDecimal(4.50), new BigDecimal(3.00), new BigDecimal(2.00));
        pizza.setId(10L);
        pizzaService.addPizza(pizza);

        mockMvc.perform(post("/main")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("quantity", "3")
                        .param("id", pizza.getId().toString())
                        .param("size", "small")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andDo(print());
    }


}
