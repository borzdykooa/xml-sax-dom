package com.borzdykooa.parser;

import com.borzdykooa.entity.Trainer;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Конкретная реализация DOM-парсера
 */
public class TrainerDOMBuilder implements ParserStrategy {

    private Set<Trainer> trainers;
    private DocumentBuilder documentBuilder;
    private String filename;
    private static final Logger logger = Logger.getLogger(TrainerDOMBuilder.class);

    public TrainerDOMBuilder(String filename) {
        this.trainers = new HashSet<Trainer>();
        this.filename = filename;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Set<Trainer> getTrainers() {
        logger.info(trainers.toString());
        return trainers;
    }

    public void buildSetTrainers() {
        try {
            Document document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList trainerList = root.getElementsByTagName("trainer");
            for (int i = 0; i < trainerList.getLength(); i++) {
                Element trainerElement = (Element) trainerList.item(i);
                Trainer trainer = buildTrainer(trainerElement);
                trainers.add(trainer);
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private Trainer buildTrainer(Element trainerElement) {
        Trainer trainer = new Trainer();
        trainer.setName(getElementTextContext(trainerElement, "name"));
        trainer.setLanguage(getElementTextContext(trainerElement, "language"));
        String experience = getElementTextContext(trainerElement, "experience");
        trainer.setExperience(Integer.parseInt(experience));
        return trainer;
    }

    private static String getElementTextContext(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
