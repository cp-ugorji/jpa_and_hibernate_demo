package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.DemoHibernateApplication;
import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class CriteriaQueryRepositoryTest {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @Autowired
    EntityManager em;

    @Test
    public void criteria_query_select_from_course() {
        //select c from Course c
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Course.class);
        
        Root<Course> courseRoot = cq.from(Course.class);
        
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
    }
    
    @Test
    public void criteria_query_select_from_course_where_name_like_format () {
        //select s from Course s where s.name like '%av%'"
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Course.class);
        
        Root<Course> courseRoot = cq.from(Course.class);
        //adding condition
        Predicate like_av = cb.like(courseRoot.get("name"), "%av%");
        //adding where clause
        cq.where(like_av);
        
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
    }
    
    @Test
    public void criteria_query_select_from_student_where_passport_id_like_format () {
        //select s from Student s where s.passport.number like '%1234%'"
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Student.class);
        
        Root<Student> courseRoot = cq.from(Student.class);
        //adding condition
        Predicate like123 = cb.like(courseRoot.get("passport").get("number"), "%E123%");
        //adding where clause
        cq.where(like123);
        
        TypedQuery<Student> query = em.createQuery(cq.select(courseRoot));
        List<Student> c = query.getResultList();
        logger.info("Student Result List => {} ", c);
    }
    
    @Test
    public void criteria_query_select_courses_without_student() {
        //select c from Course c where c.students is empty
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Course.class);
        
        Root<Course> courseRoot = cq.from(Course.class);
        //adding condition
        Predicate studentIsEmpty = cb.isEmpty(courseRoot.get("students"));
        //adding where clause
        cq.where(studentIsEmpty);
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
    }
    
    @Test
    public void join() {        
        //select c, s from Course c join c.students s
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Course.class);
        
        Root<Course> courseRoot = cq.from(Course.class);
        
        Join<Object, Object> joinCondition = courseRoot.join("students");
        
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
    }
    
    @Test
    public void left_join() {
        //select c, s from Course c left join c.students s
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Course.class);
        
        Root<Course> courseRoot = cq.from(Course.class);
        
        Join<Object, Object> joinCondition = courseRoot.join("students", JoinType.LEFT);
        
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> c = query.getResultList();
        logger.info("Course Result List => {} ", c);
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
