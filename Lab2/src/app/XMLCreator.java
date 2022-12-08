package app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLCreator {

    public void buildXML(List<Beer> beerList, String xmlFilePath) {
        try {
            File file = new File(xmlFilePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.printf("File %s already exists.%n", xmlFilePath);
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("Beer");
            document.appendChild(root);
            for (Beer beer : beerList) {
                Element beerElement = document.createElement("BeerItem");
                root.appendChild(beerElement);

                Element name = document.createElement("Name");
                name.appendChild(document.createTextNode(beer.getName()));
                beerElement.appendChild(name);

                Element manufacturer = document.createElement("Manufacturer");
                manufacturer.appendChild(document.createTextNode(beer.getManufacturer()));
                beerElement.appendChild(manufacturer);

                Element al = document.createElement("Al");
                al.appendChild(document.createTextNode(String.valueOf(beer.isAlcoholic())));
                beerElement.appendChild(al);

                Element ingredients = document.createElement("Ingredients");
                ingredients.appendChild(document.createTextNode(beer.getIngredients()));
                beerElement.appendChild(ingredients);

                Element chars = document.createElement("Chars");
                beerElement.appendChild(chars);

                Element calories = document.createElement("Calories");
                calories.appendChild(document.createTextNode(String.valueOf(beer.getChars().getCalories())));
                chars.appendChild(calories);

                Element opacity = document.createElement("Opacity");
                opacity.appendChild(document.createTextNode(String.valueOf(beer.getChars().getOpacity())));
                chars.appendChild(opacity);

                Element rotations = document.createElement("Rotations");
                rotations.appendChild(document.createTextNode(String.valueOf(beer.getChars().getRotations())));
                chars.appendChild(rotations);

                Element poor = document.createElement("Poor");
                chars.appendChild(poor);
                Element material = document.createElement("Material");
                material.appendChild(document.createTextNode(String.valueOf(beer.getChars().getPour().getMaterial())));
                poor.appendChild(material);
                Element volume = document.createElement("Volume");
                volume.appendChild(document.createTextNode(String.valueOf(beer.getChars().getPour().getVolume())));
                poor.appendChild(volume);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Exception: " + e);
        }
    }
}