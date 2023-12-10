package by.gurinovich.travelcompanionsearch.util.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {

    public static LocalDate convertFromString(String date)  {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static  String converToString(LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
