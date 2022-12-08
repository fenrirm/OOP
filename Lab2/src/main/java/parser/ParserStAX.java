package app.parser;

import app.Beer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ParserStAX extends Parser{
    private final XMLInputFactory inputFactory;
    private XMLStreamReader reader;
    private Beer current;

    public ParserStAX() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public ArrayList<Beer> parseBeers() throws Exception {
        ArrayList<Beer> beers = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(source);
        reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                String tag = reader.getLocalName();
                if (tag.equals("BeerItem")) {
                    beers.add(parseBeer());
                }
            }
        }

        return beers;
    }

    private Beer parseBeer() throws XMLStreamException {
        current = new Beer();
        current.setId(Integer.valueOf(reader.getAttributeValue(null, "id")));
        current.setChars(new Beer.Chars());
        while (reader.hasNext()) {
            int next = reader.next();
            if(next == XMLStreamConstants.START_ELEMENT) {
                String tag = reader.getLocalName();

                if (!tag.equals("Chars"))
                    current.setCharacteristic(tag, getString());

            } else if(next == XMLStreamConstants.END_ELEMENT)   {
                String tag = reader.getLocalName();

                if (tag.equals("BeerItem"))
                    return current;
            }
        }
        throw new XMLStreamException("Unknown element");
    }

    private String getString() throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
