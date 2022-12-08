package app.parser;

import app.Beer;
import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;

public abstract class Parser {
    protected Schema schema;
    protected Document document;
    protected String source;

    public void setSchema(String path) throws Exception {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        schema = factory.newSchema(new File(path));
    }

    public abstract ArrayList<Beer> parseBeers() throws Exception;

    public boolean isValid() {
        if(schema == null) {
            return false;
        }
        if(document == null) {
            return false;
        }
        try {
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setSource(String source) throws Exception {
        this.source = source;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new File(source));
        if(!isValid()){
            throw new Exception("input.xml is not valid");
        }
    }

}
