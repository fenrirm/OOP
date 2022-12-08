package app;

import app.parser.Parser;
import app.parser.ParserDOM;
import app.parser.ParserSAX;
import app.parser.ParserStAX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    Parser parser;

    @Test
    void setSchema() {
        parser = new ParserDOM();
        assertThrows(Exception.class, () -> parser.setSchema("resources/invalid_path.xsd"));
        assertDoesNotThrow(() -> parser.setSchema("resources/beer.xsd"));

        parser = new ParserSAX();
        assertThrows(Exception.class, () -> parser.setSchema("resources/invalid_path.xsd"));
        assertDoesNotThrow(() -> parser.setSchema("resources/beer.xsd"));

        parser = new ParserStAX();
        assertThrows(Exception.class, () -> parser.setSchema("resources/invalid_path.xsd"));
        assertDoesNotThrow(() -> parser.setSchema("resources/beer.xsd"));
    }

    @Test
    void setSource() {
        parser = new ParserDOM();
        assertDoesNotThrow(() -> parser.setSchema("resources/beer.xsd"));
        assertThrows(Exception.class, () -> parser.setSource("resources/invalid_path.xml"));
        assertDoesNotThrow(() -> parser.setSource("resources/input.xml"));

        parser = new ParserSAX();
        assertDoesNotThrow(() -> parser.setSchema("resources/beer.xsd"));
        assertThrows(Exception.class, () -> parser.setSource("resources/invalid_path.xml"));
        assertDoesNotThrow(() -> parser.setSource("resources/input.xml"));

        parser = new ParserStAX();
        assertDoesNotThrow(() -> parser.setSchema("resources/beer.xsd"));
        assertThrows(Exception.class, () -> parser.setSource("resources/invalid_path.xml"));
        assertDoesNotThrow(() -> parser.setSource("resources/input.xml"));
    }
}
