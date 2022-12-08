package app.parser;

import app.Beer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ParserDOM extends Parser {

    @Override
    public ArrayList<Beer> parseBeers() {
        ArrayList<Beer> beers = new ArrayList<>();
        Element root = document.getDocumentElement();

        NodeList gunsList = root.getElementsByTagName("BeerItem");
        for (int i = 0; i < gunsList.getLength(); i++) {
            Beer beer = buildGun((Element) gunsList.item(i));
            beers.add(beer);
        }
        return beers;
    }

    private Beer buildGun(Element gunElement) {
        Beer beer = new Beer();
        beer.setChars(new Beer.Chars());
        beer.setId(Integer.valueOf(gunElement.getAttribute("id")));

        for(String characteristic : beer.getCharacteristicNames()) {
            beer.setCharacteristic(characteristic, getElementString(gunElement, characteristic));
        }

        Element characteristicsElement = (Element) gunElement.getElementsByTagName("Chars").item(0);
        for(String characteristic : beer.getChars().getCharacteristicNames()) {
            beer.setCharacteristic(characteristic, getElementString(characteristicsElement, characteristic));
        }

        return beer;
    }

    private static String getElementString(Element element, String elementName) {
        NodeList nodes = element.getElementsByTagName(elementName);
        Node node = nodes.item(0);
        return node.getTextContent();
    }
}
