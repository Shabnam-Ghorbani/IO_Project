package ir.maktab.repository;

import ir.maktab.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class StudentRepository {
    private static final Logger logger = LoggerFactory.getLogger(CourseRepository.class);
    private static StudentRepository instance = new StudentRepository();

    private StudentRepository() {
    }
    public static StudentRepository getInstance() {
        return instance;
    }

    public void save(Student student) {
        EntityManager entityManager = ConfigJpa.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        logger.info("end: method save student  ");
    }

    public Student findById(String name) {
        EntityManager entityManager = ConfigJpa.getFactory().createEntityManager();
        Student student = null;
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Student s where s.name=:name");
        query.setParameter("name", name);
        try {
            student = (Student) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error("No result forund for... ");
        }
        return student;
    }
}
