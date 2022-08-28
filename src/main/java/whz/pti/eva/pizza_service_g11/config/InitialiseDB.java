package whz.pti.eva.pizza_service_g11.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import whz.pti.eva.pizza_service_g11.cart.domain.CartRepository;
import whz.pti.eva.pizza_service_g11.item.domain.ItemRepository;
import whz.pti.eva.pizza_service_g11.payment.service.PaymentService;
import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;
import whz.pti.eva.pizza_service_g11.pizza.domain.PizzaRepository;
import whz.pti.eva.pizza_service_g11.security.domain.Role;
import whz.pti.eva.pizza_service_g11.security.domain.User;
import whz.pti.eva.pizza_service_g11.security.domain.UserRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class InitialiseDB {

    private static final Logger log = LoggerFactory.getLogger(InitialiseDB.class);

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PaymentService paymentService;


    @PostConstruct
    public void init() {

        paymentService.createAccount("mainAccount");

        Pizza pizza1 = new Pizza("Margarita", new BigDecimal(4.50), new BigDecimal(3.00), new BigDecimal(2.00));
        pizza1.setImagePath("margaritta-img.png");
        Pizza pizza2 = new Pizza("Veggie", new BigDecimal(5.50), new BigDecimal(4.00), new BigDecimal(3.00));
        pizza2.setImagePath("veggie-img.png");
        Pizza pizza3 = new Pizza("Cheese & Tomato", new BigDecimal(4.50), new BigDecimal(3.00), new BigDecimal(2.00));
        pizza3.setImagePath("cheese-tomato-img.png");
        Pizza pizza4 = new Pizza("Pepperoni", new BigDecimal(4.50), new BigDecimal(3.00), new BigDecimal(2.00));
        pizza4.setImagePath("pepperoni-img.png");
        Pizza pizza5 = new Pizza("Four Cheese", new BigDecimal(4.50), new BigDecimal(3.00), new BigDecimal(2.00));
        pizza5.setImagePath("four-cheese.png");

        pizzaRepository.save(pizza1);
        pizzaRepository.save(pizza2);
        pizzaRepository.save(pizza3);
        pizzaRepository.save(pizza4);
        pizzaRepository.save(pizza5);

        User baha = new User();
        baha.setNickname("baha");
        baha.setEmail("ba@a");
        baha.setName("Baktyar");
        baha.setLastName("Tentimishov");
        baha.setPasswordHash(new BCryptPasswordEncoder().encode("demo"));
        baha.setRole(Role.ADMIN);
        userRepository.save(baha);
        paymentService.createAccount(baha.getId().toString());

        log.info("ID for baha: " + baha.getId());

        User maria = new User();
        maria.setNickname("marga");
        maria.setEmail("ma@a");
        maria.setName("Maria");
        maria.setLastName("Prangishvili");
        maria.setPasswordHash(new BCryptPasswordEncoder().encode("demo"));
        maria.setRole(Role.ADMIN);
        userRepository.save(maria);
        paymentService.createAccount(maria.getId().toString());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
