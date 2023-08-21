package practice;
import java.util.*;

public class Day2_b {
	public static void main(String[] args) {
		System.out.println(findAmicablePairs(100000));
		System.out.print(args.length);
	}
	
	public static boolean isDivisible(Integer N, Integer d) {
		return (N % d == 0);
	}
	
	public static Integer aliquotSum(Integer N) {
		if(N <= 1) return 0;
		
		int sumOfFactors = 1;
		
		for(int i = 2; i*i <= N; i++) {
			if(isDivisible(N,i)) {
				sumOfFactors += i;
				if(i != N/i)
					sumOfFactors += N/i;
			}
		}
		
		return sumOfFactors;
	}
	
//	public static Integer abundancyCategory(Integer N) {
//		int abundancy = aliquotSum(N)-N;
//		
//		if(abundancy > 0) return 1;      // Abundant
//		if(abundancy < 0) return -1;     // Deficient
//		return 0;                        // Perfect
//	}
	
	public static ArrayList<Integer> createPair(int a, int b){
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(a);
		p.add(b);
		return p;
	}
	
	
	public static ArrayList<ArrayList<Integer>> findAmicablePairs(Integer N){
		ArrayList<ArrayList<Integer>> amicablePairs = new ArrayList<ArrayList<Integer>>();
		
		int[] aliquotVal = new int[N+1];
		
		for(int i = 2; i <= N; i++) {
			aliquotVal[i] = aliquotSum(i);
		}
		
		for(int i = 2; i <= N; i++) {
			if(aliquotVal[i] >= i) continue;
			
			if(i == aliquotVal[aliquotVal[i]]) {
				
				
				amicablePairs.add(createPair(aliquotVal[i], i));
			}
		}
		
		return amicablePairs;
	}
	
}
