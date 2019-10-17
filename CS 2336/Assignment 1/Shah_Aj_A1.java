// Shah_Aj_A1.java
// Aj Shah
// 9/1/18
// This program reads in distance and time given by the user and displays them in a table.
// This program also calculates the average speed

//Required for input
import java.util.Scanner;

class Shah_Aj_A1
{
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in); //input
        // ints one to ten hold all inputs given by the user
        int one;
        int two;
        int three;
        int four;
        int five;
        int six;
        int seven;
        int eight;
        int nine;
        int ten;

        // will hold average speed once calculated
        double average;

        //gathering information from user
        System.out.println("Enter the distance Diana Nyad swam in 1974:");
        one = input.nextInt();
        System.out.println("How many hours did it take Nyad to swim the distance?");
        two = input.nextInt();
        System.out.println("Enter the distance Nyad swam in 1975:");
        three = input.nextInt();
        System.out.println("How many hours did it take Nyad to swim the distance?");
        four = input.nextInt();
        System.out.println("Enter the distance Nyad swam in 1978:");
        five = input.nextInt();
        System.out.println("How many hours did it take Nyad to swim the distance?");
        six = input.nextInt();
        System.out.println("Enter the distance Nyad swam in 1979:");
        seven = input.nextInt();
        System.out.println("How many hours did it take Nyad to swim the distance?");
        eight = input.nextInt();
        System.out.println("Enter the distance Nyad swam in 2013:");
        nine = input.nextInt();
        System.out.println("How many hours did it take Nyad to swim the distance?");
        ten = input.nextInt();

        //creating table
        System.out.println("+------------------------------------------------------------+");
        System.out.println(" Year | Distance (miles)| Time (hours) | Speed (miles/hour) ");
        System.out.println("+------------------------------------------------------------+");

        //displaying user information
        System.out.println(" 1974 | " + one + " miles        | " + two + " hours      | " + ((double) one / two) );
        System.out.println(" 1975 | " + three + " miles        | " + four + " hours      | " + ((double) three / four) );
        System.out.println(" 1978 | " + five + " miles        | " + six + " hours     | " + ((double) five / six) );
        System.out.println(" 1979 | " + seven + " miles       | " + eight + " hours     | " + ((double) seven / eight) );
        System.out.println(" 2013 | " + nine + " miles       | " + ten + " hours     | " + ((double) nine / ten) );

        //formula to calculate average speed
        average = ((double) one / two + (double) three / four + (double) five / six + (double) seven / eight + (double) nine / ten) / 5;

        //displaying average speed
        System.out.println();
        System.out.println("Diana Nyad's average speed is: " + average);
    }
}
