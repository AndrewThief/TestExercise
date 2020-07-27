package org.interlink.incamp.csv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileManager {

    private File inputFile;
    private File outputFile;

    public CsvFileManager(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public List<String[]> read() {
        List<String[]> allRows = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(inputFile), ',', '"', 1)) {
            allRows = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allRows;
    }

    public void write(List<String[]> rows, String[] columns) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            List<String[]> table = new ArrayList<>();
            table.add(columns);
            table.addAll(rows);
            table.forEach(writer::writeNext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
