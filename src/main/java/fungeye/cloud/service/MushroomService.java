package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.security.JwtGenerator;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MushroomService {
    private MushroomRepository repository;
    private UserRepository userRepository;
    private JwtGenerator generator;

    public MushroomService(MushroomRepository repository, UserRepository userRepository, JwtGenerator generator) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.generator = generator;
    }

    public MushroomDto createMushroom(MushroomCreationDTO toCreate) {
        Mushroom toSave = MushroomMapper.mapCreateToMushroom(toCreate);
        Optional<UserEntity> user = userRepository.findById(toCreate.getUserId());
        user.ifPresent(toSave::setUser);
        Mushroom saved = repository.save(toSave);
        return MushroomMapper.mapToMushroomDto(saved);
    }

    public MushroomDto getByMushroomId(Long id) {
        Optional<Mushroom> mushroom = repository.findById(id);
        if (mushroom.isPresent()) {
            return MushroomMapper.mapToMushroomDto(mushroom.get());
        } else {
            throw new IllegalArgumentException("Mushroom not found");
        }
    }

    public List<MushroomDto> getAllDefault() {
        // 3 is the admin userId
        return getMushroomDtos(3);
    }

    public List<MushroomDto> getCustom(int userId) {
        return getMushroomDtos(userId);
    }

    private List<MushroomDto> getMushroomDtos(int userId) {
        List<Mushroom> allDefaultMushrooms = repository.findByUser_Id(userId);
        List<MushroomDto> dtos = new ArrayList<>();
        for (Mushroom mushroom :
                allDefaultMushrooms) {
            dtos.add(MushroomMapper.mapToMushroomDto(mushroom));
        }
        return dtos;
    }

    public void archiveMushroom(long mushroomId, String token) {
        Mushroom entity = repository.findById(mushroomId).orElseThrow();
        UserEntity user = entity.getUser();
        String username = generator.getUsernameFromJwt(token.substring(7));
        if (user.getUsername().equals(username)) {
            if (entity.getArchived())
                throw new IllegalArgumentException(String.format("%s is already archived.", entity.getName()));
            repository.updateArchivedById(true, mushroomId);
        }
        else throw new BadCredentialsException(String.format("User: %s is not authorized to edit mushroom %s.", username, entity.getName()));

    }
}
