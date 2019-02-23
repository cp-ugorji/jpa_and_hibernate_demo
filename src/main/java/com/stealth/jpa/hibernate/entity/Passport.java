/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealth.jpa.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Chux
 */
@Entity
public class Passport {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String number;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")//this tells jpa and hibernate that the Student table is the owning table and contains a field called passport that maps to this table
    //mapped by is never placed on the owning side of the relationship
    private Student student;

    protected Passport() {
    }
    
    public Passport(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

//    @Override
//    public String toString() {
//        return "Passport{" + "id=" + id + ", number=" + number + ", student=" + student + '}';
//    }

    @Override
    public String toString() {
        return "Passport{" + "id=" + id + ", number=" + number + '}';
    }
    
}
