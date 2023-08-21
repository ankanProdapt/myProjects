package practice;
import java.util.*;

public class Day5_a {
	
	public static Integer getDigitCount(int reading) {
		return String.valueOf(reading).length();
	}
	
	public static boolean isAscending(int reading) {
		int prev = 10;
		while(reading > 0) {
			int lastDigit = reading % 10;
			if(lastDigit >= prev) return false; 
			
			prev = lastDigit;
			reading /= 10;
		}
		
		return true;
	}
	
	public static int nextReading(int reading) {
		int digitCount = getDigitCount(reading);
		while(true) {
			reading += 1;
			if(getDigitCount(reading) != digitCount)
				reading = Integer.valueOf("1".repeat(digitCount));
			
			if(isAscending(reading))
				return reading;
			
		}
	}
	
	public static int prevReading(int reading) {
		int digitCount = getDigitCount(reading);
		while(true) {
			reading -= 1;
			if(getDigitCount(reading) != digitCount)
				reading = Integer.valueOf("9".repeat(digitCount));
			
			if(isAscending(reading))
				return reading;
		}
	}
	
	
	public static int nextKthReading(int reading, int k) {
		for(int i = 0; i < k; i++) {
			reading = nextReading(reading);
		}
		return reading;
	}
	
	public static int prevKthReading(int reading, int k) {
		for(int i = 0; i < k; i++) {
			reading = prevReading(reading);
		}
		return reading;
	}
	
	
	
	public static int distanceBetween(int start, int end) {
		int distance = 0;
		while(start != end) {
			distance += 1;
			start = nextReading(start);
		}
		
		return distance;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(isAscending(123));
		System.out.println(nextReading(123));
		System.out.println(prevReading(123));
		System.out.println(nextKthReading(123, 2));
		System.out.println(prevKthReading(123, 1000));
		System.out.println(distanceBetween(123, 145));
		System.out.println(distanceBetween(999, 123));
	}
}