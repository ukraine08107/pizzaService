package whz.pti.eva.pizza_service_g11.security.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class  LoginController {


    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String getLoginPage(@RequestParam Optional<String> error, Model model) {
    	log.debug("Pizza service here, hello!");
		return "login";
	}

}
