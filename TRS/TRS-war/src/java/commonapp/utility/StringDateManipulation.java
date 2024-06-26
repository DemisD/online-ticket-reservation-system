package commonapp.utility;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
//import org.converter.DateConverter;
/*
 * StringDateManipulation.java
 *
 * Created on April 3, 2007, 4:01 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 * This class is to manipulate the date strings which comes from the text field
 * objects ( which in turn comes from the EthCalendar Object ) This class mainly
 * calculate the difference in year in months and in days and have 3 methods
 * correspondingly
 *
 * @author Administrator
 */
public class StringDateManipulation {

    public static class toDayInEc {

        public toDayInEc() {
        }
    }

    /**
     * Creates a new instance of StringDateManipulation
     */
    public StringDateManipulation() {
    }

    /**
     * This method calculates the 'year' difference between two date strings and
     * returns the number
     *
     * @param date1 is a String date (in yyyy-mm-dd )
     * @param date2 is a String date (in yyyy-mm-dd )
     * @see #differnceInMonths
     * @see #differenceInDays
     * @return returns an int value which is the difference between the years
     *
     */
    public static int differenceInYears(String date1, String date2) {
        String year1 = date1.substring(0, date1.indexOf('-'));
        String year2 = date2.substring(0, date2.indexOf('-'));
        int yearDiff = Integer.parseInt(year1) - Integer.parseInt(year2);

        return yearDiff;
    }

    /**
     *
     * @param _date
     * @return
     */
    public static String arrangeDateFormat(String date1) {
        String day;
        String month;
        String year;
        day = date1.substring(0, date1.indexOf('/'));
        month = date1.substring(date1.indexOf('/') + 1, date1.lastIndexOf('/'));
        year = date1.substring(date1.lastIndexOf('/') + 1);
        date1 = year + "-" + month + "-" + day;
        return date1;
    }

    /**
     * This method clalusates the difference between two dates in months Unlike
     * differenceInMonths it considers the years as months _date1 and _date2
     * must be String type and in yyyy-MM-dd format
     *
     * @param _date1
     * @param _date2
     * @return returns (((_date1.year - _date2.year) * 12 ) + _date1.month) -
     * date2.month
     */
    public static int datesDifferenceInMonths(String date1, String date2) {
        return (((getYear(date1) - getYear(date2)) * 12) + getMonth(date1)) - getMonth(date2);
    }

    /**
     * This method clalusates the difference between two dates in days Unlike
     * differenceInMonths it considers the years as months _date1 and _date2
     * must be String type and in yyyy-MM-dd format
     *
     * @param _date1
     * @param _date2
     * @return returns (((_date1.year - _date2.year) * 12 ) + _date1.month) -
     * date2.month
     */
    public static int datesDifferenceInDays(String date1, String date2) {

        int yearDif = getYear(date1) - getYear(date2);
        int monthDif = (yearDif > 0) ? getMonth(date1) - getMonth(date2) : getMonth(date2) - getMonth(date1);
        int dayDif = (yearDif > 0) ? getDate(date1) - getDate(date2) : getDate(date2) - getDate(date1);
        int year = Math.abs(yearDif);
        int month = monthDif;
        int day = dayDif;
        if (day < 0) {
            day = 30 + day;
            month = month - 1;
        }
        if (month < 0) {
            month = 12 + month;
            year = year - 1;
        }
        if (year < 0) {
            if (monthDif < 0) {
                day = Math.abs(dayDif);
                month = Math.abs(monthDif);
            } else {
                month = 12 - month;
            }
            year = 0;
        }

        return year * 365 + month * 30 + day;
    }

    /**
     * Extracts the year value from _date
     *
     * @param _date a String date in yyyy-MM-dd format
     * @return returns the first for charachters as integer
     */
    public static int getYear(String date1) {
        return Integer.parseInt(date1.substring(0, date1.indexOf('-')));
    }

    /**
     * Extracts the month value from _date
     *
     * @param _date a string date in yyyy-MM-dd format
     * @return returns the MM from yyyy-MM-dd as integer
     */
    public static int getMonth(String date1) {
        return Integer.parseInt(date1.substring(date1.indexOf('-') + 1, date1.lastIndexOf('-')));
    }

