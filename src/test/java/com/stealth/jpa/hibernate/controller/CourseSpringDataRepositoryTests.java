package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.DemoHibernateApplication;
import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Review;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//add classes = DemoHibernateApplication.class to tell it the exact class to run from
@SpringBootTest(classes = DemoHibernateApplication.class)
public class CourseSpringDataRepositoryTests {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CourseSpringDataRepository courseRepository;
    
    @Test
    public void findById_CoursePresent()
    {
        Optional<Course> courseOptional = courseRepository.findById(10001L);
        logger.info("Course is present => {}", courseOptional.isPresent());
        Assert.assertTrue(courseOptional.isPresent());
    }
    
    @Test
    public void findById_CourseNotPresent()
    {
        Optional<Course> courseOptional = courseRepository.findById(470001L);
        logger.info("Course is present => {}", courseOptional.isPresent());
        Assert.assertFalse(courseOptional.isPresent());
    }
    
    @Test
    public void saveAndUpdate()
    {
        Course c = new Course("Operation Research");
        courseRepository.save(c);
        
        //update course
        c.setName("Operation Research - Updated");
        courseRepository.save(c);
    }
    
    @Test
    public void find()
    {
        logger.info("Course count => {}", courseRepository.count());
        logger.info("Courses => {}", courseRepository.findAll());
    }
    
    @Test
    public void sort()
    {
        //telling spring data in what order to sort the result
        Sort s =  new Sort(Sort.Direction.DESC, "name");
        logger.info("Course count => {}", courseRepository.count());
        logger.info("Sorted Courses => {}", courseRepository.findAll(s));
    }
    
    @Test
    public void pagination()
    {
        //to divide result into pages we need PageRequest
        //1st parameter is the start of the page and the 
        //2nd parameter is the number of records to be retrieved per page 
        //PageRequest pageRequest = new PageRequest(0, 2);
        //the above declaration can be rewritten as
        PageRequest pageRequest = PageRequest.of(0, 2);        
        Page<Course> firstPage = courseRepository.findAll(pageRequest);
        logger.info("First Page of Courses => {}", firstPage.getContent());
        
        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = courseRepository.findAll(secondPageable);
        logger.info("Second Page of Courses => {}", secondPage.getContent());
    }
    
    @Test
    public void findUsingName()
    {
        //calling my defined method findByName
        logger.info("Find course by name => {}",courseRepository.findByName("Java"));
    }
    
}
