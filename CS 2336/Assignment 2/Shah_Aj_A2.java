// Shah_Aj_A2.java
// Aj Shah
// 9/22/18
// This program shows a list of items for the user.
// This program asks the user if he/she would like to add items to his/her shopping cart
// This program displays the total cost with tax
// This program allows the user to start over / clear his/her shoppingCart

import java.util.Scanner; //used for user input

public class Shah_Aj_A2
{
    public static void main(String [] args) //main
    {

        String[] books = new String[5]; //Array for book names (5)
        books[0] = "Intro to Java";
        books[1] = "Intro to C++";
        books[2] = "Python";
        books[3] = "Perl";
        books[4]= "C#";

        double[] booksPrices = new double[5]; //Array for book prices (5)
        booksPrices[0] = 45.99;
        booksPrices[1] = 89.34;
        booksPrices[2] = 100.00;
        booksPrices[3] = 25.00;
        booksPrices[4] = 49.99;

        String[] dvds = new String[5]; //Array for DVD names (5)
        dvds[0] = "Snow White";
        dvds[1] = "Cinderella";
        dvds[2] = "Dumbo";
        dvds[3] = "Bambi";
        dvds[4] = "Frozen";

        double[] dvdsPrices = new double[5]; //Array for DVD prices
        dvdsPrices[0] = 19.99;
        dvdsPrices[1] = 24.99;
        dvdsPrices[2] = 17.99;
        dvdsPrices[3] = 21.99;
        dvdsPrices[4] = 24.99;

        //Insertion Sort for bookPrices (Price low to high)
        int n = booksPrices.length; //size of booksPrices array -> 5
        for (int i=1; i<n; ++i)
        {
            double key = booksPrices[i];
            String itemName = books[i];
            int j = i-1;
            while (j>=0 && booksPrices[j] > key)
            {
                booksPrices[j+1] = booksPrices[j]; //sorts bookPrices array from low to high
                books[j+1] = books[j]; //adjusts books array from corresponding changes to bookPrices
                j = j-1;
            }
            booksPrices[j+1] = key;
            books[j+1] = itemName;
        }

        //Insertion Sort for dvdPrices (Price low to high)
        int m = dvdsPrices.length; //size of dvdsPrices -> 5
        for (int a=1; a<m; ++a)
        {
            double key1 = dvdsPrices[a];
            String itemName1 = dvds[a];
            int b = a-1;
            while(b>=0 && dvdsPrices[b] > key1)
            {
                dvdsPrices[b+1] = dvdsPrices[b]; //sorts dvdsPrices array from low to high
                dvds[b+1] = dvds[b]; //adjusts dvds array from correspoding changes to bookPrices
                b = b-1;
            }
            dvdsPrices[b+1] = key1;
            dvds[b+1] = itemName1;
        }

        String[] shoppingCart = new String[100]; //Did not use Dynamic Array, instead made shopping cart 100 elements
        double[] priceCart = new double[100]; //Maximum items in cart is 100

        int cartCounter = 0; //number of items in cart, will increase by 1 as an item is entered from inventory

        System.out.println("**Welcome to the Mustangs Books and DVDs Store**");

        int userNum = displayMenu(); //displays menu and gets the number entered by user
        while(userNum != 8)
        {
            userAdvance(userNum, books, booksPrices, dvds, dvdsPrices, cartCounter, shoppingCart, priceCart); //calls function to perform action based on user input
            userNum = displayMenu(); //displays menu and gets the number entered by user
        }
    }
    //This function displays the menu and gets an int input from user.
    //If the number entered by the user is invalid, it will call displayMenu again.
    //returns a valid int given by user. (1-8)
    public static int displayMenu()
    {
        System.out.println("Choose from the following options: ");
        System.out.println("1 - Browse books Inventory (price low to high)");
        System.out.println("2 - Browse DVDs inventory (price low to high)");
        System.out.println("3 – add a book to the cart");
        System.out.println("4 – add a DVD to the cart");
        System.out.println("5 - View cart");
        System.out.println("6 - Checkout");
        System.out.println("7 - Cancel Order");
        System.out.println("8 - Exit store");

        Scanner input = new Scanner(System.in); //input
        int user = input.nextInt();

        if(user < 1 || user > 8) //check to see if the number is within bounds, if not call function again (recursive)
        {
            System.out.println("This option is not acceptable");
            user = displayMenu(); //displays menu and gets the number entered by user
        }
        return user;
    }
    //This function prints out every items with prices.
    public static void displayArrays(String[] itemsArray, double[] pricesArray, String itemType)
    {
        System.out.println("Inventory Number    " + itemType + "                  Prices");
        System.out.println("-------------------------------------------------");
        for(int i = 0; i < 5; i++) //loop through entire array
        {
            System.out.println(String.format("%-20d", (i+1)) + String.format("%-22s", itemsArray[i]) + String.format("$%6.2f", pricesArray[i]));
        }
        System.out.println();
    }
    //This function gets the inventory number requested by user.
    public static int getInventoryNumber()
    {
        Scanner input = new Scanner(System.in); //input

        System.out.println("Enter the Inventory Number You Wish to Purchase");
        System.out.println("(enter -1 if you want to go back to the menu)"); //This is dealt with in userAdvance

        int user = input.nextInt();
        return user;
    }
    //This function displays all items in cart at the time the function was called.
    public static void displayCart(String[] b, double[] bp, String[] d, double[] dp, int count, String[] sc, double[] pc)
    {
        int user;
        if( count == 0) //if user inputs 5 from menu without entering any inventory numbers, then the cart will be empty
        {
            System.out.println("Your cart is empty");
            user = displayMenu(); //displays menu and gets the number entered by user
            userAdvance(user, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
        }
        else //if user inputs inventory numbers, then the cart will not be empty and this statement will execute
        {
            double total = 0; //gathers total
            System.out.println("Items             Prices");
            System.out.println("------------------------");
            for(int i = 0; i < count; i++)
            {
                System.out.println(String.format("%-17s", sc[i]) + String.format("$%6.2f", pc[i]));
                total = total + pc[i]; //increases total based on items in cart
            }
            System.out.println("------------------------");

            double tax = total * 0.0825; //calculate amount of tax given based on total
            total = total + tax; //adds tax to total (Actual Total)
            System.out.println("Total + tax:     " + String.format("$%6.2f", total));

            user = displayMenu(); //displays menu and gets the number entered by user
            userAdvance(user, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
        }
    }
    //gathers total cost with tax in cart. used when option 6 from menu is called.
    //if called when cart is empty, total cost will be $0.00
    public static double getTotal(int counter, double[] pc)
    {
        double t= 0;
        for(int i = 0; i < counter; i++)
        {
            t = t + pc[i]; //loops through array, adding all prices in cart
        }
        double tax = t * 0.0825; //calculates tax
        t = t + tax; //adds tax to total (Actual Total)
        return t;
    }
    //this function clears the shoppingCart array and the pricesCart array.
    //Used when option 7 from menu is called
    public static void clearArrays(String[] sc, double[] pc)
    {
        sc = null; //sets all values in shoppingCart to be null (of no value)
        pc = null; //sets all values in pricesCart to be null (of no value)
    }
    //*IMPORTANT FUNCTION*
    //called after user inputs a valid option from menu (displayMenu)
    //decides what is going to be done, based off user input
    public static void userAdvance(int userN, String[] b, double[] bp, String[] d, double[] dp, int count, String[] sc, double[] pc)
    {
        if(userN != 8) //if user selects 8, exit program
        {
            //Browse books inventory
            if(userN == 1) //if user selects 1
            {
                displayArrays(b, bp, "Books");

                userN = displayMenu(); //displays menu and gets the number entered by user
                userAdvance(userN, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
            }
            //Browse DVDs inventory
            else if(userN == 2) //if user selects 2
            {
                displayArrays(d, dp, "DVDs");

                userN = displayMenu(); //displays menu and gets the number entered by user
                userAdvance(userN, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
            }
            //add a book to the cart
            else if(userN == 3) //if user selects 3
            {
                int userInv = getInventoryNumber();
                while(userInv == -1)
                {
                    userN = displayMenu(); //displays menu and gets the number entered by user
                    userAdvance(userN, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
                }
                userInv = userInv - 1;
                sc[count] = b[userInv];
                pc[count] = bp[userInv];
                count++;

                userN = displayMenu(); //displays menu and gets the number entered by user
                userAdvance(userN, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
            }
            //add a dvd to the cart
            else if(userN == 4) //if user selects 4
            {
                int userInv = getInventoryNumber();
                while(userInv == -1)
                {
                    userN = displayMenu(); //displays menu and gets the number entered by user
                    userAdvance(userN, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
                }
                userInv = userInv - 1;
                sc[count] = d[userInv];
                pc[count] = dp[userInv];
                count++;

                userN = displayMenu(); //displays menu and gets the number entered by user
                userAdvance(userN, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
            }
            //view cart
            else if(userN == 5) //if user selects 5
            {
                displayCart(b, bp, d, dp, count, sc, pc);
            }
            //check out (displays total cost with tax)
            else if(userN == 6) //if user selects 6
            {
                double total = getTotal(count, pc);
                System.out.println("Total + tax: " + "$" + String.format("%.2f", total));

                userN = displayMenu(); //displays menu and gets the number entered by user
                userAdvance(userN, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
            }
            //Cancel order (clears shoppingCart and pricesCart)
            else if(userN == 7) //if user selects 7
            {
                clearArrays(sc, pc);
                count = 0; //sets count back to 0

                userN = displayMenu(); //displays menu and gets the number entered by user
                userAdvance(userN, b, bp, d, dp, count, sc, pc); //calls function to perform action based on user input
            }
        }
        System.exit(0); //if option 8 from menu is selected
    }

}
