package cz.enehano.training.demoapp.restapi.repositories;

import cz.enehano.training.demoapp.restapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
