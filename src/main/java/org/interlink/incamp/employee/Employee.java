package org.interlink.incamp.employee;

import java.util.List;

public class Employee {

    private String name;

    private List<Double> allHours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllHours(List<Double> allHours) {
        this.allHours = allHours;
    }

    public List<Double> getAllHours() {
        return allHours;
    }

}
