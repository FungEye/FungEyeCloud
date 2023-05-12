package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MushroomCreationDTOTest {

    @Test
    void testSetters() {
        // Create a new MushroomCreationDTO object with default constructor
        MushroomCreationDTO mushroom = new MushroomCreationDTO();

        // Set the name, description, origin, and userId
        mushroom.setName("Portobello");
        mushroom.setDescription("A large, meaty mushroom commonly used in vegetarian dishes");
        mushroom.setOrigin("France");
        mushroom.setUserId(1);

        // Check that the name and description fields were properly set
        assertEquals("Portobello", mushroom.getName());
        assertEquals("A large, meaty mushroom commonly used in vegetarian dishes", mushroom.getDescription());
        assertEquals("France", mushroom.getOrigin());
        assertEquals(1, mushroom.getUserId());
    }

    @Test
    void testToString() {
        // Create a new MushroomCreationDTO object with a name and description
        MushroomCreationDTO mushroom = new MushroomCreationDTO();
        mushroom.setName("Portobello");
        mushroom.setDescription("A large, meaty mushroom commonly used in vegetarian dishes");
        mushroom.setOrigin("France");
        mushroom.setUserId(1);

        // Check that the toString() method returns the expected string
        assertEquals("MushroomCreationDTO{name='Portobello', description='A large, meaty mushroom commonly" +
                " used in vegetarian dishes', origin='France', userId=1}", mushroom.toString());
    }
}
