package helper;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");

    // Lay Thoi Gian Hien Tai
    public static Date now() {
        return new Date();
    }

    public static Date convertStringToDate(String dateString) throws ParseException {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            return date;
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Date convertDateToString(Date date) throws ParseException {
        if (date == null) {
            date = DateHelper.now();
        }
        return date;
    }

    // Chuyen String => Date Nhập Bằng JTextField
    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                DATE_FORMATTER.applyPattern(pattern[0]);
            } else if (date == null) {
                return DateHelper.now();
            }
            return DATE_FORMATTER.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Chuyen Date => String
    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            DATE_FORMATTER.applyPattern(pattern[0]);
        } else if (date == null) {
            date = DateHelper.now();
        }
        return DATE_FORMATTER.format(date);
    }

    // Them ngay Vao Thoi Gian
    public static Date addDay(Date date, int days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    // Them Ngay vao thoi gian hien tai
    public static Date add(int days) {
        Date now = DateHelper.now();
        now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
        return now;
    }

    public static Date truYear(int Year) {
        Date now = DateHelper.now();
        now.setYear(now.getYear() - Year);
        return now;
    }

    public static Date addYear(int Year) {
        Date now = DateHelper.now();
        now.setYear(now.getYear() + Year);
        return now;
    }
    
    public static Date addMonth(int month) {
        Date now = DateHelper.now();
        now.setMonth(now.getMonth() + month);
        return now;
    }

    // Get year
    public static int getYear(Date date) {
        // Geting an error because input date values is an formated SimpleDateFormat type
        // LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // int year = localDate.getYear();
        // return year;

        String input = date.toString();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(input, f);
        int year = ld.getYear();
        return year;
    }

    // Get month
    public static int getMonth(Date date) {
        String input = date.toString();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(input, f);
        int month = ld.getMonthValue();
        return month;
    }

}
