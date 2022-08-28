package whz.pti.eva.pizza_service_g11.security.service.user;


import java.util.Collection;
import java.util.Optional;

import whz.pti.eva.pizza_service_g11.security.domain.User;
import whz.pti.eva.pizza_service_g11.security.domain.UserCreateForm;

public interface UserService {

    User getUserById(long id);

    Optional<User> getUserByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Collection<User> getAllUsers();


    User create(UserCreateForm form);
    void createUser(User user);
}
