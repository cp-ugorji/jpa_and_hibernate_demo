/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.entity.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chux
 */
public interface CourseSpringDataRepository extends JpaRepository<Course, Long>
{
    //defining my own methods
    List<Course> findByName(String name);
}
