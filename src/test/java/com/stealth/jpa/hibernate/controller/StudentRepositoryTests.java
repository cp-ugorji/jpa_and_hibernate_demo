package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.DemoHibernateApplication;
import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Passport;
import com.stealth.jpa.hibernate.entity.Student;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//add classes = DemoHibernateApplication.class to tell it the exact class to run from
@SpringBootTest(classes = DemoHibernateApplication.class)
public class StudentRepositoryTests {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    EntityManager em;

    @Test
    //testing find by id method in StudentRepository class of DemoHibernateApplication
    //@Transactional
    public void retrieveStudentAndPassportDetails() {
//        Student c = studentRepository.saveStudentWithPassport(new Student("Chux"));
//        Assert.assertEquals("PHP", c.getName());
        Student c = studentRepository.findById(20001L);
        logger.info("student info -> {}", c);
        logger.info("passport info -> {}", c.getPassport());
    }

    @Test
    //testing find by id method in StudentRepository class of DemoHibernateApplication
    @Transactional
    public void retrievePassportAndAssociatedStudent() {
        Passport c = em.find(Passport.class, 40001L);
        logger.info("passprt info -> {}", c);
        logger.info("student info -> {}", c.getStudent());
    }
    
    @Test
    //testing find by id method in StudentRepository class of DemoHibernateApplication
    @Transactional
    public void retrieveStudentAndCourses() {
        Student c = em.find(Student.class, 20001L);
        logger.info("student info -> {}", c);
        logger.info("courses info -> {}", c.getCourses());
    }
    
     @Test
    //testing find by id method in StudentRepository class of DemoHibernateApplication
    @Transactional
    public void retrieveCoursesAndStudent() {
         Course c = em.find(Course.class, 10002L);
        logger.info("courses info -> {}", c);
        logger.info("student info -> {}", c.getStudents());
    }
}
