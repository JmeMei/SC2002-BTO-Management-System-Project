//just cchecking
package lab1;

import java.util.Scanner;

public class P1 {

	public static void main(String[] args) {
		//checking whether pushed 4th
		// TODO Auto-generated method stub
		System.out.println("Which movie fan are you?");
		System.out.println("A/a: Action movie");
		System.out.println("C/c: Comedy movie");
		System.out.println("D/d: Drama movie");
		
		Scanner sc = new Scanner(System.in); //need to import java.util.Scanner
		
		char choice;
		choice = sc.nextLine().charAt(0);
		choice = Character.toLowerCase(choice);
		
		switch(choice) {
			case('a'):
				System.out.println("Action movie fan");
				break;
			case 'c':
				System.out.println("Comedy movie fan");
				break;
			case 'd':
				System.out.println("Drama movie fan");
				break;
			default:
				System.out.println("Invalid choice");		
		}
		
		
		/* if statement
		if ((choice == 'A') || (choice == 'a'))
			printf("Action movie fan\n");
		else if ((choice == 'C') || (choice == 'c'))
			printf("Comedy movie fan\n");
		else if ((choice == 'D') || (choice == 'd'))
			printf("Drama movie fan\n");
		else
			printf("Invalid choice\n");
		*/
		
		sc.close();
	}


}
