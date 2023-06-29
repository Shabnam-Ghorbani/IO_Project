package ir.maktab.service;

import ir.maktab.entity.Student;
import ir.maktab.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    StudentRepository studentRepository = StudentRepository.getInstance();
    private static StudentService instance = new StudentService();

    private StudentService() {
    }

    public static StudentService getInstance() {
        return instance;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student find(String name) {
        Student student = studentRepository.findById(name);
        logger.info("find student by name");
        return student;
    }
}
