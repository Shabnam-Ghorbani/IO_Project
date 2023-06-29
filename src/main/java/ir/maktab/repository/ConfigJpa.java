package ir.maktab.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConfigJpa {
    private static final Logger logger = LoggerFactory.getLogger(CourseRepository.class);
    public static EntityManagerFactory getFactory() {
        logger.info(" initialization.");
        return Persistence.createEntityManagerFactory("PU");
    }
}
