package com.borzdykooa.parser;

import com.borzdykooa.entity.Trainer;

import java.util.Set;

/**
 * Интерфейс для конкретных реализаций стратегии DOM и SAX
 */
public interface ParserStrategy {

    void buildSetTrainers();

    Set<Trainer> getTrainers();
}
