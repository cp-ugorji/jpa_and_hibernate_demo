package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.DemoHibernateApplication;
import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//add classes = DemoHibernateApplication.class to tell it the exact class to run from
@SpringBootTest(classes = DemoHibernateApplication.class)
public class JPQLRepositoryTest {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @Autowired
    EntityManager em;

    @Test
    public void jpql_courses_without_student() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
    }
    
    @Test
    public void jpql_courses_with_at_least_2_student() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
    }
    
    @Test
    public void jpql_courses_order_by_number_of_student() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students)", Course.class);
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
    }
    
    @Test
    public void jpql_courses_order_by_number_of_student_desc() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc", Course.class);
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
    }
    
    @Test
    public void jpql_student_with_passport_in_certain_format_desc() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> s = query.getResultList();
        logger.info("Student Result List => {} ", s);
    }
    
    @Test
    public void join() {
        Query query = em.createQuery("select c, s from Course c join c.students s ");
        List<Object []> resultList = query.getResultList();
        logger.info("Result List Size=> {} ", resultList.size());
        resultList.forEach((result) -> {
            //result[0] holds the courses
            //result[1] holds the student
            logger.info("Course => {}, Student => {} ", result[0], result[1]);
        });
//        for(Object[] result:resultList)
//        {
//            //result[0] holds the courses
//            //result[1] holds the student
//            logger.info("Course => {}, Student => {} ", result[0], result[1]);
//        }
    }
    
    @Test
    public void left_join() {
        Query query = em.createQuery("select c, s from Course c left join c.students s ");
        List<Object []> resultList = query.getResultList();
        logger.info("Result List Size=> {} ", resultList.size());
        resultList.forEach((result) -> {
            //result[0] holds the courses
            //result[1] holds the student
            logger.info("Course => {}, Student => {} ", result[0], result[1]);
        });
    }
    
    @Test
    public void cross_join() {
        Query query = em.createQuery("select c, s from Course c, Student s ");
        List<Object []> resultList = query.getResultList();
        logger.info("Result List Size=> {} ", resultList.size());
        resultList.forEach((result) -> {
            //result[0] holds the courses
            //result[1] holds the student
            logger.info("Course => {}, Student => {} ", result[0], result[1]);
        });
    }

}
