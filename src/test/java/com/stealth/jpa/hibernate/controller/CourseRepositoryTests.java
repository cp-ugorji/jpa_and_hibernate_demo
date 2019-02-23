package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.DemoHibernateApplication;
import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Review;
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
public class CourseRepositoryTests {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    EntityManager em;

    @Test
    //testing find by id method in CourseRepository class of DemoHibernateApplication
    public void findById_BasicTestCase() {
        Course c = courseRepository.findById(10001L);
        Assert.assertEquals("PHP", c.getName());
    }

//    @Test
//    //testing delete by id method in CourseRepository class of DemoHibernateApplication
//    @DirtiesContext//this means after the delete test is done, please automatically reset the data back to previous state
//    public void deleteById_BasicTestCase() {
//        courseRepository.deleteById(10001L);
//        //since the course with the id 10001L has been deleted, check if finding that id returns null.
//        //if it those, then we consider the test as passed
//        Assert.assertNull(courseRepository.findById(10001L));
//    }
    
    @Test
    //testing save/update method in CourseRepository class of DemoHibernateApplication
    @DirtiesContext//this means after the save test is done, please automatically reset the data back to previous state
    public void save_BasicTestCase() {
        //we want to update the course as a test to see if it works
        //so we first check if the course exist
        Course c = courseRepository.findById(10001L);
        //ensure the name PHP is exactly same as received in c.getName
        Assert.assertEquals("PHP", c.getName());
        //update the name to PHP - Updated
        c.setName("PHP - Updated");        
        courseRepository.save(c);
        //check the course with same id again
        Course c1 = courseRepository.findById(10001L);
        //ensure the name has changed from PHP  to PHP - Updated as received in c.getName
        Assert.assertEquals("PHP - Updated", c.getName());
    }
    
    @Test
    @Transactional
    public void retrieveReviewsForCourses()
    {
        Course c = courseRepository.findById(10001L);
        logger.info("{}", c.getReviews());
    }
    
    @Test
    @Transactional
    public void retrieveCourseForReview()
    {
        Review c = em.find(Review.class, 50001L);
        logger.info("{}", c.getCourse());
    }
}
