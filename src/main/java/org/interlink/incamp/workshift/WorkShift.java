package org.interlink.incamp.workshift;

import org.interlink.incamp.csv.CsvConst;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WorkShift {

    private String name;

    private LocalDate date;

    private double hours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "WorkShift{" +
                "name='" + name + '\'' +
                ", date=" + date.format(DateTimeFormatter.ofPattern(CsvConst.DATE_TIME_FORMAT, Locale.UK)) +
                ", hours=" + hours +
                '}';
    }
}
