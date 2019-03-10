/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealth.jpa.hibernate.controller;

import com.stealth.jpa.hibernate.entity.Employee;
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
public class EmployeeRepository {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    EntityManager em;
    
    public Employee insert(Employee employee)
    {
        //this method will be used for both insert and update
        if(employee.getId() == null)
        {
            //insert
            em.persist(employee);
        }
        else
        {
            //update
            em.merge(employee);
        }
        return employee;
    }
    
    public List<Employee> getAllEmployee()
    {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }
    
//    public void deleteById(Long id)
//    {
//        Course c = em.find(Course.class, id);
//        em.remove(c);
//    }
    
}
