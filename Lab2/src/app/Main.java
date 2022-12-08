/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import app.parser.Parser;
import app.parser.ParserDOM;
import app.parser.ParserSAX;
import app.parser.ParserStAX;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

class Main {

    private static final String schema = "resources/beer.xsd";
    private static final String source = "resources/beer.xsd";
    private static final String resultPath = "resources/dom.xml";

    public static void main(String[] args) {
        try {
            XMLCreator xmlCreator = new XMLCreator();
            ArrayList<Beer> beersDOM;
            ArrayList<Beer> beersSAX;
            ArrayList<Beer> beersStAX;

            beersDOM = runDOMParser();
            beersSAX = runSAXParser();
            beersStAX = runStAXParser();

            Assertions.assertEquals(beersDOM.size(), 1);
            for(int i = 0; i < beersDOM.size(); i++){
                Assertions.assertEquals(beersDOM.get(i), beersSAX.get(i));
                Assertions.assertEquals(beersSAX.get(i), beersStAX.get(i));
            }

            beersDOM.sort(new Beer.IdComparator());

            System.out.println("XML successfully parsed");
            xmlCreator.buildXML(beersDOM, resultPath);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private static ArrayList<Beer> runDOMParser() throws Exception {
        ParserDOM parserDOM = new ParserDOM();
        initParser(parserDOM);
        return parserDOM.parseBeers();
    }

    private static ArrayList<Beer> runSAXParser() throws Exception {
        ParserSAX parserSAX = new ParserSAX();
        initParser(parserSAX);
        return parserSAX.parseBeers();
    }

    private static ArrayList<Beer> runStAXParser() throws Exception {
        ParserStAX parserStAX = new ParserStAX();
        initParser(parserStAX);
        return parserStAX.parseBeers();
    }

    private static void initParser(Parser parser) throws Exception {
        parser.setSchema(schema);
        parser.setSource(source);
    }

}
