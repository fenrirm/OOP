package app;

import app.parser.ParserSAX;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserSAXTest {
    private ParserSAX parser;

    @BeforeEach
    void init() {
        parser = new ParserSAX();
    }

    @Test
    void parseGuns() {
        try{
            parser.setSchema("resources/beer.xsd");
            parser.setSource("resources/test.xml");
            ArrayList<Beer> beers =  parser.parseBeers();
            assertEquals(2, beers.size());

            Beer ak = beers.get(0);
            assertEquals(1, ak.getId());
            assertEquals("N", ak.getName());
            assertEquals("M", ak.getManufacturer());
            assertEquals("Glass", ak.getIngredients());
            Beer.Chars akCharacteristics = ak.getChars();
            assertEquals(100, akCharacteristics.getOpacity());
            assertEquals(500, akCharacteristics.getCalories());

            Beer m16 = beers.get(1);
            assertEquals(2, m16.getId());
            assertEquals("N2", m16.getName());
            assertEquals("M2", m16.getManufacturer());
            assertEquals("Glass", m16.getIngredients());
            Beer.Chars m16Characteristics = m16.getChars();
            assertEquals(11, m16Characteristics.getOpacity());
            assertEquals(600, m16Characteristics.getCalories());
        } catch(Exception e) {
            fail();
        }
    }

}