/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealth.jpa.hibernate.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;

/**
 *
 * @author Chux
 */
@Entity
public class PartTimeEmployee extends Employee
{
    private BigDecimal hourlyWage;
    
    public PartTimeEmployee() {
    }

    public PartTimeEmployee(BigDecimal hourlyWage, String name) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    
    
    
    
}
