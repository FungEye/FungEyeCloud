package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}