package com.borzdykooa;

import com.borzdykooa.connection.SessionFactoryManager;
import com.borzdykooa.dao.TrainerDao;
import com.borzdykooa.entity.Trainer;
import com.borzdykooa.parser.ParserStrategy;
import com.borzdykooa.util.ParserChooser;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Set;

public class Demo {

    public static void main(String[] args) {
        ParserStrategy firstParser = ParserChooser.chooseBuilder("resources/trainers1.xml");
        firstParser.buildSetTrainers();
        Set<Trainer> firstTrainerSet = firstParser.getTrainers();

        ParserStrategy secondParser = ParserChooser.chooseBuilder("resources/trainers2.xml");
        secondParser.buildSetTrainers();
        Set<Trainer> secondTrainerSet = secondParser.getTrainers();

        SessionFactory sessionFactory = SessionFactoryManager.getSessionFactory();
        firstTrainerSet.forEach(trainer -> TrainerDao.getInstance().save(trainer));
        secondTrainerSet.forEach(trainer -> TrainerDao.getInstance().save(trainer));
        List<Trainer> savedTrainers = TrainerDao.getInstance().findAll();
        System.out.println(savedTrainers.toString());
        sessionFactory.close();
    }
}
