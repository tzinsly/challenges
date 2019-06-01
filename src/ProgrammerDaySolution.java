public class ProgrammerDaySolution {

    private static final int GREGORIAN_CALENDAR = 1918;


    static String dayOfProgrammer(int year) {

        //Check if the year is leap year
        if ( (year < GREGORIAN_CALENDAR && year % 4 == 0)
                || (year > GREGORIAN_CALENDAR && (year % 400 == 0 || year % 4 == 0 && year % 100 != 0))) { //Is Leap Year
            return returnTwelve(year);

        } else if (year == GREGORIAN_CALENDAR) {
            return "26.09."+year;
        } else {
            return returnThirteen(year);
        }
    }

    static String returnTwelve(int year) {
        return "12.09."+year;
    }

    static String returnThirteen(int year) {
        return "13.09."+year;
    }

    public static void main (String ... args) {
        System.out.println(dayOfProgrammer(2000));
    }
}
