package com.borzdykooa.dao;

import com.borzdykooa.entity.Trainer;
import com.borzdykooa.connection.SessionFactoryManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Класс, содержащий методы работы с таблицей trainer
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TrainerDao {

    private static final TrainerDao INSTANCE = new TrainerDao();
    private static final Logger log = Logger.getLogger(TrainerDao.class);
    private static final SessionFactory SESSION_FACTORY = SessionFactoryManager.getSessionFactory();

    public Long save(Trainer trainer) {
        log.info("Method save is called in TrainerDao");
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = session.save(trainer);
            session.getTransaction().commit();
            if (id != null) {
                log.info(trainer.toString() + " has been saved successfully!");
            }
            return (Long) id;
        }
    }

    public List<Trainer> findAll() {
        log.info("Method findAll is called in TrainerDao");
        try (Session session = SESSION_FACTORY.openSession()) {
            List<Trainer> list = session.createQuery("select t from Trainer t", Trainer.class).list();
            if (list.size() > 0) {
                log.info("List of Trainers: " + list.toString());
            } else {
                log.info("List of Trainers is empty");
            }
            return list;
        }
    }

    public static TrainerDao getInstance() {
        return INSTANCE;
    }
}