    /**
     * Extracts the date value form _date
     *
     * @param _date a string date in yyyy-MM-dd format
     * @return returns the dd from yyyy-MM-dd as integer
     */
    public static int getDate(String date1) {
        return Integer.parseInt(date1.substring(date1.lastIndexOf('-') + 1));
    }

    /**
     * converts yyyy-mm-dd to <h1>monthNameInAmharic dd, yyyy</h1> to be easily
     * readable
     *
     * @param date the date in ethiopian calander
     */
    public static String convertDateToString(String date) {
        String dateName[] = {"መስከረ�?", "ጥቅ�?ት", "ህዳር", "ታህሳስ", "ጥር", "የካቲት", "መጋቢት", "ሚያ�?ያ", "�?ንቦት", "ሰኔ", "ሀ�?ሌ", "�?ሀሴ", "ጳጉሜ"};
        return dateName[getMonth(date) - 1] + " " + Integer.toString(getDate(date)) + ", " + Integer.toString(getYear(date));
    }

    /**
     * This method calculates the 'month' difference between two date strings
     * and returns +ve if date1 > date2 else returns -ve
     *
     * @param date1 is a String date (in yyyy-mm-dd )
     * @param date2 is a String date (in yyyy-mm-dd )
     * @see #differnceInYears
     * @see #differenceInDays
     * @return returns an int value which is the difference between the months
     *
     */
    public static int differenceInMonths(String date1, String date2) {
        String month1 = date1.substring(date1.indexOf('-') + 1, date1.lastIndexOf('-'));
        String month2 = date2.substring(date2.indexOf('-') + 1, date2.lastIndexOf('-'));
        int monthDiff = Integer.parseInt(month1) - Integer.parseInt(month2);

        return monthDiff;
    }

    /**
     * This method calculates the 'day' difference between two date strings and
     * returns the number
     *
     * @param date1 is a String date (in yyyy-mm-dd )
     * @param date2 is a String date (in yyyy-mm-dd )
     * @see #differnceInMonths , #differenceYears
     * @return returns an int value which is the difference between the days
     *
     */
    public static int differenceInDays(String date1, String date2) {
        String day1 = date1.substring(date1.lastIndexOf('-') + 1);
        String day2 = date2.substring(date2.lastIndexOf('-') + 1);
        int dayDiff = Integer.parseInt(day1) - Integer.parseInt(day2);

        return dayDiff;
    }

    /**
     * Computes for the next day in Ethiopian calendar
     *
     * @param _date the initial string date in yyyy-MM-dd format
     * @return returns the next day's date in yyyy-MM-dd format
     */
    public static String nextDayInEC(String _date) {
        String _day = String.valueOf(getDate(_date));
        String _month = String.valueOf(getMonth(_date));
        String _year = String.valueOf(getYear(_date));
        int day = getDate(_date);
        int month = getMonth(_date);
        day++;
        if (day <= 30) {

            _day = String.valueOf(day);
        } else if (day > 30) {
            _day = "1";
            month++;
            if (month > 12) {
                int year = Integer.parseInt(_year);
                year++; // next year
                _year = String.valueOf(year);
            } else if (month < 10) {
                _month = "0" + String.valueOf(month);
            } else {
                _month = String.valueOf(month);
            }
        }
        if (_day.length() < 2) {
            _day = "0" + _day;
        }
        if (_month.length() < 2) {
            _month = "0" + _month;
        }

        String _nextDayDate = _year + "-" + _month + "-" + _day;

        return _nextDayDate;
    }

