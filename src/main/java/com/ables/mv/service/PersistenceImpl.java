/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ables.mv.service;

import com.ables.mv.model.Employee;
import com.ables.mv.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ables
 */
@Service
public class PersistenceImpl {
    @Autowired
    EmployeeRepo employeeRepo;
    public Employee save(Employee employee){
        System.out.println("Enter save method");
    return employeeRepo.save(employee);
    }
}
