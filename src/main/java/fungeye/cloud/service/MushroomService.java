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

    /*
    TODO Filter out archived mushrooms
    But first come up with a solution so that users can archive their own mushrooms,
    but not default mushrooms.
    More TODO: Test added code + probably edit already created tests?
     */
    private List<MushroomDto> getMushroomDtos(int userId) {
        List<Mushroom> allDefaultMushrooms = repository.findByUser_Id(userId);
        List<MushroomDto> dtos = new ArrayList<>();
        for (Mushroom mushroom :
                allDefaultMushrooms) {
            dtos.add(MushroomMapper.mapToMushroomDto(mushroom));
        }
        return dtos;
    }

    public void archiveMushroom(long mushroomId) {
        Mushroom entity = repository.findById(mushroomId).orElseThrow();
        if (entity.getArchived())
            throw new IllegalArgumentException(String.format("%s is already archived.", entity.getName()));
        repository.updateArchivedById(true, mushroomId);
    }
}
