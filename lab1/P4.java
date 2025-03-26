package lab1;

import java.util.Scanner;

public class P4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int height; 
		
		System.out.println("Enter height: ");
		height = sc.nextInt();
		
		  // Handle error case
        if (height <= 0) {
            System.out.println("Error input!!");
            sc.close();
            return;
        }

     // Loop to control the number of rows in the pattern
        for (int i = 1; i <= height; i++) {
            
            // Inner loop to control the number of patterns printed in each row
            for (int j = 0; j < i; j++) {
                
                // Check if the row number (i) is odd
                if (i % 2 != 0) {
                    // If column index (j) is even, print "AA"
                    if (j % 2 == 0) {
                        System.out.print("AA");
                    } 
                    // If column index (j) is odd, print "BB"
                    else {
                        System.out.print("BB");
                    }
                } 
                // If row number (i) is even
                else {
                    // If column index (j) is even, print "BB"
                    if (j % 2 == 0) {
                        System.out.print("BB");
                    } 
                    // If column index (j) is odd, print "AA"
                    else {
                        System.out.print("AA");
                    }
                }
            }

            System.out.println(""); // Finished one row , move to next line
        }

        sc.close(); // Close the Scanner to prevent resource leaks
    }
		
		
	}
