package com.borzdykooa.util;

import com.borzdykooa.entity.Trainer;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TrainerHandler extends DefaultHandler {

    private Set<Trainer> trainers;
    private Trainer current = null;
    private TrainerEnum currentEnum = null;
    private EnumSet<TrainerEnum> withText;

    public TrainerHandler() {
        this.trainers = new HashSet<>();
        this.withText = EnumSet.range(TrainerEnum.TRAINER, TrainerEnum.EXPERIENCE);
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("trainer".equals(localName)) {
            current = new Trainer();
        } else {
            TrainerEnum temp = TrainerEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case EXPERIENCE:
                    current.setExperience(Integer.parseInt(s));
                    break;
                case LANGUAGE:
                    current.setLanguage(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("trainer".equals(localName)) {
            trainers.add(current);
        }
    }
}
