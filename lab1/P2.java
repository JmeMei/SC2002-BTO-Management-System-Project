package lab1;

import java.util.Scanner;

public class P2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int merit, salary;
		
		System.out.println("Salary range for grade A: $700 - $899");
		System.out.println("Salary range for grade B: $600 - $ 799");
		System.out.println("Salary range for grade C: $500 - $ 649");
		
		Scanner sc=new Scanner(System.in);
		
		System.out.print("salary:");
		salary = sc.nextInt();
		
		System.out.print("merit:");
		merit = sc.nextInt();
		
		char grade = '\0';
		
		//System.out.println(salary);
		//System.out.println(merit);
		
		if( (salary <= 500)|| (salary <= 649)) {
			if( merit >= 10 && salary >= 600) {
				//System.out.print("Grade B");
				grade = 'B';
			}
			else {
				//System.out.println("Grade C");
				grade = 'C';
			}
			
		}
		
		else if( (salary <= 600)|| (salary <= 799)) {
			if( merit >= 20 && salary >= 700) {
				//System.out.println("Grade A");
				grade = 'A';
			}
			else {
				//System.out.println("Grade B");
				grade = 'B';
			}
			
		}
		
		else if( (salary <= 700)|| (salary <= 899)) {
				//System.out.println("Grade A");
			grade = 'A';
		}
		
		System.out.println("salary: " + salary + ", merit: " + merit + ", Grade: " + grade);
		sc.close();
	}

}
