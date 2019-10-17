//Aj Shah
//ajs170530
//Assignment 3
// This program creates a catalog and allows the user to add and remove items in the catalog.

import java.util.ArrayList;
import java.util.Scanner;

public class Shah_Aj_A3
{
    public static ArrayList<Book> books = new ArrayList<>(); //Catalog for Books
    public static ArrayList<DVD> dvds = new ArrayList<>(); //Catalog for DVDs

    public static void main(String [] args)
    {
        displayMenu();
    }

    public static void displayMenu()
    {
        //Menu
        System.out.println("**Welcome to the Comets Books and DVDs Store (Catalog Section)**");
        System.out.println("Choose from the following options:");
        System.out.println("1 – Add Book ");
        System.out.println("2 – Add AudioBook ");
        System.out.println("3 – Add DVD");
        System.out.println("4 – Remove Book");
        System.out.println("5 – Remove DVD");
        System.out.println("6 – Display Catalog ");
        System.out.println("9 - Exit store ");

        Scanner input = new Scanner(System.in); //input
        int user = input.nextInt(); //gets user option

        //checks whether user option is valid.
        //if user option is invalid it will go through the while loop below until a valid option is picked
        while((user < 1 || user > 6))
        {
            if(user < 1)
            {
                System.out.println("This option is not acceptable");
                displayMenu();
            }
            else if(user > 6)
            {
                if(user == 9)
                {
                    //Exit Program
                    System.exit(0);
                }
                else
                {
                    System.out.println("This option is not acceptable");
                    displayMenu();
                }
            }
        }
        //if user picks option 1
        if(user == 1)
        {
            System.out.println("Enter the ISBN code: ");
            int userISBN = input.nextInt();
            input.nextLine();
            //checks to see if user enters a negative ISBN code
            //loops until user enters a valid ISBN code
            while(userISBN < 0)
            {
                System.out.println("ISBN code cannot be negative. Please enter a valid ISBN code.");
                System.out.println("Enter the ISBN code: ");
                userISBN = input.nextInt();
            }
            //if the books catalog arraylist is empty
            if(books.size() == 0)
            {
                System.out.println("Enter the Title: ");
                String userTitle = input.nextLine();
                //checks to see if user enters an empty title
                //loops until user enters a valid title
                while(userTitle == "")
                {
                    System.out.println("Title cannot be empty. Please enter a valid title.");
                    System.out.println("Enter the Title: ");
                    userTitle = input.nextLine();
                }
                System.out.println("Enter the Author: ");
                String userAuthor = input.nextLine();
                //checks to see if user enters an empty author
                //loops until user enters a valid author
                while(userAuthor == "")
                {
                    System.out.println("Author cannot be empty. Please enter a valid author.");
                    System.out.println("Enter the Author: ");
                    userAuthor = input.nextLine();
                }
                System.out.print("Enter the price: \n$");
                double userPrice = input.nextDouble();
                //checks to see if user enters a negative price
                //loops until user enters a valid price
                while(userPrice < 0.0)
                {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                    System.out.print("Enter the price: \n$");
                    userPrice = input.nextDouble();
                }

                //adds book to book arraylist
                Book b = new Book(userTitle, userAuthor, userPrice, userISBN);
                books.add(b);
                displayMenu();
            }
            //if there are existing books in the catalog arraylist
            else
            {
                //checks to see if there is an existed book with the given ISBN number
                for(int i = 0; i < books.size(); i++)
                {
                    if(books.get(i).getISBN() == userISBN)
                    {
                        System.out.println("There is already a book of that ISBN that exists!");
                        displayMenu();
                    }
                    else
                    {
                        System.out.println("Enter the Title: ");
                        String userTitle = input.nextLine();
                        //checks to see if user enters an empty title
                        //loops until user enters a valid title
                        while(userTitle == "")
                        {
                            System.out.println("Title cannot be empty. Please enter a valid title.");
                            System.out.println("Enter the Title: ");
                            userTitle = input.nextLine();
                        }
                        System.out.println("Enter the Author: ");
                        String userAuthor = input.nextLine();
                        //checks to see if user enters an empty author
                        //loops until user enters a valid author
                        while(userAuthor == "")
                        {
                            System.out.println("Author cannot be empty. Please enter a valid author.");
                            System.out.println("Enter the Author: ");
                            userAuthor = input.nextLine();
                        }
                        System.out.print("Enter the price: \n$");
                        double userPrice = input.nextDouble();
                        //checks to see if user enters a negative price
                        //loops until user enters a valid price
                        while(userPrice < 0.0)
                        {
                            System.out.println("Price cannot be negative. Please enter a valid price.");
                            System.out.print("Enter the price: \n$");
                            userPrice = input.nextDouble();
                        }

                        //adds book to book arraylist
                        Book b = new Book(userTitle, userAuthor, userPrice, userISBN);
                        books.add(b);
                        displayMenu();
                    }
                }
            }
        }
        else if(user == 2)
        {
            System.out.println("Enter the ISBN code: ");
            int userISBN = input.nextInt();
            input.nextLine();
            //checks to see if user enters a negative ISBN code
            //loops until user enters a valid ISBN code
            while(userISBN < 0)
            {
                System.out.println("ISBN code cannot be negative. Please enter a valid ISBN code.");
                System.out.println("Enter the ISBN code: ");
                userISBN = input.nextInt();
            }//if the audiobook catalog arraylist is empty
            if(books.size() == 0)
            {
                System.out.println("Enter the Title: ");
                String userTitle = input.nextLine();
                //checks to see if user enters an empty title
                //loops until user enters a valid title
                while(userTitle == "")
                {
                    System.out.println("Title cannot be empty. Please enter a valid title.");
                    System.out.println("Enter the Title: ");
                    userTitle = input.nextLine();
                }
                System.out.println("Enter the Author: ");
                String userAuthor = input.nextLine();
                //checks to see if user enters an empty author
                //loops until user enters a valid author
                while(userAuthor == "")
                {
                    System.out.println("Author cannot be empty. Please enter a valid author.");
                    System.out.println("Enter the Author: ");
                    userAuthor = input.nextLine();
                }
                System.out.print("Enter the price: \n$");
                double userPrice = input.nextDouble();
                //checks to see if user enters a negative price
                //loops until user enters a valid price
                while(userPrice < 0.0)
                {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                    System.out.print("Enter the price: \n$");
                    userPrice = input.nextDouble();
                }
                //checks to see if user enters a negative running time
                //loops until user enters a valid running time
                System.out.println("Enter the Running Time: ");
                double userRT = input.nextDouble();
                while(userRT < 0.0)
                {
                    System.out.println("Running time cannot be negative. Please enter a valid running time.");
                    System.out.println("Enter the Running Time: ");
                    userRT = input.nextDouble();
                }

                //adds audiobook to book arraylist
                AudioBook b = new AudioBook(userTitle, userAuthor, userPrice, userISBN, userRT);
                books.add(b);
                displayMenu();
            }
            //if there are existing audio in the catalog arraylist
            else
            {
                //checks to see if there is an existed book with the given ISBN number
                for(int i = 0; i < books.size(); i++)
                {
                    if(books.get(i).getISBN() == userISBN)
                    {
                        System.out.println("There is already a book of that ISBN that exists!");
                        displayMenu();
                    }
                    else
                    {
                        System.out.println("Enter the Title: ");
                        String userTitle = input.nextLine();
                        //checks to see if user enters an empty title
                        //loops until user enters a valid title
                        while(userTitle == "")
                        {
                            System.out.println("Title cannot be empty. Please enter a valid title.");
                            System.out.println("Enter the Title: ");
                            userTitle = input.nextLine();
                        }
                        System.out.println("Enter the Author: ");
                        String userAuthor = input.nextLine();
                        //checks to see if user enters an empty author
                        //loops until user enters a valid author
                        while(userAuthor == "")
                        {
                            System.out.println("Author cannot be empty. Please enter a valid author.");
                            System.out.println("Enter the Author: ");
                            userAuthor = input.nextLine();
                        }
                        System.out.print("Enter the price: \n$");
                        double userPrice = input.nextDouble();
                        //checks to see if user enters a negative price
                        //loops until user enters a valid price
                        while(userPrice < 0.0)
                        {
                            System.out.println("Price cannot be negative. Please enter a valid price.");
                            System.out.print("Enter the price: \n$");
                            userPrice = input.nextDouble();
                        }
                        System.out.println("Enter the Running Time: ");
                        double userRT = input.nextDouble();
                        //checks to see if user enters a negative running time
                        //loops until user enters a valid running time
                        while(userRT < 0.0)
                        {
                            System.out.println("Running time cannot be negative. Please enter a valid running time.");
                            System.out.println("Enter the Running Time: ");
                            userRT = input.nextDouble();
                        }
                        //adds audiobook to the book arraylist
                        AudioBook b = new AudioBook(userTitle, userAuthor, userPrice, userISBN, userRT);
                        books.add(b);
                        displayMenu();
                    }
                }
            }
        }
        else if(user == 3)
        {
            System.out.println("Enter the DVD code: ");
            int userDvDCode = input.nextInt();
            input.nextLine();
            //checks to see if user enters a negative dvd code
            //loops until user enters a valid dvd code
            while(userDvDCode < 0)
            {
                System.out.println("DVD code cannot be negative. Please enter a valid DVD code.");
                System.out.println("Enter the DVD code: ");
                userDvDCode = input.nextInt();
            }
            //if the dvd catalog arraylist is empty
            if(dvds.size() == 0)
            {
                System.out.println("Enter the Title: ");
                String userTitle = input.nextLine();
                //checks to see if user enters an empty title
                //loops until user enters a valid title
                while (userTitle == "")
                {
                    System.out.println("Title cannot be empty. Please enter a valid title.");
                    System.out.println("Enter the Title: ");
                    userTitle = input.nextLine();
                }
                System.out.println("Enter the Director: ");
                String userDirector = input.nextLine();
                //checks to see if user enters an empty director
                //loops until user enters a valid director
                while (userDirector == "")
                {
                    System.out.println("Director cannot be empty. Please enter a valid director.");
                    System.out.println("Enter the Director: ");
                    userDirector = input.nextLine();
                }
                System.out.print("Enter the price: \n$");
                double userPrice = input.nextDouble();
                //checks to see if user enters a negative price
                //loops until user enters a valid price
                while (userPrice < 0.0)
                {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                    System.out.print("Enter the price: \n$");
                    userPrice = input.nextDouble();
                }
                System.out.println("Enter the Year: ");
                int userYear = input.nextInt();
                //checks to see if user enters a negative year
                //loops until user enters a valid year
                while (userYear < 0)
                {
                    System.out.println("Year cannot be negative. Please enter a valid year.");
                    System.out.println("Enter the Year: ");
                    userYear = input.nextInt();
                }

                //adds dvd to dvd arraylist
                DVD b = new DVD(userTitle, userDirector, userPrice, userYear, userDvDCode);
                dvds.add(b);
                displayMenu();
            }
            //if there are existing dvds in the catalog arraylist
            else
            {
                //checks to see if a dvd of that isbn code that exists
                for(int i = 0; i < dvds.size(); i++) {
                    if (dvds.get(i).getDvdcode() == userDvDCode)
                    {
                        System.out.println("There is already a DVD of that code that exists!");
                        displayMenu();
                    }
                    else
                    {
                        System.out.println("Enter the Title: ");
                        String userTitle = input.nextLine();
                        //checks to see if user enters an empty title
                        //loops until user enters a valid title
                        while (userTitle == "")
                        {
                            System.out.println("Title cannot be empty. Please enter a valid title.");
                            System.out.println("Enter the Title: ");
                            userTitle = input.nextLine();
                        }
                        System.out.println("Enter the Director: ");
                        String userDirector = input.nextLine();
                        //checks to see if user enters an empty director
                        //loops until user enters a valid director
                        while (userDirector == "")
                        {
                            System.out.println("Director cannot be empty. Please enter a valid director.");
                            System.out.println("Enter the Director: ");
                            userDirector = input.nextLine();
                        }
                        System.out.print("Enter the price: \n$");
                        double userPrice = input.nextDouble();
                        //checks to see if user enters a negative price
                        //loops until user enters a valid price
                        while (userPrice < 0.0)
                        {
                            System.out.println("Price cannot be negative. Please enter a valid price.");
                            System.out.print("Enter the price: \n$");
                            userPrice = input.nextDouble();
                        }
                        System.out.println("Enter the Year: ");
                        int userYear = input.nextInt();
                        //checks to see if user enters a negative year
                        //loops until user enters a valid year
                        while (userYear < 0)
                        {
                            System.out.println("Year cannot be negative. Please enter a valid year.");
                            System.out.println("Enter the Year: ");
                            userYear = input.nextInt();
                        }

                        //adds dvd to the dvd arraylist
                        DVD b = new DVD(userTitle, userDirector, userPrice, userYear, userDvDCode);
                        dvds.add(b);
                        displayMenu();
                    }
                }
            }
        }
        //if user chooses to remove a book
        else if(user == 4)
        {
            //checks to see if there are any books in the catalog
            if(books.size() == 0)
            {
                System.out.println("There are no books in this catalog");
                displayMenu();
            }
            else
            {
                System.out.println("Enter the ISBN code of the book you would like to remove: ");
                int userISBN = input.nextInt();
                //goes through arraylist to see if there is a book of that isbn number
                for(int i = 0; i < books.size(); i++)
                {
                    //if found
                    if (books.get(i).getISBN() == userISBN)
                    {
                        books.remove(i);
                        //displays dvd catalog
                        for(int j = 0; j < books.size(); j++)
                        {
                            if(!(books.get(j) instanceof AudioBook))
                            {
                                System.out.println(books.get(j));
                            }
                            else
                            {
                                System.out.println(books.get(j));
                            }
                        }
                        displayMenu();
                    }
                    //if not found
                    else
                    {
                        System.out.println("The Book doesn't exist in the catalog.");
                        displayMenu();
                    }
                }
            }
        }
        //if user chose to remove a dvd
        else if(user == 5)
        {
            //checks to see if there are no dvds in the arraylist
            if(dvds.size() == 0)
            {
                System.out.println("There are no dvds in this catalog");
                displayMenu();
            }
            else
            {
                System.out.println("Enter the DVD code of the dvd you would like to remove: ");
                int userDvDCode = input.nextInt();
                //goes through dvds arraylist
                for(int i = 0; i < dvds.size(); i++)
                {
                    //if dvd code is found
                    if (dvds.get(i).getDvdcode() == userDvDCode)
                    {
                        dvds.remove(i);
                        //displays dvd catalog
                        for(int j = 0; j < dvds.size(); j++)
                        {
                            System.out.println(dvds.get(j));
                        }
                        displayMenu();
                    }
                    //if not found
                    else
                    {
                        System.out.println("The DVD doesn't exist in the catalog.");
                        displayMenu();
                    }
                }
            }
        }
        //if user wants to display the entire catalog, both books and dvds
        else if(user == 6)
        {
            for(int i = 0; i < books.size(); i++)
            {
                if(!(books.get(i) instanceof AudioBook))
                {
                    System.out.println(books.get(i));
                }
                else
                {
                    System.out.println(books.get(i));
                }
            }
            System.out.println("------------------------------------------------------------");
            for(int i = 0; i < dvds.size(); i++)
            {
                System.out.println(dvds.get(i));
            }
            displayMenu();
        }
        //method check
        else
        {
            System.out.println("The number inputed by the user is not going through the DisplayMenu method properly.");
            System.exit(0);
        }
    }
}
