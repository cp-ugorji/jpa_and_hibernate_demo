package com.stealth.jpa.hibernate;

import com.stealth.jpa.hibernate.controller.CourseRepository;
import com.stealth.jpa.hibernate.controller.StudentRepository;
import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Review;
import com.stealth.jpa.hibernate.entity.Student;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoHibernateApplication implements CommandLineRunner{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    public static void main(String[] args) {
            SpringApplication.run(DemoHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //find course by id
//        Course course = courseRepository.findById(10001L);
//        logger.info("Course 10001 -> {}", course);
        
        //delete existing course by id
        //courseRepository.deleteById(10001L);
        
        //add/update course
        //Course add = courseRepository.save(new Course("C"));
        
//        Student student = studentRepository.findById(20001L);
//        logger.info("Student 20001 -> {}", student);

//        List<Review> reviews = new ArrayList<>();
//        reviews.add(new Review("3", "Pretty Good"));
//        reviews.add(new Review("3", "Great"));
//        courseRepository.addReviewsForCourse(10001L, reviews);

        Student student = new Student("Okechuwkwu");
        Course course = new Course("C++");
        studentRepository.saveStudentAndCourse(student, course);
    }

}