    /**
     * Computes for the next day in Ethiopian calendar
     *
     * @param _date the initial string date in yyyy-MM-dd format
     * @return returns the next day's date in yyyy-MM-dd format
     *
     * public static String previousDayInEC(String date11) { String dayy =
     * String.valueOf(getDate(date11)); String monthh =
     * String.valueOf(getMonth(date11)); String yearr =
     * String.valueOf(getYear(date11)); int day = getDate(date11); int month =
     * getMonth(date11); day--; if (day >= 1) { if (day < 10) { dayy = "0" +
     * String.valueOf(day); } else { dayy = String.valueOf(day); } } else if
     * (day < 1) { dayy = "30"; month--; if (month < 1) {
     * int year = Integer.parseInt(yearr);
     * year--; // next year
     * yearr = String.valueOf(year);
     * month = 12;  // first month
     * } else if (month > 1 && month < 10) { monthh = "0" +
     * String.valueOf(month); } else { monthh = String.valueOf(month); } }
     * //concat to the month if the month is between 1 and 10 inclusive if
     * (month < 10) { monthh = "0" + String.valueOf(month); } else { monthh =
     * String.valueOf(month); } // construct date String _previousDay = yearr +
     * "-" + monthh + "-" + dayy;
     *
     * return _previousDay; }
     */
    /**
     * Computes for the next month in Ethiopian calendar (Eliminating Poagmea)
     *
     * @param _date the initial string date in yyyy-MM-dd format
     * @return returns the next month's date in yyyy-MM-dd format
     */
    public static String nextMonthInEC(String _date) {
        String _month = _date.substring(_date.indexOf('-') + 1,
                _date.lastIndexOf('-'));
        int month = Integer.parseInt(_month);
        String _year = _date.substring(0, _date.indexOf('-'));
        month++;    // increament the month
        if (month > 12) {
            int year = Integer.parseInt(_year);
            year++; // next year
            _year = String.valueOf(year);
            month = 1;  // first month
        }
        if (month < 10) {
            _month = "0" + String.valueOf(month);
        } else {
            _month = String.valueOf(month);
        }

        String nextMonthsDate = _year + "-" + _month + "-"
                + _date.substring(_date.lastIndexOf('-') + 1);

        return nextMonthsDate;
    }

    /**
     *
     * @param _date
     * @param spliter
     * @return
     */
    public static String nextMonthInEC(String _date, String spliter) {
        String _month = _date.substring(_date.indexOf(spliter) + 1,
                _date.lastIndexOf(spliter));
        int month = Integer.parseInt(_month);
        String _year = _date.substring(0, _date.indexOf(spliter));
        month++;    // increament the month
        if (month > 12) {
            int year = Integer.parseInt(_year);
            year++; // next year
            _year = String.valueOf(year);
            month = 1;  // first month
        }
        if (month < 10) {
            _month = "0" + String.valueOf(month);
        } else {
            _month = String.valueOf(month);
        }

        String _nextMonthsDate = _year + spliter + _month + spliter
                + _date.substring(_date.lastIndexOf(spliter) + 1);

        return _nextMonthsDate;
    }

    /**
     *
     * @param _date
     * @param spliter
     * @return
     */
    public static String nextMonthInECPuagume(String _date, String spliter) {

        int year = Integer.parseInt(_date.substring(6, 10));
        int month = Integer.parseInt(_date.substring(3, 5));
        int day = Integer.parseInt(_date.substring(0, 2));
        String _month;
        String _year;
        month++;
        if (month == 13 || month == 14) {
            month = 1;
            year++;
        }
        if (month < 10) {
            _month = "0" + String.valueOf(month);
        } else {
            _month = String.valueOf(month);
        }

        String _nextMonthsDate = day + spliter + _month + spliter
                + year;
        return _nextMonthsDate;
    }

