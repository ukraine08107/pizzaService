package whz.pti.eva.pizza_service_g11.security.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    
    List<User> findAllByOrderByEmailAsc();
}
