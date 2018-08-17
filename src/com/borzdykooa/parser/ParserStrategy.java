package com.borzdykooa.parser;

import com.borzdykooa.entity.Trainer;

import java.util.Set;

public interface ParserStrategy {

    void buildSetTrainers();

    Set<Trainer> getTrainers();
}
