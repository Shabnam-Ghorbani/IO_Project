package ir.maktab.service;

import ir.maktab.entity.Course;
import ir.maktab.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    CourseRepository courseRepository = CourseRepository.getInstance();
    private static CourseService instance = new CourseService();

    private CourseService() {
    }

    public static CourseService getInstance() {
        return instance;
    }

    public void save(Course course) {
        courseRepository.save(course);
        logger.info("save course");
    }

    public Course find(String name) {
        Course course = courseRepository.findById(name);
        logger.info("find course by name");
        return course;
    }
}
