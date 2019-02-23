/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.entity.Course;
import com.stealth.jpa.hibernate.entity.Passport;
import com.stealth.jpa.hibernate.entity.Student;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chux
 */
@Repository
@Transactional
public class StudentRepository {
    @Autowired
    EntityManager em;
    
    public Student save(Student student)
    {
        //this method will be used for both insert and update
        if(student.getId() == null)
        {
            //insert
            em.persist(student);
        }
        else
        {
            //update
            em.merge(student);
        }
        return student;
    }
    
    public Student findById(Long id)
    {
        return em.find(Student.class, id);
    }
    
    public void deleteById(Long id)
    {
        Student c = em.find(Student.class, id);
        em.remove(c);
    }
    
    public Student saveStudentWithPassport(Student student)
    {        
        //this method will be used for both insert and update
        if(student.getId() == null)
        {
            //generate passport id
            String passport_id = student.getName().charAt(0) + "123456";
            Passport passport = new Passport(passport_id);
            //save passport
            em.persist(passport);
            //assign passport id to student
            student.setPassport(passport);
            //insert/save
            em.persist(student);
        }
        else
        {
            //update
            em.merge(student);
        }
        return student;
    }
    
    public void saveStudentAndCourse(Student student, Course course)
    {        
        em.persist(student);
        em.persist(course);
        student.addCourse(course);
        course.addStudents(student);
        em.persist(student);
    }
}
