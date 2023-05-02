package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.User;
import fungeye.cloud.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import static fungeye.cloud.service.mappers.UserMapper.userFromCreationDto;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(UserCreationDto dto) {
        try {
            userRepository.save(userFromCreationDto(dto));
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String username, String password) {
        User user = userRepository.findById(username).get();
        return (user.getPassword().equals(password));
    }
}
