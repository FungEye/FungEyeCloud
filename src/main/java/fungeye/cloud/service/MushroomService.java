package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MushroomService {
    private MushroomRepository repository;
    private UserRepository userRepository;

    public MushroomService(MushroomRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public MushroomDto createMushroom(MushroomCreationDTO toCreate)
    {
        Mushroom toSave = MushroomMapper.mapCreateToMushroom(toCreate);
        Optional<UserEntity> user = userRepository.findById(toCreate.getUserId());
        if (user.isPresent())
        {
            toSave.setUser(user.get());
        } else if (toCreate.getUserId() == 0) {
            UserEntity passUser = new UserEntity();
            passUser.setId(0);
            toSave.setUser(passUser);
        }
        Mushroom saved = repository.save(toSave);
        return MushroomMapper.mapToMushroomDto(saved);
    }

    public MushroomDto getById(Long id)
    {
        Optional<Mushroom> mushroom = repository.findById(id);
        if (mushroom.isPresent())
        {
            return MushroomMapper.mapToMushroomDto(mushroom.get());
        }
        else
        {
            throw new IllegalArgumentException("Mushroom not found");
        }
    }

    public List<MushroomDto> getAll() {
        List<Mushroom> allMushrooms = repository.findAll();
        List<MushroomDto> dtos = new ArrayList<>();
        for (Mushroom mushroom:
             allMushrooms) {
            dtos.add(MushroomMapper.mapToMushroomDto(mushroom));
        }
        return dtos;
    }
}
