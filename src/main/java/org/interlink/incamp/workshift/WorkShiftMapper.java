package org.interlink.incamp.workshift;

import org.interlink.incamp.csv.CsvConst;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class WorkShiftMapper {

    public List<WorkShift> mapAll(List<String[]> rows) {
        List<WorkShift> workShifts;

        workShifts = rows.stream()
                .map(this::fromCsvFormat)
                .collect(Collectors.toList());

        return workShifts;
    }


    private WorkShift fromCsvFormat(String[] row) {
        WorkShift workShiftBean = new WorkShift();

        workShiftBean.setName(row[0]);
        workShiftBean.setDate(LocalDate.parse(row[1],
                DateTimeFormatter.ofPattern(CsvConst.DATE_TIME_FORMAT, Locale.UK)));
        workShiftBean.setHours(Double.parseDouble(row[2]));

        return workShiftBean;
    }


}