    /**
     *
     * @param dateToSplit
     * @return
     */
    public static String returnYearAndMonth(String dateToSplit) {
        try {
            String dates[] = dateToSplit.split("/");
            return dates[1] + "/" + dates[2];

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * reads the date of the client machien and change it to Ethiopian date
     *
     * @param todayInGC the current date in GC
     * @return returns the current date in EC with 'yyyy-MM-dd' format
     *
     */
    public static String todayInEC(String todayInGC) {

        long noYear = 0;
        currentYear = year1999;     // starting date's year
        currentMonth = year1999_1stMonth;
        currentDay = year1999_1stDay;
        currentDate = year1999_1stDate;
        long noOfDaysFromStart = java.sql.Date.valueOf(todayInGC).getTime() - java.sql.Date.valueOf("2006-09-11").getTime();

        noOfDaysFromStart /= (1000 * 60 * 60 * 24); //   number of days

        noYear = (4 * noOfDaysFromStart) / ((4 * 365) + 1);   // number of years in the gap

        currentYear += noYear;

        noOfDaysFromStart %= 365;

        if (noOfDaysFromStart < (noYear / 4)) {
            currentYear--;
            noOfDaysFromStart += 365;
        }

        noOfDaysFromStart -= (noYear / 4);

        if ((noOfDaysFromStart % 30) == 0) {
            currentMonth = (noOfDaysFromStart / 30);
        } else {
            currentMonth += (noOfDaysFromStart / 30);
        }

        if ((noOfDaysFromStart % 30) == 0) {
            currentDate = 30;
        } else {
            currentDate = (noOfDaysFromStart % 30);
        }
        String dateString = null;

        String _dateSeparator = "-";
        String _monthSeparator = "-";
        if (currentDate < 10) {
            _dateSeparator = "-0";
        }
        if (currentMonth < 10) {
            _monthSeparator = "-0";
        }

        dateString = currentYear + _monthSeparator + currentMonth + _dateSeparator + currentDate;

        return dateString;
    }

    /**
     * Changes the current date in GC to String format yyyy-MM-dd
     *
     * @return returns the java.util.Date() as yyyy-MM-dd
     */
    public static String currentDateInGC() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date curDate = new java.util.Date();
        return format.format(curDate);
    }

    /**
     * Calls the method toDayInEc(currentDateInGC())
     *
     * @return
     * @returns toDayInEc(currentDateInGC())
     * @see #currentDateInGC()
     * @see #todayInEC(String todayInGC)
     */
    public static String toDayInEthiopianCal() {
        return todayInEC(currentDateInGC());
    }

    /**
     * compute and return the closest integer number
     *
     * @param doubleNum <code>double</code> number
     * @return an integer number close to doubleNum
     */
    public static int getIntPart(double doubleNum) {
        int intPart = 0;
        if (doubleNum < -0.0000001) {
            intPart = (int) Math.ceil(doubleNum - 0.0000001);
        } else {
            intPart = (int) Math.floor(doubleNum + 0.0000001);
        }
        return intPart;
    }

    /**
     * returns name of the week
     *
     * @param date
     * @return returns Week Name
     */
    public static String getWeekDayName(String date) {
        String[] weekdays = {"ማክሰኞ", "ረብዑ", "�?ሙስ", "አርብ", "ቅዳሜ", "እ�?ድ", " ሰኞ"};
        java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();

        calendar.set(getYear(date), getMonth(date), getDate(date));
        String dateName = weekdays[calendar.get(java.util.Calendar.DAY_OF_WEEK) - 1];
        return dateName;
    }

    /**
     * Change the given date in gregorian calendar to Hijri Calender date, or
     * Islamic Calender. (Note: Hijri Calender is a lunar calendar based on 12
     * lunar months in a year of 354 or 355 days.)
     *
     * @param datee
     * @param date the date in gregorian date
     * @return returns the gregorian date in hijri with 'yyyy-MM-dd' format
     *
     */
    public static String todayInHC(String datee) {
        int date1 = StringDateManipulation.getDate(datee);
        int month = StringDateManipulation.getMonth(datee);
        int year = StringDateManipulation.getYear(datee);
        int jd = 0;//Julian days
        if ((year > 1582) || ((year == 1582) && (month > 10)) || ((year == 1582) && (month == 10) && (date1 > 14))) {
            jd = getIntPart((1461 * (year + 4800 + getIntPart((month - 14) / 12.0))) / 4.0) + getIntPart((367 * (month - 2 - 12 * (getIntPart((month - 14) / 12.0)))) / 12.0)
                    - getIntPart((3 * (getIntPart((year + 4900 + getIntPart((month - 14) / 12.0)) / 100.0))) / 4.0) + date1 - 32075;
        } else {
            jd = 367 * year - getIntPart((7 * (year + 5001 + getIntPart((month - 9) / 7.0))) / 4.0) + getIntPart((275 * month) / 9.0) + date1 + 1729777;
        }
        int l = jd - 1948440 + 10632;
        int n = getIntPart((l - 1) / 10631.0);
        l = l - 10631 * n + 354;
        int j = (getIntPart((10985 - l) / 5316.0)) * (getIntPart((50 * l) / 17719.0)) + (getIntPart(l / 5670.0)) * (getIntPart((43 * l) / 15238.0));
        l = l - (getIntPart((30 - j) / 15.0)) * (getIntPart((17719 * j) / 50.0)) - (getIntPart(j / 16.0)) * (getIntPart((15238 * j) / 43.0)) + 29;
        month = getIntPart((24 * l) / 709.0);
        date1 = l - getIntPart((709 * month) / 24.0);
        year = 30 * n + j - 30;
        datee = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date1);
        return datee;
    }

    /**
     * Changes the current date in GC to String format yyyy-MM-dd
     *
     * @return returns the java.util.Date() as yyyy-MM-dd
     */
    public static String convertTodayInGCtoHC() {
        return todayInHC(currentDateInGC());
    }

