package whz.pti.eva.pizza_service_g11.order.boundary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import whz.pti.eva.pizza_service_g11.order.domain.Ordered;
import whz.pti.eva.pizza_service_g11.order.domain.dto.OrderedDTO;
import whz.pti.eva.pizza_service_g11.order.service.OrderedService;
import whz.pti.eva.pizza_service_g11.security.boundary.CurrentUserControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderedService orderedService;


    @GetMapping("/ordered")
    public String getOrder(Model model) {


        if (!CurrentUserControllerAdvice.isLoggedIn()) {
            return "login";
        }
        String userId = CurrentUserControllerAdvice.getCurrentUserId().toString();

        List<Ordered> orderedList = new ArrayList<>();
        orderedList = orderedService.getOrderedsByUserId(userId);

        if (orderedList.size() < 1) {
            model.addAttribute("orderMessage", "You didnt order anything");
            return "ordered";
        }

        List<OrderedDTO> orderedDTOS = new ArrayList<>();

        for (int index = 0; index < orderedList.size(); index++) {
            orderedDTOS.add(new OrderedDTO(index + 1,
                    orderedList.get(index).getDeliveryAddress().toString(),
                    orderedList.get(index).getOrderTotal(),
                    orderedList.get(index).getItems()));
        }

        model.addAttribute("items", orderedDTOS);


        return "ordered";
    }


}
