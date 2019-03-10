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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author Chux
 */
@Entity
@NamedQueries(value = {
    @NamedQuery(name = "get_all_courses", query = "select c from Course c"),
    @NamedQuery(name = "get_all_courses_join_fetch", query = "select c from Course c join fetch c.students s")
})
@SQLDelete(sql = "update course set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();
    
    @ManyToMany(mappedBy = "courses")
    //student is the owning side of this relationship 
    //so to avoid creating 2 tables(course_student and student_courses), 
    //we map by courses which creates only one relationship/join table student_courses
    private List<Student> students = new ArrayList<>();
    
    private boolean isDeleted;
    
    protected Course() {
    }
    
    public Course(String name) {
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
    
    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudents(Student student) {
        this.students.add(student);
    }

    
    
    
    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
