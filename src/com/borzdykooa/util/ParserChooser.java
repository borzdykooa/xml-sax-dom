package com.borzdykooa.util;

import com.borzdykooa.parser.ParserStrategy;
import com.borzdykooa.parser.TrainerDOMBuilder;
import com.borzdykooa.parser.TrainerSAXBuilder;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Класс, отвечающий за выбор конкретной реализации парсера
 */
public class ParserChooser {

    private static ParserStrategy parserStrategy;
    private static final Logger logger = Logger.getLogger(ParserChooser.class);

    public static ParserStrategy chooseBuilder(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            Long fileSize = getFileSizeBytes(file);
            if (fileSize > 500) {
                logger.info("File size is " + fileSize + ", DOM-parser is chosen");
                parserStrategy = new TrainerDOMBuilder(fileName);
            } else {
                logger.info("File size is " + fileSize + ", SAX-parser is chosen");
                parserStrategy = new TrainerSAXBuilder(fileName);
            }
        }
        return parserStrategy;
    }

    private static Long getFileSizeBytes(File file) {
        return file.length();
    }
}
