package ir.maktab.repository;

import ir.maktab.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class CourseRepository {
    private static final Logger logger = LoggerFactory.getLogger(CourseRepository.class);
    private static CourseRepository instance = new CourseRepository();

    private CourseRepository() {
    }

    public static CourseRepository getInstance() {
        return instance;
    }

    public void save(Course course) {

        EntityManager entityManager = ConfigJpa.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        logger.info(" courseSave ");
    }

    public Course findById(String name) {
        EntityManager entityManager = ConfigJpa.getFactory().createEntityManager();
        Course course = null;
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Course c where c.name=:name");
        query.setParameter("name", name);
        try {
            course = (Course) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error("No result forund for... ");
        }
        return course;
    }
}
