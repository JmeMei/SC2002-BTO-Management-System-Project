package lab1;

import java.util.Scanner;

public class P3 {
	private static final double RATE = 1.82;
	
	//private means this method can only be used within the same class.
	//static means this method belongs to the class itself, not an object.
	//void means this method does not return a value.
	private static void forloop(int start, int end, int increment) {
		System.out.println("For loop");
		System.out.println("US$     S$");
		System.out.println("----------");
		
		for(; start <= end; start+=increment) {
			System.out.println(start + "    " + String.format("%10.2f", start*RATE));
		}
		System.out.println("");
		
	}
	
	private static void whileloop(int start, int end, int increment) {
		System.out.println("While loop");
		System.out.println("US$     S$");
		System.out.println("----------");
		
		while(start<=end) {
			System.out.println(start + "    " + String.format("%10.1f", start*RATE));
			start+=increment;
		}
		System.out.println("");
	}
	
	private static void dowhileloop(int start, int end, int increment) {
		System.out.println("do/while loop");
		System.out.println("US$     S$");
		System.out.println("----------");
		
		do {
			System.out.println(start + "    " + String.format("%10.1f", start*RATE));
			start+=increment;
		}while(start<=end);
	}
	
	public static void main(String[] args) {
		//private	Only accessible inside the same class
		//static	Belongs to the class, not instances
		//final	Cannot be changed (constant)
		Scanner sc = new Scanner(System.in);
		int starting, ending, increment;
		
		
		
		System.out.print("starting: ");
		starting = sc.nextInt();
		
		System.out.print("ending: ");
		ending = sc.nextInt();
		
		System.out.print("increment: ");
		increment = sc.nextInt();
		
		if( (starting >= ending) || (increment > ending)) System.out.println("Error input!");
		else {
			forloop(starting, ending, increment);
			whileloop(starting, ending, increment);
			dowhileloop(starting, ending, increment);
		}
		sc.close();
		
	}
}
