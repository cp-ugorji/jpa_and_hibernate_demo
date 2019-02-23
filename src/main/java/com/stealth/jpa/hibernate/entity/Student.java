/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealth.jpa.hibernate.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Chux
 */
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @OneToOne//database relationship meaning a student can have only one passport id and a passport id can only belong to one student. this is also known as eager fetch
    //@OneToOne(fetch = FetchType.LAZY)// this tells jpa and hibernate to only select passport when requested/needed
    private Passport passport;
    
//    @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany()
    @JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    //without this, it creates a table with name as STUDENT_COURSES but we want STUDENT_COURSE thus the addition of this line
    //now if you check the table, it created columns STUDENTS_ID and COURSES_ID but we want STUDENT_ID and COURSE_ID
    //so we add joinColumns parameter to define what we want as coulmn names in the join/relationship table
    private List<Course> courses = new ArrayList<>();

    protected Student() {
    }
    
    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
    
    

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", passport=" + passport + '}';
    }
    
//    @Override
//    public String toString() {
//        return "Student{" + "id=" + id + ", name=" + name + '}';
//    }
}
