package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static fungeye.cloud.service.mappers.UserMapper.userFromCreationDto;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(UserCreationDto dto) {
        try {
            UserEntity user = userRepository.save(userFromCreationDto(dto));
            return user != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean login(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user.map(userEntity -> userEntity.getPassword().equals(password)).orElse(false);
    }
}
