// Name: Aj Shah
// Class: 3345
// Section: 004
// Semester: Spring 2019
// Project: 1

import java.util.Scanner;

public class Project1
{
    public static void main(String[] args)
    {
        int n = 0;
        Scanner input = new Scanner(System.in);
        boolean error = true; //for validation purposes (error = true -> error found, error = false -> error not found)

        //input validation
        while(error)
        {
            System.out.println("Enter an integer greater than 1: ");
            //if the input is an integer
            if(input.hasNextInt())
            {
                n = input.nextInt();
                //if the input is NOT greater than 1
                if(n < 2)
                {
                    System.out.println("Number must be an integer greater than 1, Try Again!");
                    error = true;
                }
                else
                {
                    error = false;
                }
            }
            //if the input is NOT an integer
            else
            {
                System.out.println("Invalid Input, Try Again!");
                input.next();
                error = true;
            }
        }

        //Creation of boolean array based on value of n
        boolean primes[] = new boolean[n+1];
        for(int t = 0; t <= n; t++) //entire array set to true;
        {
            primes[t] = true;
        }
        //setting 0 and 1 to false because they are assumed not prime
        primes[0] = false;
        primes[1] = false;

        //algorithm
        for(int x = 2; x*x <= n; x++)
        {
            if(primes[x] == true)
            {
                for(int y = x*2; y <= n; y+=x)
                {
                    primes[y] = false;
                }
            }
        }

        //prints out all prime numbers in array
        for(int i = 0; i <= n; i++)
        {
            if(primes[i] == true)
            {
                System.out.print(i + " ");
            }
        }
    }
}
