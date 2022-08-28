package whz.pti.eva.pizza_service_g11.pizza.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;
import whz.pti.eva.pizza_service_g11.pizza.service.PizzaService;

import java.security.Principal;
import java.util.List;

@Controller
public class PizzaController {

    @Autowired
    PizzaService pizzaService;


    @RequestMapping(value = {"/", "/main"})
    public String listAllPizzas(Model model) {
        List<Pizza> allPizzas = pizzaService.listAllPizzas();
        model.addAttribute("listAllPizzas", allPizzas);
        return "main";
    }


}
