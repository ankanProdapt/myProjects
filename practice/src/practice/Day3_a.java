package practice;
import java.util.*;

//233667862

public class Day3_a {
	public static void main(String[] args) {
		System.out.println(julianToDdMmYyyy("2020001"));
	}
	
	
	
	
	//private static final String SEPARATORS = "/,";
	public static final int[] NON_LEAP = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public static final int[] LEAP = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	
	
	public static boolean isLeap(int year) {
		if(year % 100 == 0) {
			return (year % 400 == 0);
		}
		return (year % 4 == 0);
	}
	
	public static int[] findCalendar(int year) {
		return isLeap(year)? LEAP: NON_LEAP;
	}
	
	public static int findMonth(int count, int year) {
		
		int[] calendar = findCalendar(year);
		
		int m = 1;
		while(count - calendar[m] > 0) {
			count -= calendar[m];
			m++;
		}
		return m;
	}
	
	public static int findDay(int count, int year) {
		
		int[] calendar = findCalendar(year);
		
		int m = 1;
		while(count - calendar[m] > 0) {
			count -= calendar[m];
			m++;
		}
		return count;
	}
	
	public static String addDigits(int N, int d) {
		String result = String.valueOf(N);
		while(result.length() < d) {
			result = "0" + result;
		}
		return result;
	}
	
	
	public static String julianToDdMmYyyy(String julianDate) {
		int year = Integer.valueOf(julianDate.substring(0,4));
		int count = Integer.valueOf(julianDate.substring(4,7));
		
		int month = findMonth(count, year);
		int day = findDay(count, year);
		
		String ddmmyyyy = String.valueOf(addDigits(day,2)) +
				String.valueOf(addDigits(month,2)) +
				String.valueOf(addDigits(year,4));
		return ddmmyyyy;
	}

}
