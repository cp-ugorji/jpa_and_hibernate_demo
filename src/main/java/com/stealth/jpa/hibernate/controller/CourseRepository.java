/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Review;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chux
 */
@Repository
@Transactional
public class CourseRepository {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    EntityManager em;
    
    public Course save(Course course)
    {
        //this method will be used for both insert and update
        if(course.getId() == null)
        {
            //insert
            em.persist(course);
        }
        else
        {
            //update
            em.merge(course);
        }
        return course;
    }
    
    public Course findById(Long id)
    {
        return em.find(Course.class, id);
    }
    
    public void deleteById(Long id)
    {
        Course c = em.find(Course.class, id);
        em.remove(c);
    }
    
    public void addReviewsForCourse(Long course_id, List<Review> reviews)
    {
        Course c = findById(course_id);
        logger.info("c.getReviews() -> {}", c.getReviews());
//        for(Review r : reviews)
//        {
//            c.addReview(r);
//            r.setCourse(c);
//            em.persist(r);
//        }
        //functional way of writing for each loop above
        reviews.stream().map((r) -> {
            c.addReview(r);
            return r;
        }).map((r) -> {
            r.setCourse(c);
            return r;
        }).forEachOrdered((r) -> {
            em.persist(r);
        });
        
        logger.info("c.getReviews() -> {}", c.getReviews());
    }
}
