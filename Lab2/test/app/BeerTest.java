package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeerTest {

    private Beer beer;

    @BeforeEach
    void init() {
        beer = new Beer();
        beer.setChars(new Beer.Chars());
    }

    @Test
    void setCharacteristic() {
        beer.setCharacteristic("Name", "name");
        assertEquals("name", beer.getName());

        beer.setCharacteristic("Manufacturer", "Me");
        assertEquals("Me", beer.getManufacturer());

        beer.setCharacteristic("Al", "true");
        assertEquals(true, beer.isAlcoholic());

        beer.setCharacteristic("Ingredients", "Cat food");
        assertEquals("Cat food", beer.getIngredients());

        beer.setCharacteristic("Opacity", "100");
        assertEquals(100, beer.getChars().getOpacity());

        beer.setCharacteristic("Calories", "10");
        assertEquals(10, beer.getChars().getCalories());

        beer.setCharacteristic("Rotations", "500");
        assertEquals(500, beer.getChars().getRotations());

        beer.setCharacteristic("Material", "Glass");
        assertEquals("Glass", beer.getChars().getPour().getMaterial());

        beer.setCharacteristic("Volume", "500");
        assertEquals(500, beer.getChars().getPour().getVolume());
    }

    @Test
    void comparator() {
        Beer g1 = new Beer();
        g1.setId(1);
        Beer g2 = new Beer();
        g2.setId(2);
        Beer g3 = new Beer();
        g3.setId(3);
        Beer.IdComparator comparator = new Beer.IdComparator();
        assertTrue(comparator.compare(g2, g1) > 0);
        assertTrue(comparator.compare(g2, g3) < 0);
    }
}