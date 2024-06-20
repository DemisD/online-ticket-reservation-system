/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonapp.utility;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Administrator
 */
public class GregorianCalendarManipulation {

    public static int compareDates(String dateOne, String dateTwo) {
        return compareDates(convertDateToCalendar(dateOne), convertDateToCalendar(dateTwo));
    }

    public static int compareDates(Calendar dateOne, Calendar dateTwo) {
        if (dateOne != null) {
            if (dateOne.before(dateTwo)) {
                return 1;
            } else if (dateOne.after(dateTwo)) {
                return -1;
            } else {//if they equal
                return 0;
            }
        }
        return 0;
    }

    public static Calendar convertDateToCalendar(String date) {
        try {
            String dateParameters[] = date.split("-");
            int year = Integer.parseInt(dateParameters[0]);
            int month = Integer.parseInt(dateParameters[1]) - 1;
            int day = Integer.parseInt(dateParameters[2]);
            return new GregorianCalendar(year, month, day);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Calendar convertDateInECToCalendar(String date) {
        try {
            String dateParameters[] = date.split("/");
            int year = Integer.parseInt(dateParameters[2]);
            int month = Integer.parseInt(dateParameters[1]) - 1;
            int day = Integer.parseInt(dateParameters[0]);
            return new GregorianCalendar(year, month, day);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * return the month name eg 2012-07-10 returns july
     *
     * @return
     */
    public static String returnmonthName(String date) {
        String dateParameters[] = date.split("-");
        int year = Integer.parseInt(dateParameters[0]);
        int month = Integer.parseInt(dateParameters[1]) - 1;
        int day = Integer.parseInt(dateParameters[2]);
        Calendar ca1 = Calendar.getInstance();
        ca1.set(year, month, day);
        java.util.Date d = new java.util.Date(ca1.getTimeInMillis());
        return new SimpleDateFormat("MMMM").format(d);
    }

    public static String returnmonthNameWithDescription(String date) {
        String dateParameters[] = date.split("-");
        int year = Integer.parseInt(dateParameters[0]);
        int month = Integer.parseInt(dateParameters[1]) - 1;
        int day = Integer.parseInt(dateParameters[2]);
        Calendar ca1 = Calendar.getInstance();
        ca1.set(year, month, day);
        java.util.Date d = new java.util.Date(ca1.getTimeInMillis());
        return new SimpleDateFormat("MMMM").format(d) + " of " + year;
    }

    public static String convertCalendarToString(Calendar date) {
        DecimalFormat decimalforamt = new DecimalFormat("00");
        return Integer.toString(date.get(Calendar.YEAR)) + "-" + decimalforamt.format(date.get(Calendar.MONTH) + 1L) + "-" + decimalforamt.format(date.get(Calendar.DATE));
    }

    public static int calaculateYearDifference(String startDate) {
        if (startDate != null) {
            try {
                Calendar todayCal = new GregorianCalendar();
                Calendar startDateCal = convertDateToCalendar(startDate);
                return calaculateYearDifference(startDateCal, todayCal);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return 0;

    }

    public static int calaculateYearDifference(Calendar startDateCal) {
        Calendar todayCal = new GregorianCalendar();
        return calaculateYearDifference(startDateCal, todayCal);
    }

    public static int calaculateYearDifference(String startDate, String endDate) {
        if (startDate != null && endDate != null) {
            try {
                Calendar startDateCal = convertDateToCalendar(startDate);
                Calendar endDateCal = convertDateToCalendar(endDate);
                return calaculateYearDifference(startDateCal, endDateCal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;

    }

    public static int calaculateYearDifference(Calendar startDate, Calendar endDate) {
        int differenceInYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
        if ((startDate.get(Calendar.MONTH) > endDate.get(Calendar.MONTH))
                || ((startDate.get(Calendar.MONTH) == endDate.get(Calendar.MONTH)
                && startDate.get(Calendar.DAY_OF_MONTH) > endDate.get(Calendar.DAY_OF_MONTH)))) {
            differenceInYear = differenceInYear - 1;
        }
        return differenceInYear;
    }

    public static int calaculateMonthDifference(String startDate, String endDate) {
        if (startDate != null && endDate != null) {
            try {
                return calaculateMonthDifference(convertDateToCalendar(startDate), convertDateToCalendar(endDate));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;

    }

    public static int calaculateMonthDifference(Calendar startDate) {
        return calaculateMonthDifference(startDate, new GregorianCalendar());
    }

    public static Calendar addDaysToGivenDate(Calendar startDate, int daysToAdd) {
        startDate.add(Calendar.DATE, daysToAdd);
        return startDate;
    }

    /**
     * return month and year
     *
     * @param dateToSplit
     * @return
     */
    public static String returnYearAndManth(String dateToSplit) {
        String conc[];
        conc = dateToSplit.split("-");
        String val1 = conc[0];//year
        String val2 = conc[1];//month    
        return (val1 + "-" + val2);
    }

    public static String returnYearAndManthAndDate(String dateToSplit) {
        String conc[];
        conc = dateToSplit.split("-");
        String val1 = conc[0];//year
        String val2 = conc[1];//month
        String val3 = conc[2];//date      
        return (val1 + "-" + val2 + "-" + val3);
    }

    public static String returnDate(String dateToSplit) {
        String conc[];
        conc = dateToSplit.split("-");
        String val1 = conc[0];//year
        String val2 = conc[1];//month
        String val3 = conc[2];//date
        String date = val3;
        return date;
    }

    public static String returnMonth(String dateToSplit) {
        String conc[];
        conc = dateToSplit.split("-");
        String val1 = conc[0];//year
        String val2 = conc[1];//month
        String val3 = conc[2];//date
        String date = val2;
        return date;
    }

    public static String returnYear(String dateToSplit) {
        String conc[];
        conc = dateToSplit.split("-");
        String val1 = conc[0];//year        
        return val1;
    }

    public static int returnStaticHolydays(String year) {
        String listOfHolydays[] = new String[6];
        listOfHolydays[0] = year + "-01-01";
        listOfHolydays[1] = year + "-01-17";
        listOfHolydays[2] = year + "-11-05";
        listOfHolydays[3] = year + "-23-08";
        listOfHolydays[4] = year + "-27-08";
        listOfHolydays[5] = year + "-20-09";
        int count = 0;
        for (int i = 0; i < listOfHolydays.length; i++) {

        }
        return count;
    }

    public static int returnKnownHolydays(String date) {
        /**
         * ArrayList<HashMap> listOfHolidays = new ArrayList<HashMap>(); String
         * yearAndMont = returnYearAndManth(date);
         *
         * //listOfHolidays = holydayManager.lodHolydays(yearAndMont);
         *
         */
        return 0;
    }

    /**
     * the date is in gregorian calander return number of working dates
     *
     * @return
     */
    public static int returnNumberOfSaturdayAndSun(String date) {
        int year = 0;
        int month = 0;
        int cDate;
        year = Integer.valueOf(returnYear(date));
        month = Integer.valueOf(returnMonth(date));
        cDate = Integer.valueOf(returnDate(date));
        int numberOfDays = numberOfDaysInaMonth(year, month, cDate);
        String dateForCheck = null;
        int count = 0;
        for (int i = 1; i <= numberOfDays; i++) {
            if (i < 10) {
                dateForCheck = "0" + i;
            } else {
                dateForCheck = "" + i;
            }

        }

        return count;
    }

    /**
     * adds the months to the given date in string format
     *
     * @param startDate
     * @param monthsToAdd
     * @return
     *
     * public static String addMonthsToGivenDate(String startDate, int
     * monthsToAdd) {
     *
     * try {
     *
     * Calendar now = convertDateToCalendar(startDate); now.add(Calendar.MONTH,
     * monthsToAdd); return convertCalendarToString(now);
     *
     * } catch (Exception ex) { ex.printStackTrace(); }
     *
     * return null; }
     */
    /*
     public static String substractMonthsToGivenDate(String startDate, int monthsToAdd) {
     try {
     Calendar now = convertDateToCalendar(startDate);
     now.add(Calendar.MONTH, -monthsToAdd);
     String date = convertCalendarToString(now);
     return convertCalendarToString(now);
     } catch (Exception ex) {
     ex.printStackTrace();
     }
     return null;
     }*/
    //retuns the number of days in a month
    public static int numberOfDaysInaMonth(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        int maxDay = 0;
        maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;/////////////
    }

    public static int calaculateMonthDifference(Calendar startDate, Calendar endDate) {
        int yearDifference = calaculateYearDifference(startDate, endDate);
        return yearDifference * 12 + (int) Math.abs(startDate.get(Calendar.MONTH) - endDate.get(Calendar.MONTH));
    }

    public static int calaculateDateDifference(String startDate, String endDate) {
        try {
            return calaculateDateDifference(convertDateToCalendar(startDate), convertDateToCalendar(endDate));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * compare with today
     */
    public static int calaculateDateDifference(Calendar startDate) {
        return calaculateDateDifference(startDate, new GregorianCalendar());
    }

    public static int calaculateDateDifference(Calendar startDate, Calendar endDate) {
        long milliSecondDiffrence = (endDate.getTime()).getTime() - (startDate.getTime()).getTime();
        return (int) (milliSecondDiffrence / (1000 * 60 * 60 * 24));
    }

    public static String gregorianToEthiopian(String gregDate) {
        return StringDateManipulation.todayInEC(gregDate);
    }

    /**
     * return the month accepting the date in GC
     *
     * @param Date
     * @return
     */
    public static String returnEthMonthName(String Date) {
        String dateInEt = gregorianToEthiopian(Date);
        int etMonht = Integer.valueOf(returnMonth(dateInEt));

        if (etMonht == 1) {
            return "መስከረ�?".toString();

        }
        if (etMonht == 2) {
            return "ጥቅ�?ት".toString();

        }
        if (etMonht == 3) {
            return "ህዳር".toString();

        }
        if (etMonht == 4) {
            return "ታህሳስ".toString();

        }
        if (etMonht == 5) {
            return "ጥር".toString();

        }
        if (etMonht == 6) {
            return "የክቲት".toString();

        }
        if (etMonht == 7) {
            return "የክቲት".toString();

        }
        if (etMonht == 8) {
            return "ሚያዚያ".toString();

        }
        if (etMonht == 9) {
            return "�?ንቦት".toString();

        }
        if (etMonht == 10) {
            return "ሰኔ".toString();

        }
        if (etMonht == 11) {
            return "�?�?ሌ".toString();

        }
        if (etMonht == 12) {
            return "�?ሀሴ".toString();

        }

        return null;
    }

    /**
     * retuns the maximum date in the giben date
     *
     * @param dateOfYear
     * @return
     */
    public static int returnMaximumDate(String dateOfYear) {
        int mohth = Integer.valueOf(returnMonth(dateOfYear));
        int year = Integer.valueOf(returnYear(dateOfYear));
        int date = Integer.valueOf(returnDate(dateOfYear));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, mohth, date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int numberOfDaysInAMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }
    /*
     public static float calculateDateDifference(String dateStart, String dateStop) {
     SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
     Date d1 = null;
     Date d2 = null;
     try {
     d1 = format.parse(dateStart);
     d2 = format.parse(dateStop);
     float diff = d2.getTime() - d1.getTime();
     float diffDays = diff / (24 * 60 * 60 * 1000);
     return diffDays;

     } catch (Exception e) {
     e.printStackTrace();
     }

     return 0;
     }
     */

    public int returnMonth(int month) {

        if (month == 1) {
            return 31;
        }
        return 0;
    }

    public static int returnTotalDate(int month) {

        if (month == 1) {
            return 31;
        } else if (month == 2) {
            return 59;
        } else if (month == 3) {
            return 90;
        } else if (month == 4) {
            return 120;
        } else if (month == 5) {
            return 151;
        } else if (month == 6) {
            return 181;
        } else if (month == 7) {
            return 212;
        } else if (month == 8) {
            return 243;
        } else if (month == 9) {
            return 273;
        } else if (month == 10) {
            return 304;
        } else if (month == 11) {
            return 334;
        } else if (month == 12) {
            return 365;
        }

        return 0;
    }

    public static int returnNumberOfMonths(int month) {

        if (month >= 334 && month < 365) {
            return 11;
        } else if (month >= 304 && month <= 334) {
            return 10;
        } else if (month >= 273 && month <= 304) {
            return 9;
        } else if (month >= 243 && month <= 273) {
            return 8;
        } else if (month >= 212 && month <= 243) {
            return 7;
        } else if (month >= 181 && month <= 212) {
            return 6;
        } else if (month >= 151 && month <= 181) {
            return 5;
        } else if (month >= 120 && month <= 151) {
            return 4;
        } else if (month >= 90 && month <= 120) {
            return 3;
        } else if (month >= 59 && month < 90) {
            return 2;
        } else if (month >= 31 && month < 59) {
            return 1;
        } else if (month >= 1 && month <= 31) {
            return 0;
        }

        return 0;
    }

    public static int returnNumberOfDays(int month) {

        if (month >= 334 && month <= 365) {
            return month - 334;
        } else if (month >= 304 && month <= 334) {
            return month - 304;
        } else if (month >= 273 && month <= 304) {
            return month - 273;
        } else if (month >= 243 && month <= 273) {
            return month - 243;
        } else if (month >= 212 && month <= 243) {
            return month - 212;
        } else if (month >= 181 && month <= 212) {
            return month - 181;
        } else if (month >= 151 && month <= 181) {
            return month - 151;
        } else if (month >= 120 && month <= 151) {
            return month - 120;
        } else if (month >= 90 && month <= 120) {
            return month - 90;
        } else if (month >= 59 && month <= 90) {
            return month - 59;
        } else if (month >= 31 && month <= 59) {
            return month - 31;
        } else if (month >= 1 && month <= 31) {
            return month;
        }

        return 0;
    }

    public static int numberOfRemainingDates(int numberOfDays) {
        return numberOfDays % 365;
    }

    public static int calculateYear(int numberOfDays) {
        try {
            DecimalFormat df = new DecimalFormat("#");
            float result = Float.valueOf(numberOfDays) / Float.valueOf(365);
            int year = Integer.valueOf(df.format(result));
            return year;

        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }

    }

    public static String returnDateDiffInExperenceFormat(String startDate, String endDate) {
        try {
            int fromYear = Integer.valueOf(returnYear(startDate));
            int fromMoth = Integer.valueOf(returnMonth(startDate));
            int fromDate = Integer.valueOf(returnDate(startDate));
            int toYear = Integer.valueOf(returnYear(endDate));
            int toMonth = Integer.valueOf(returnMonth(endDate));
            int toDate = Integer.valueOf(returnDate(endDate));
            int year = 0;
            int month = 0;
            int days = 0;

            if (fromYear == toYear) {
                if (fromMoth <= toMonth) {
                    int fromMonthDifference = fromMoth - 1;
                    int toMonthDifference = toMonth - 1;
                    int totalFromDate = 0;
                    int totalToDate = 0;
                    if (fromMonthDifference == 0) {
                        totalFromDate = fromDate;
                    } else {
                        totalFromDate = returnTotalDate(fromMonthDifference) + fromDate;
                    }
                    totalToDate = returnTotalDate(toMonthDifference) + toDate;
                    int diff = totalToDate - totalFromDate;
                    int allTotals = totalToDate - totalFromDate;
                    year = 0;
                    month = returnNumberOfMonths(allTotals);
                    days = returnNumberOfDays(allTotals);
                    return year + " Year " + month + " Month " + days + " Days";

                }
            } //What if from year is not equal to to year
            else if (fromYear < toYear) {
                int fromMonthDifference = fromMoth - 1;
                int toMonthDifference = toMonth - 1;
                int totalFromDateDiff = 0;
                int totalToDateDiffer = 0;

                if (fromMonthDifference == 0) {
                    totalFromDateDiff = 365 - fromDate;
                } else {
                    totalFromDateDiff = 365 - (returnTotalDate(fromMonthDifference) + fromDate);
                }
                if (toMonthDifference == 0) {
                    totalToDateDiffer = toDate;
                } else {
                    totalToDateDiffer = returnTotalDate(toMonthDifference) + toDate;
                }
                int totalDatesForAgivenYear = totalToDateDiffer + totalFromDateDiff;
                if (totalDatesForAgivenYear >= 365) {
                    int reminingDates = numberOfRemainingDates(totalDatesForAgivenYear);
                    year = calculateYear(totalDatesForAgivenYear);
                    month = returnNumberOfMonths(reminingDates);
                    days = returnNumberOfDays(reminingDates);
                } else {
                    month = returnNumberOfMonths(totalDatesForAgivenYear);
                    days = returnNumberOfDays(totalDatesForAgivenYear);
                    year = 0;
                }
                int yearDifference = toYear - fromYear;

                if (yearDifference > 1) {
                    year = (yearDifference - 1) + year;
                }
                return year + " Year " + month + " Month " + days + " Days";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static int returnYearFromDateDifference(String startDate, String endDate) {
        try {
            int fromYear = Integer.valueOf(returnYear(startDate));
            int fromMoth = Integer.valueOf(returnMonth(startDate));
            int fromDate = Integer.valueOf(returnDate(startDate));
            int toYear = Integer.valueOf(returnYear(endDate));
            int toMonth = Integer.valueOf(returnMonth(endDate));
            int toDate = Integer.valueOf(returnDate(endDate));
            int year = 0;
            int month = 0;
            int days = 0;

            if (fromYear == toYear) {
                if (fromMoth <= toMonth) {
                    int fromMonthDifference = fromMoth - 1;
                    int toMonthDifference = toMonth - 1;
                    int totalFromDate = 0;
                    int totalToDate = 0;
                    if (fromMonthDifference == 0) {
                        totalFromDate = fromDate;
                    } else {
                        totalFromDate = returnTotalDate(fromMonthDifference) + fromDate;
                    }
                    totalToDate = returnTotalDate(toMonthDifference) + toDate;
                    int diff = totalToDate - totalFromDate;
                    int allTotals = totalToDate - totalFromDate;
                    year = 0;
                    month = returnNumberOfMonths(allTotals);
                    days = returnNumberOfDays(allTotals);
                    return year;

                }
            } //What if from year is not equal to to year
            else if (fromYear < toYear) {
                int fromMonthDifference = fromMoth - 1;
                int toMonthDifference = toMonth - 1;
                int totalFromDateDiff = 0;
                int totalToDateDiffer = 0;

                if (fromMonthDifference == 0) {
                    totalFromDateDiff = 365 - fromDate;
                } else {
                    totalFromDateDiff = 365 - (returnTotalDate(fromMonthDifference) + fromDate);
                }
                if (toMonthDifference == 0) {
                    totalToDateDiffer = toDate;
                } else {
                    totalToDateDiffer = returnTotalDate(toMonthDifference) + toDate;
                }
                int totalDatesForAgivenYear = totalToDateDiffer + totalFromDateDiff;
                if (totalDatesForAgivenYear >= 365) {
                    int reminingDates = numberOfRemainingDates(totalDatesForAgivenYear);
                    year = calculateYear(totalDatesForAgivenYear);
                    month = returnNumberOfMonths(reminingDates);
                    days = returnNumberOfDays(reminingDates);
                } else {
                    month = returnNumberOfMonths(totalDatesForAgivenYear);
                    days = returnNumberOfDays(totalDatesForAgivenYear);
                    year = 0;
                }
                int yearDifference = toYear - fromYear;

                if (yearDifference > 1) {
                    year = (yearDifference - 1) + year;
                }
                return year;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static int returnMonthFromDateDifference(String startDate, String endDate) {
        try {
            int fromYear = Integer.valueOf(returnYear(startDate));
            int fromMoth = Integer.valueOf(returnMonth(startDate));
            int fromDate = Integer.valueOf(returnDate(startDate));
            int toYear = Integer.valueOf(returnYear(endDate));
            int toMonth = Integer.valueOf(returnMonth(endDate));
            int toDate = Integer.valueOf(returnDate(endDate));
            int year = 0;
            int month = 0;
            int days = 0;

            if (fromYear == toYear) {
                if (fromMoth <= toMonth) {
                    int fromMonthDifference = fromMoth - 1;
                    int toMonthDifference = toMonth - 1;
                    int totalFromDate = 0;
                    int totalToDate = 0;
                    if (fromMonthDifference == 0) {
                        totalFromDate = fromDate;
                    } else {
                        totalFromDate = returnTotalDate(fromMonthDifference) + fromDate;
                    }
                    totalToDate = returnTotalDate(toMonthDifference) + toDate;
                    int diff = totalToDate - totalFromDate;
                    int allTotals = totalToDate - totalFromDate;
                    year = 0;
                    month = returnNumberOfMonths(allTotals);
                    days = returnNumberOfDays(allTotals);
                    return month;

                }
            } //What if from year is not equal to to year
            else if (fromYear < toYear) {
                int fromMonthDifference = fromMoth - 1;
                int toMonthDifference = toMonth - 1;
                int totalFromDateDiff = 0;
                int totalToDateDiffer = 0;

                if (fromMonthDifference == 0) {
                    totalFromDateDiff = 365 - fromDate;
                } else {
                    totalFromDateDiff = 365 - (returnTotalDate(fromMonthDifference) + fromDate);
                }
                if (toMonthDifference == 0) {
                    totalToDateDiffer = toDate;
                } else {
                    totalToDateDiffer = returnTotalDate(toMonthDifference) + toDate;
                }
                int totalDatesForAgivenYear = totalToDateDiffer + totalFromDateDiff;
                if (totalDatesForAgivenYear >= 365) {
                    int reminingDates = numberOfRemainingDates(totalDatesForAgivenYear);
                    year = calculateYear(totalDatesForAgivenYear);
                    month = returnNumberOfMonths(reminingDates);
                    days = returnNumberOfDays(reminingDates);
                } else {
                    month = returnNumberOfMonths(totalDatesForAgivenYear);
                    days = returnNumberOfDays(totalDatesForAgivenYear);
                    year = 0;
                }
                int yearDifference = toYear - fromYear;

                if (yearDifference > 1) {
                    year = (yearDifference - 1) + year;
                }
                return month;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static int returnNumberOfDaysFromDateDifference(String startDate, String endDate) {
        try {
            int fromYear = Integer.valueOf(returnYear(startDate));
            int fromMoth = Integer.valueOf(returnMonth(startDate));
            int fromDate = Integer.valueOf(returnDate(startDate));
            int toYear = Integer.valueOf(returnYear(endDate));
            int toMonth = Integer.valueOf(returnMonth(endDate));
            int toDate = Integer.valueOf(returnDate(endDate));
            int year = 0;
            int month = 0;
            int days = 0;

            if (fromYear == toYear) {
                if (fromMoth <= toMonth) {
                    int fromMonthDifference = fromMoth - 1;
                    int toMonthDifference = toMonth - 1;
                    int totalFromDate = 0;
                    int totalToDate = 0;
                    if (fromMonthDifference == 0) {
                        totalFromDate = fromDate;
                    } else {
                        totalFromDate = returnTotalDate(fromMonthDifference) + fromDate;
                    }
                    totalToDate = returnTotalDate(toMonthDifference) + toDate;
                    int diff = totalToDate - totalFromDate;
                    int allTotals = totalToDate - totalFromDate;
                    year = 0;
                    month = returnNumberOfMonths(allTotals);
                    days = returnNumberOfDays(allTotals);
                    return days;

                }
            } //What if from year is not equal to to year
            else if (fromYear < toYear) {
                int fromMonthDifference = fromMoth - 1;
                int toMonthDifference = toMonth - 1;
                int totalFromDateDiff = 0;
                int totalToDateDiffer = 0;

                if (fromMonthDifference == 0) {
                    totalFromDateDiff = 365 - fromDate;
                } else {
                    totalFromDateDiff = 365 - (returnTotalDate(fromMonthDifference) + fromDate);
                }
                if (toMonthDifference == 0) {
                    totalToDateDiffer = toDate;
                } else {
                    totalToDateDiffer = returnTotalDate(toMonthDifference) + toDate;
                }
                int totalDatesForAgivenYear = totalToDateDiffer + totalFromDateDiff;
                if (totalDatesForAgivenYear >= 365) {
                    int reminingDates = numberOfRemainingDates(totalDatesForAgivenYear);
                    year = calculateYear(totalDatesForAgivenYear);
                    month = returnNumberOfMonths(reminingDates);
                    days = returnNumberOfDays(reminingDates);
                } else {
                    month = returnNumberOfMonths(totalDatesForAgivenYear);
                    days = returnNumberOfDays(totalDatesForAgivenYear);
                    year = 0;
                }
                int yearDifference = toYear - fromYear;

                if (yearDifference > 1) {
                    year = (yearDifference - 1) + year;
                }
                return days;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    /**
     * returns only the total date difference between two dates
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int returnAnyDateDifference(String startDate, String endDate) {
        try {
            int fromYear = Integer.valueOf(returnYear(startDate));
            int fromMoth = Integer.valueOf(returnMonth(startDate));
            int fromDate = Integer.valueOf(returnDate(startDate));
            int toYear = Integer.valueOf(returnYear(endDate));
            int toMonth = Integer.valueOf(returnMonth(endDate));
            int toDate = Integer.valueOf(returnDate(endDate));
            int year = 0;
            int month = 0;
            int days = 0;

            if (fromYear == toYear) {
                if (fromMoth <= toMonth) {
                    int fromMonthDifference = fromMoth - 1;
                    int toMonthDifference = toMonth - 1;
                    int totalFromDate = 0;
                    int totalToDate = 0;
                    if (fromMonthDifference == 0) {
                        totalFromDate = fromDate;
                    } else {
                        totalFromDate = returnTotalDate(fromMonthDifference) + fromDate;
                    }
                    totalToDate = returnTotalDate(toMonthDifference) + toDate;
                    int allTotals = totalToDate - totalFromDate;

                    return allTotals;

                }
            } //What if from year is not equal to to year
            else if (fromYear < toYear) {
                int fromMonthDifference = fromMoth - 1;
                int toMonthDifference = toMonth - 1;
                int totalFromDateDiff = 0;
                int totalToDateDiffer = 0;

                if (fromMonthDifference == 0) {
                    totalFromDateDiff = 365 - fromDate;
                } else {
                    totalFromDateDiff = 365 - (returnTotalDate(fromMonthDifference) + fromDate);
                }
                if (toMonthDifference == 0) {
                    totalToDateDiffer = toDate;
                } else {
                    totalToDateDiffer = returnTotalDate(toMonthDifference) + toDate;
                }
                int totalDatesForAgivenYear = totalToDateDiffer + totalFromDateDiff;
                if (totalDatesForAgivenYear >= 365) {
                    int reminingDates = numberOfRemainingDates(totalDatesForAgivenYear);

                    return reminingDates;
                } else {

                    return totalDatesForAgivenYear;

                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static int returnMaxDateInMonth(String date) {
        String concatValue = date;
        String conc[];
        conc
                = concatValue.split("-");
        String val1 = conc[0];//year
        String val2 = conc[1];//month
        int datedd = 1;
        int intYear = Integer.valueOf(val1);
        int month = Integer.valueOf(val2) - 1;
        int max = GregorianCalendarManipulation.numberOfDaysInaMonth(intYear,
                month, datedd);
        return max;
    }

    public static void main(String args[]) {

        try {
            int s = 5;
            double z = 5.5 - s;
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
