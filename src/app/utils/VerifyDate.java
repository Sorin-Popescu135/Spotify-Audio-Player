package app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public final class VerifyDate {
    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2023;
    private static final int FEBRUARY = 2;
    private static final int MAX_DAY_IN_FEB = 28;
    private static final int MAX_DAY_IN_MONTH = 31;
    private static final int MAX_MONTH = 12;

    private VerifyDate() {

    }

    /**
     * Checks if a given date string is valid or not.
     *
     * @param data The date string to be checked. The format should be "dd-MM-yyyy".
     * @return True if the date is invalid, false otherwise.
     */
    public static boolean isDateWrong(final String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(data);
            Calendar calendar = Calendar.getInstance();

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            if (month == FEBRUARY && day > MAX_DAY_IN_FEB) {
                return true;
            } else {
                return day > MAX_DAY_IN_MONTH || month > MAX_MONTH
                        || year < MIN_YEAR || year > MAX_YEAR;
            }

        } catch (ParseException e) {
            return true;
        }
    }
}
