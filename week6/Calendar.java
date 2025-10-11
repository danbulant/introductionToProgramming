package week6;

public class Calendar {
  static final String[] months = {
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December"
  };

  public static void main(String[] args) {
    int month = Integer.parseInt(args[0]);
    int year = Integer.parseInt(args[1]);

    System.out.printf("%s %d\n", months[month - 1], year);
    System.out.println(" S  M Tu  W Th  F  S");
    int daysToSkip = dayOfWeek(year, month, 1);
    int monthLength = monthLength(year, month);
    for(var i = 1; i < monthLength + daysToSkip + 1; i++) {
      if(i < daysToSkip && i % 7 == 0) {
        System.out.println();
        continue;
      }
      if(i <= daysToSkip) {
        System.out.print("   ");
        continue;
      }
      System.out.printf("%2d ", i - daysToSkip);
      if(i % 7 == 0) {
        System.out.println();
      }
    }
  }

  static final int[] monthLengths = {
      31,
      28,
      31,
      30,
      31,
      30,
      31,
      31,
      30,
      31,
      30,
      31,
  };

  /**
   * Length of a given month
   * @param year
   * @param month 1=January
   * @return length of the month in days, leap year adjusted
   */
  static int monthLength(int year, int month) {
    return monthLengths[month - 1] + (month == 2 ? (isLeapYear(year) ? 1 : 0) : 0);
  }

  static boolean isLeapYear(int year) {
    boolean isLeapYear;
    isLeapYear = (year % 4 == 0);
    isLeapYear = isLeapYear && (year % 100 != 0);
    isLeapYear = isLeapYear || (year % 400 == 0);
    return isLeapYear;
  }

  /**
   * @param year
   * @param month 1=January
   * @param day
   * @return day of week, 0=Sunday,6=Saturday
   */
  static int dayOfWeek(int year, int month, int day) {
    int y0 = year - (14 - month) / 12;
    int leapAdjustedYear = y0 + y0 / 4 - y0 / 100 + y0 / 400;
    int m0 = month + 12 * ((14 - month) / 12) - 2;
    return (day + leapAdjustedYear + (31 * m0) / 12) % 7;
  }
}
