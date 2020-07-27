package org.interlink.incamp.csv;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvConst {

    private CsvConst() {
    }

    private static final Path usersDirectory = Paths.get(System.getProperty("user.dir"));

    public static final File DEFAULT_OUTPUT_FILE = usersDirectory.resolve("accounting.csv").toFile();

    public static final File DEFAULT_INPUT_FILE = usersDirectory.resolve("acme_worksheet.csv").toFile();

    public static final String DATE_TIME_FORMAT = "MMM dd yyyy";

    public static final String TABLE_BEGINNING = "Names/Dates";
}
