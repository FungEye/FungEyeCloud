package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MushroomCreationDTOTest {

    @Test
    void testMushroomCreationDTO() {
        // Create a new MushroomCreationDTO object with a name and description
        MushroomCreationDTO mushroom = new MushroomCreationDTO("Shiitake",
                "A type of edible mushroom commonly used in Japanese cuisine", "Japan");

        // Check that the name and description fields were properly set
        assertEquals("Shiitake", mushroom.getName());
        assertEquals("A type of edible mushroom commonly used in Japanese cuisine", mushroom.getDescription());
        assertEquals("Japan", mushroom.getOrigin());
    }

    @Test
    void testSetters() {
        // Create a new MushroomCreationDTO object with default constructor
        MushroomCreationDTO mushroom = new MushroomCreationDTO();

        // Set the name and description fields using setters
        mushroom.setName("Portobello");
        mushroom.setDescription("A large, meaty mushroom commonly used in vegetarian dishes");
        mushroom.setOrigin("France");

        // Check that the name and description fields were properly set
        assertEquals("Portobello", mushroom.getName());
        assertEquals("A large, meaty mushroom commonly used in vegetarian dishes", mushroom.getDescription());
        assertEquals("France", mushroom.getOrigin());
    }

    @Test
    void testToString() {
        // Create a new MushroomCreationDTO object with a name and description
        MushroomCreationDTO mushroom = new MushroomCreationDTO("Button", "A small, white mushroom commonly used in salads and soups", "France");

        // Check that the toString() method returns the expected string
        assertEquals("MushroomCreationDTO{name='Button', description='A small, white mushroom commonly used in " +
                "salads and soups', origin='France'}", mushroom.toString());
    }
}
