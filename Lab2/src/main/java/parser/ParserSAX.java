package app.parser;

import java.util.ArrayList;

import app.Beer;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParserSAX extends Parser{

    @Override
    public ArrayList<Beer> parseBeers() throws Exception {
        ArrayList<Beer> beers = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setSchema(schema);

        SAXParser saxParser = factory.newSAXParser();

        XMLReader reader = saxParser.getXMLReader();
        reader.setContentHandler(new BeerHandler(beers));
        reader.parse(source);

        return beers;
    }

    private static class BeerHandler extends DefaultHandler {
        private final ArrayList<Beer> beers;
        private Beer current;
        private String currentTag;

        public BeerHandler(ArrayList<Beer> beers) {
            this.beers = beers;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attrs) {
            if (qName.equals("BeerItem")) {
                current = new Beer();
                current.setChars(new Beer.Chars());
                current.setId(Integer.valueOf(attrs.getValue(0)));
            }
            currentTag = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("BeerItem"))
                beers.add(current);
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String value = new String(ch, start, length).trim();
            if(value.length() == 0) {
                return;
            }
            current.setCharacteristic(currentTag, value);
        }
    }
}