    /**
     * Changes the current date in GC to String format yyyy-MM-dd
     *
     * @return returns the java.util.Date() as yyyy-MM-dd
     */
    public static String convertTodayInECtoHC() {
        return todayInHC(toDayInEthiopianCal());
    }

    /**
     * This method compares _date with today, the method just calls
     * compareDate(todayInEc(), _date)
     *
     * @param strDate
     * @see #compareDate
     * @see #todayInEC
     * @return returns compareDate(todayInEc(), _date)
     *
     */
    public static int compareWithToday(String strDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return compareDate(todayInEC(format.format(new java.util.Date())), strDate);
    }

    /**
     * extracts the date value from _date (Note : _date must be in 'yyyy-MM-dd'
     * format)
     *
     * @param strDate
     * @return returns an integer representative of the date
     */
    public static int extractDate(String strDate) {
        return Integer.parseInt(strDate.substring(strDate.indexOf('-') + 1));
    }

    /**
     * extracts the year value from _date (Note : _date must be in 'yyyy-MM-dd'
     * format)
     *
     * @param strDate
     * @return returns an integer representative of the year
     */
    public static int extractYear(String strDate) {
        return Integer.parseInt(strDate.substring(0, strDate.indexOf('-')));
    }

    /**
     * extracts the month value from _date (Note : _date must be in 'yyyy-MM-dd'
     * format)
     *
     * @param _date the date from wich the month is to be extracted
     * @return returns an integer representative of the month
     */
    public static int extractMonth(String strDate) {
        return Integer.parseInt(strDate.substring(strDate.indexOf('-') + 1, strDate.lastIndexOf('-')));
    }

    /**
     * This method compares two date strings
     *
     * @param date1 is a String date (in yyyy-mm-dd )
     * @param date2 is a String date (in yyyy-mm-dd )
     * @see #differnceInMonths , #differenceYears , #differenceInDays
     * @return returns 1 if date1 &gt; date2, -1 if date1 &lt; date2 and/or 0 if
     * date1 == date2
     *
     */
    public static int compareDate(String date1, String date2) {
        int flag = 0;
        if (differenceInYears(date1, date2) > 0) {
            flag = 1;
        } else if (differenceInYears(date1, date2) < 0) {
            flag = -1;
        } else if (differenceInYears(date1, date2) == 0) {

            if (differenceInMonths(date1, date2) > 0) {
                flag = 1;
            } else if (differenceInMonths(date1, date2) < 0) {
                flag = -1;
            } else if (differenceInMonths(date1, date2) == 0) {

                if (differenceInDays(date1, date2) > 0) {
                    flag = 1;
                } else if (differenceInDays(date1, date2) < 0) {
                    flag = -1;
                } else if (differenceInDays(date1, date2) == 0) {
                    flag = 0;
                }
            }
        }

        return flag;
    }

    /**
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateDifference(String date1, String date2) {
        try {
            int yearDiff = differenceInYears(date2, date1);
            int monthDiff = differenceInMonths(date2, date1);
            int dayDiff = differenceInDays(date2, date1);
            int yearDiffInDays = 0;
            int monthDiffInDays = 0;
            int i = 0;
            int year = Integer.parseInt(date2.split("-")[0]);
            int preYear = Integer.parseInt(date1.split("-")[0]);
            int month = Integer.parseInt(date2.split("-")[1]);
            int day = Integer.parseInt(date2.split("-")[2]);
            for (int j = preYear; j < year; j++) {
                if ((j % 4 == 0)) {
                    i = i + 1;
                }
            }
            if (month == 13 && day == 6) {
                i = i + 1;
            }
            yearDiffInDays = (yearDiff * 365) + i;
            monthDiffInDays = monthDiff * 30;
            return (yearDiffInDays + monthDiffInDays + dayDiff);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return 0;
    }

    /**
     * The method reads accepts two dates and calculates the year difference
     * between the two dates. It may be used to calculate the age if
     * date1=birth_date and date2=today date2>date1
     */
    public static int calculateYearDifferenceBetweenTwoDates(String date1, String date2) {
        int dateDifference = datesDifferenceInDays(date1, date2);
        return dateDifference / 365;
    }

