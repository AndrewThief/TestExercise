package org.interlink.incamp.csv;

import org.interlink.incamp.employee.Employee;
import org.interlink.incamp.employee.EmployeeMapper;
import org.interlink.incamp.workshift.WorkShift;
import org.interlink.incamp.workshift.WorkShiftMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CsvService {

    private final EmployeeMapper employeeMapper;

    private final WorkShiftMapper workShiftMapper;

    private final CsvFileManager csvFileManager;

    public CsvService(EmployeeMapper employeeMapper,
                      WorkShiftMapper workShiftMapper,
                      CsvFileManager csvFileManager) {
        this.employeeMapper = employeeMapper;
        this.workShiftMapper = workShiftMapper;
        this.csvFileManager = csvFileManager;
    }

    public List<String[]> getEmployeesRow() {
        List<Employee> employees = getAllEmployees();
        List<String[]> employeesRow = new ArrayList<>(employees.size());

        getAllEmployees().forEach(employee ->
                employeesRow.add(employeeMapper.toCsvFormat(employee)));

        return employeesRow;
    }

    public List<LocalDate> getEmployeesDates() {
        List<WorkShift> workShifts = getAllWorkShifts();
        Set<LocalDate> localDatesSet = new HashSet<>();

        return workShifts.stream()
                .map(WorkShift::getDate)
                .filter(localDatesSet::add)
                .collect(Collectors.toList());
    }

    private List<Employee> getAllEmployees() {
        List<WorkShift> workShifts = getAllWorkShifts();
        List<Employee> employees = new ArrayList<>();

        for (String name : getNamesSet(workShifts)) {
            List<WorkShift> employeesWorkShifts = workShifts.stream()
                    .filter(e -> e.getName().equals(name))
                    .collect(Collectors.toList());

            employees.add(employeeMapper.getEmployee(employeesWorkShifts));
        }

        return employees;
    }

    private Set<String> getNamesSet(List<WorkShift> workShifts) {
        return workShifts.stream()
                .map(WorkShift::getName)
                .collect(Collectors.toSet());
    }

    private List<WorkShift> getAllWorkShifts() {
        List<String[]> rows = csvFileManager.read();
        return workShiftMapper.mapAll(rows);
    }
}
