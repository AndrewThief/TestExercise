package org.interlink.incamp.employee;

import org.interlink.incamp.csv.CsvConst;
import org.interlink.incamp.workshift.WorkShift;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static au.com.bytecode.opencsv.CSVParser.DEFAULT_SEPARATOR;

public class EmployeeMapper {

    public Employee getEmployee(List<WorkShift> employeesWorkShifts) {

        Employee employee = new Employee();
        List<Double> hours = employeesWorkShifts.stream()
                .map(WorkShift::getHours)
                .collect(Collectors.toList());

        Optional<WorkShift> name = employeesWorkShifts.stream()
                .findFirst();
        name.ifPresent(workShift -> employee.setName(workShift.getName()));
        employee.setAllHours(hours);
        return employee;
    }

    public String[] toCsvFormat(Employee employee) {
        return (employee.getName()
                + DEFAULT_SEPARATOR
                + mapListToCsv(employee.getAllHours())).split(",");

    }

    public String[] createColumn(List<LocalDate> localDates) {
        return localDates.stream()
                .map(e ->
                        e.format(DateTimeFormatter.ofPattern(CsvConst.DATE_TIME_FORMAT, Locale.UK)))
                .toArray(String[]::new);

    }

    private String mapListToCsv(List<Double> list) {
        return list.stream()
                .map(e -> Double.toString(e))
                .collect(Collectors.joining(","));
    }
}
