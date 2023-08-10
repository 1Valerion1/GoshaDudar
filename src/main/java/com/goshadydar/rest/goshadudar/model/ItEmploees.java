package com.goshadydar.rest.goshadudar.model;

import jakarta.persistence.*;

@Entity
public class ItEmploees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="department")
    private String department;

    @Column(name="salaries")
    private  int salaries;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalaries() {
        return salaries;
    }

    public void setSalaries(int salaries) {
        this.salaries = salaries;
    }


    public ItEmploees(){

    }
    public ItEmploees(String name, String department, int salaries) {
        this.name = name;
        this.department = department;
        this.salaries = salaries;
    }



}
