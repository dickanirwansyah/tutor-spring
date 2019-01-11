package com.dicka.springreadexcel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExcelWritter {



    private static List<Employee> employees = new ArrayList<>();

    static {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1996, 03, 19);
        employees.add(new Employee("Muhammad Dicka Nirwansyah",
                "dickanirwansyah@gmail.com", dateOfBirth.getTime(), 800000.0));

        dateOfBirth.set(1995, 11, 11);
        employees.add(new Employee("Denada Rosa Florina",
                "denaflorina@gmail.com", dateOfBirth.getTime(), 300000.0));
    }

}

class Employee {

    private String name;
    private String email;
    private Date dateOfBirth;
    private double salary;

    public Employee(){}

    public Employee(String name, String email, Date dateOfBirth, double salary){
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }
}
