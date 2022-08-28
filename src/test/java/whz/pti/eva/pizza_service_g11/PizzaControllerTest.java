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
import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;
import whz.pti.eva.pizza_service_g11.pizza.service.PizzaService;

import java.math.BigDecimal;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PizzaControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testListAllPizzas() throws Exception {

        Pizza pizza1 = new Pizza("Margarita", new BigDecimal(4.50), new BigDecimal(3.00), new BigDecimal(2.00));
        Pizza pizza2 = new Pizza("Veggie", new BigDecimal(5.50), new BigDecimal(4.00), new BigDecimal(3.00));
        Pizza pizza3 = new Pizza("Cheese & Tomato", new BigDecimal(4.50), new BigDecimal(3.00), new BigDecimal(2.00));
        Pizza pizza4 = new Pizza("Pepperoni", new BigDecimal(4.50), new BigDecimal(3.00), new BigDecimal(2.00));
        pizzaService.addPizza(pizza1);
        pizzaService.addPizza(pizza2);
        pizzaService.addPizza(pizza3);
        pizzaService.addPizza(pizza4);

        mockMvc.perform(get("/main")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(model().attribute("listAllPizzas", hasSize(4)))
                .andDo(print());
    }

}
