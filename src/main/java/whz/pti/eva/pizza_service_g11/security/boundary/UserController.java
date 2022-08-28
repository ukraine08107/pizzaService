package whz.pti.eva.pizza_service_g11.security.boundary;

import java.security.Principal;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import whz.pti.eva.pizza_service_g11.order.domain.Ordered;
import whz.pti.eva.pizza_service_g11.order.service.OrderedService;
import whz.pti.eva.pizza_service_g11.payment.service.PaymentService;
import whz.pti.eva.pizza_service_g11.security.domain.CurrentUser;
import whz.pti.eva.pizza_service_g11.security.domain.User;
import whz.pti.eva.pizza_service_g11.security.domain.UserCreateForm;
import whz.pti.eva.pizza_service_g11.security.service.user.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    PaymentService paymentService;
    @Autowired
    OrderedService orderedService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getCreateUserPage() {
        return "user_create";
    }

    @PostMapping("/users/create")
    public String handleUserCreateForm(@Valid @ModelAttribute("myform") UserCreateForm form, BindingResult bindingResult, Model model) {
        LOGGER.info("Processing user create form= " + form + " bindingResult= " + bindingResult);
        LOGGER.info(form.toString());
        model.addAttribute("users", userService.getAllUsers());
      /*  if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "user_create";
        }*/
        User user = userService.create(form);
        paymentService.createAccount(user.getId().toString());
//        orderedService.createOrdered(new Ordered(user.getId().toString()));
        return "redirect:/login";
    }


    @RequestMapping(value = "/myAccount", method = {RequestMethod.GET})
    public String getMyAccount(Model model) {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info(currentUser.getUser().toString());
        model.addAttribute("user", currentUser.getUser());
        model.addAttribute("balance", paymentService.checkBalance(currentUser.getId().toString()).getCode().replaceAll("Kontostand betraegt ", ""));
//        if (user != null) model.addAttribute("user", user);
//        else model.addAttribute("user", new NoSuchElementException(String.format("User=%s not found", id)));
        return "myAccount";
    }


}
