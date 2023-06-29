package ir.maktab.service;

import ir.maktab.entity.Course;
import ir.maktab.entity.Student;
import ir.maktab.entity.StudentCourseRating;
import ir.maktab.repository.StuCourseRatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class StudentCourseRatingService {
    private static final Logger logger = LoggerFactory.getLogger(StudentCourseRatingService.class);
    private static StudentService studentService = StudentService.getInstance();
    private static CourseService courseService = CourseService.getInstance();
    StuCourseRatingRepository stuCourseRatingRepository = StuCourseRatingRepository.getInstance();
    private static StudentCourseRatingService instance = new StudentCourseRatingService();

    private StudentCourseRatingService() {
    }

    public static StudentCourseRatingService getInstance() {
        return instance;
    }

    public static Date changeLocalDateToDate(LocalDateTime localDate) {
        logger.info("change localDate to date");
        ZonedDateTime zdt = localDate.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        return output;
    }

    public void save(StudentCourseRating studentCourseRating) {
        logger.info("save object studentCourseRating");
        stuCourseRatingRepository.save(studentCourseRating);
    }

    public void readObjectFromFile(String filepath) throws IOException {
        logger.info("start readObjectFromFile");
        FileReader fileReader = new FileReader(filepath);
        BufferedReader buffReader = new BufferedReader(fileReader);
        if (!buffReader.ready()) {
            logger.error("file not found");
            throw new FileNotFoundException("file is empty");
        }
        while (buffReader.ready()) {
            String inputObject = buffReader.readLine();
            String[] arrayOfInput = inputObject.split(",");
            Student student;
            if (studentService.find(arrayOfInput[1]) == null) {
                student = new Student(0, arrayOfInput[1]);
                studentService.save(student);
            } else
                student = studentService.find(arrayOfInput[1]);
            Course course;
            if (courseService.find(arrayOfInput[0]) == null) {
                course = new Course(0, arrayOfInput[0]);
                courseService.save(course);
            } else course = courseService.find(arrayOfInput[0]);
            String[] dateString = arrayOfInput[2].split(" ");
            String[] date = dateString[0].split("-");
            String[] time = dateString[1].split(":");
            LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                    Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
            Date dateComment = changeLocalDateToDate(localDateTime);
            StudentCourseRating studentCourseRating = new StudentCourseRating(student, course, dateComment,
                    Double.parseDouble(arrayOfInput[3]), arrayOfInput[4]);
            save(studentCourseRating);
        }
        logger.info("readObjectFromFile method do good");
    }
}
