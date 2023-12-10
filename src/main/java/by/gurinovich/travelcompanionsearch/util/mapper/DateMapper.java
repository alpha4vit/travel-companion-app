package by.gurinovich.travelcompanionsearch.util.mapper.impl;

import by.gurinovich.travelcompanionsearch.exception.InvalidRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {
    public static LocalDate convertFromString(String date)  {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        catch (Exception e){
            throw new InvalidRequestException("Invalid date format, date pattern should be 'dd/MM/yyyy'");
        }
    }
}