    public static String calculateYearAndMonthDifference(String date1, String date2) {
        int difference = datesDifferenceInMonths(date1, date2);
        return calculateYearAndMonthDifference(difference);
    }

    public static String calculateYearAndMonthDifference(int difference) {
        int year = difference / 12;
        int month = difference % 12;
        String years = (year == 1) ? year + " year" : (year > 0) ? year + " years" : "";
        String months = (month == 1) ? month + " month" : (month > 0) ? month + " months" : "";
        String experiance = (year > 0 && month > 0) ? years + " and " + months : (year > 0) ? years : months;
        return experiance;
    }

    public static String calculateYearAndMonthDifferenceRound(int difference) {
        int year = difference / 12;
        int month = difference % 12;
        int date = (difference % 365) % 30;
        month = (date >= 15) ? month + 1 : month;
        String years = (year == 1) ? year + " year" : (year > 0) ? year + " years" : "";
        String months = (month == 1) ? month + " month" : (month > 0) ? month + " months" : "";
        String experiance = (year > 0 && month > 0) ? years + " and " + months : (year > 0) ? years : months;
        return experiance;
    }

    public static String calculateYearAndMonthAndDateDifference(String date1, String date2) {
        int difference = datesDifferenceInDays(date1, date2);
        return calculateYearAndMonthAndDateDifference(difference);
    }

    public static String calculateYearAndMonthAndDateDifference(int difference) {
        if (difference > 0) {
            int year = (difference / 365);
            int month = (difference % 365) / 30;
            int date = (difference % 365) % 30;
            String years = (year == 1) ? year + " Year" : (year > 0) ? year + " Years " : "";
            String months = (month == 1) ? month + " Month" : (month > 0) ? month + " Months " : "";
            String dates = (date == 1) ? date + " Day " : (date > 0) ? date + " Days " : "";
            String separatorM = (year > 0 && month > 0) ? " , " : "";
            String separatord = ((year > 0 || month > 0) && date > 0) ? " , " : "";
            String experiance = years + separatorM + months + separatord + dates;
            return experiance;
        } else {
            return String.valueOf(difference);
        }
    }

    public static int calculateYearOfExpereance(int difference) {
        if (difference > 0) {
            int year = (difference / 365);

            return year;
        } else {
            return difference;
        }
    }

    public static int getGCBudgetYear() {
        int year = StringDateManipulation.getYear(currentDateInGC());
        try {
            Date toDay = new SimpleDateFormat("yyyy-MM-dd").parse(currentDateInGC());
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-07-01");
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse((year + 1) + "-06-30");
            if (toDay.after(startDate) && toDay.before(endDate)) {
                year += 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return year;
    }

    public void calendarComparison() {
        GregorianCalendar date1 = new GregorianCalendar();
        GregorianCalendar date2 = new GregorianCalendar();
        date1.set(2001, 10, 10);
        date2.set(2003, 10, 6);
    }
    public static long currentYear;
    public static long currentMonth;
    public static long currentDay;
    public static long currentDate;
    static long year1999 = 1999;
    static long year1999_1stDate = 1;
    static long year1999_1stMonth = 1;
    static long year1999_1stDay = 1;

    public static int compareYearAndMonth(String date1, String date2) {
        int flag = 0;
        if (differenceInYears(date1, date2) > 0) {
            flag = 1;
        } else if (differenceInYears(date1, date2) < 0) {
            flag = -1;
        } else if (differenceInYears(date1, date2) == 0) {

            if (differenceInMonths(date1, date2) > 0) {
                flag = 1;
            } else if (differenceInMonths(date1, date2) < 0) {
                flag = -1;
            }
        }

        return flag;
    }
    static String[] monthNames = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};

    public static String getMonthName(int month) {
        return monthNames[month];
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int x = StringDateManipulation.differenceInDays("2013-11-10", "2014-06-02");
    }

    public static Date getParseStringToDate(String dateVale) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = format.parse(dateVale);
        return date;
    }

    public static Date getParseStringToDate2(String dateVale) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateVale);
        return date;
    }

//    public static String getSubtrsctYearsFromDate(int year) {
//        try {
//            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar now = Calendar.getInstance();
//            now.add(Calendar.MONTH, -1);
//            now.add(Calendar.YEAR, -year);
//            String outputDate = DateConverter.converterGCtoEC(format.format(now.getTime()));
//            return outputDate;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }

}
