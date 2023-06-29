package ir.maktab.view;

import ir.maktab.service.StudentCourseRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String filepath = "file.txt";
    private static StudentCourseRatingService studentCourseRatingService = StudentCourseRatingService.getInstance();

    public static void main(String args[]) throws IOException {
        logger.info("start main");
        studentCourseRatingService.readObjectFromFile(filepath);
        logger.info("end main");
    }
}