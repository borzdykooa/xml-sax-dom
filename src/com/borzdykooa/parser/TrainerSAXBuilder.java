package com.borzdykooa.parser;

import com.borzdykooa.entity.Trainer;
import com.borzdykooa.util.TrainerHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class TrainerSAXBuilder implements ParserStrategy {

    private Set<Trainer> trainers;
    private TrainerHandler trainerHandler;
    private XMLReader reader;
    private String filename;
    private static final Logger logger = Logger.getLogger(TrainerSAXBuilder.class);


    public TrainerSAXBuilder(String filename) {
        trainerHandler = new TrainerHandler();
        this.filename = filename;
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(trainerHandler);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public Set<Trainer> getTrainers() {
        logger.info(trainers.toString());
        return trainers;
    }

    public void buildSetTrainers() {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        trainers = trainerHandler.getTrainers();
    }
}
