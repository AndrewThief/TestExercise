package org.interlink.incamp;

import org.interlink.incamp.csv.CsvConst;
import org.interlink.incamp.csv.CsvFileManager;
import org.interlink.incamp.csv.CsvService;
import org.interlink.incamp.employee.EmployeeMapper;
import org.interlink.incamp.workshift.WorkShiftMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.interlink.incamp.csv.CsvConst.DEFAULT_INPUT_FILE;
import static org.interlink.incamp.csv.CsvConst.DEFAULT_OUTPUT_FILE;

public class ApplicationStarter {

    public static void main(String[] args) {

        CsvFileManager csvFileManager;
        WorkShiftMapper workShiftMapper = new WorkShiftMapper();
        EmployeeMapper employeeMapper = new EmployeeMapper();


        Scanner scan = new Scanner(System.in);

        System.out.println("Enter input file path (gets users directory by default): ");
        String inputPath = scan.nextLine();
        File inputFile = new File(inputPath);

        System.out.println("Enter output file path (gets users directory by default): ");
        String outputPath = scan.nextLine();
        File outputFile = new File(outputPath);

        if (inputPath.isEmpty() || !inputFile.exists()) {
            csvFileManager = new CsvFileManager(DEFAULT_INPUT_FILE, DEFAULT_OUTPUT_FILE);
        } else if (outputPath.isEmpty()) {
            csvFileManager = new CsvFileManager(inputFile, DEFAULT_OUTPUT_FILE);
        } else {
            csvFileManager = new CsvFileManager(inputFile, outputFile);
        }

        CsvService csvService = new CsvService(employeeMapper, workShiftMapper, csvFileManager);

        List<String[]> employeesRows = csvService.getEmployeesRow();
        String[] columns = createColumns(employeeMapper.createColumn(csvService.getEmployeesDates()));

        csvFileManager.write(employeesRows, columns);

    }

    private static String[] createColumns(String[] formattedDates) {
        List<String> columns = new ArrayList<>();
        columns.add(CsvConst.TABLE_BEGINNING);
        columns.addAll(Arrays.asList(formattedDates));

        return columns.toArray(String[]::new);
    }
}
