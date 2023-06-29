package ir.maktab.repository;

import ir.maktab.entity.StudentCourseRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class StuCourseRatingRepository {
    private static final Logger logger = LoggerFactory.getLogger(StuCourseRatingRepository.class);

    private static StuCourseRatingRepository instance = new StuCourseRatingRepository();

    private StuCourseRatingRepository() {
    }

    public static StuCourseRatingRepository getInstance() {
        return instance;
    }
    public void save(StudentCourseRating studentCourseRating) {

        EntityManager entityManager = ConfigJpa.getFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(studentCourseRating);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("is not save");
            entityManager.getTransaction().rollback();
        }
        logger.info("StudentCourseRating save ");
    }
}
