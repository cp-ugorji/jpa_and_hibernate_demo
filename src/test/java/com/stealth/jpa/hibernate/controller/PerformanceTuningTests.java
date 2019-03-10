package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.DemoHibernateApplication;
import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Review;
import java.util.List;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
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
public class PerformanceTuningTests {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    //testing find by id method in CourseRepository class of DemoHibernateApplication
    public void creatingNPlusOneProblem() {
        List<Course> courses = em.createNamedQuery("get_all_courses", Course.class).getResultList();
        courses.forEach((course) -> {
            logger.info("Course => {} Student => {}", course, course.getStudents());
        });
    }
    
    @Test
    @Transactional
    //testing find by id method in CourseRepository class of DemoHibernateApplication
    public void solvingTheNPlusOneProblem() {
        List<Course> courses = em.createNamedQuery("get_all_courses_join_fetch", Course.class).getResultList();
        courses.forEach((course) -> {
            logger.info("Course => {} Student => {}", course, course.getStudents());
        });
    }
    
    @Test
    @Transactional
    //testing find by id method in CourseRepository class of DemoHibernateApplication
    public void solvingNPlusOneProblem() {
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> subgraph = entityGraph.addSubgraph("students");
        List<Course> courses = em.createNamedQuery("get_all_courses", Course.class).setHint("javax.persistence.loadgraph", entityGraph).getResultList();
        courses.forEach((course) -> {
            logger.info("Course => {} Student => {}", course, course.getStudents());
        });
    }

}
