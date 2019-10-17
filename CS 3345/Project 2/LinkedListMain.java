//Aj Shah
//Project 2
//3345.004
//ajs170530
//This program creates a SinglyLinkedList of Magazines. You have the option to insert, delete, and view the Linked List.

import java.util.Scanner;

public class LinkedListMain
{
    public static void main(String [] args)
    {
        SinglyLinkedList<Magazine> magazineLinkedList = new SinglyLinkedList<Magazine>(); //creates SinglyLinkedList
        int user = getInput(); //displays prompt and gets input from user
        while(user != 7) //if 7, Exit program
        {
            Scanner input = new Scanner(System.in);
            int magazineID;
            String magazineName, publisherName;
            IDedObject object;
            Magazine magazine;
            if(user == 1) //if user wants to empty the entire list
            {
                magazineLinkedList.makeEmpty();
                System.out.println("List Emptied");
                user = getInput();
            }
            else if(user == 2) //if user wants to find a magazine in the list given a magazine ID
            {
                System.out.print("ID No: ");
                magazineID = input.nextInt();
                Magazine m = magazineLinkedList.findID(magazineID);
                if(m != null)
                {
                    m.printID();
                }
                else
                {
                    System.out.println("A Magazine of that ID No. does not exist!");
                }
                user = getInput();
            }
            else if(user == 3) //if user wants to add a magazine to the front of the list
            {
                System.out.print("Enter Magazine ID: ");
                magazineID = input.nextInt();
                System.out.print("Enter Magazine Name: ");
                magazineName = input.next();
                System.out.print("Enter Publisher Name: ");
                publisherName = input.next();

                magazine = new Magazine(magazineID, magazineName, publisherName);
                if(magazineLinkedList.insertAtFront(magazine))
                {
                    System.out.println("Magazine Added");
                }
                else
                {
                    System.out.println("Magazine already exists");
                }
                user = getInput();
            }
            else if(user == 4) //if the user wants to delete the magazine at the very front of the list
            {
                object = magazineLinkedList.deleteFromFront();
                if(object != null)
                {
                    System.out.println("Magazine Deleted");
                    object.printID();
                }
                else
                {
                    System.out.println("List is empty");
                }
                user = getInput();
            }
            else if(user == 5) //if the user wants to delete a magazine given a magazine ID
            {
                System.out.print("Enter Magazine ID to delete: ");
                magazineID = input.nextInt();
                object = magazineLinkedList.delete(magazineID);
                if(object != null)
                {
                    System.out.println("Magazine Deleted");
                    object.printID();
                }
                else
                {
                    System.out.println("A Magazine of that ID No. does not exist!");
                }
                user = getInput();
            }
            else // if the user selects 6, the user wants to view all the magazines in the list.
            {
                magazineLinkedList.printAllRecords();
                user = getInput();
            }
        }
        System.out.println("Done."); //exit prompt
    }
    public static int getInput()
    {
        int x = 0;
        Scanner input = new Scanner(System.in);
        boolean error = true; //for validation purposes (error = true -> error found, error = false -> error not found)
        while(error)
        {
            System.out.println("Operations on List");
            System.out.println("1. Make Empty");
            System.out.println("2. Find ID");
            System.out.println("3. Insert at Front");
            System.out.println("4. Delete from Front");
            System.out.println("5. Delete ID");
            System.out.println("6. Print All Records");
            System.out.println("7. Done");
            System.out.println();
            System.out.print("Your Choice: ");
            if(input.hasNextInt()) //checks to see if it's an integer
            {
                x = input.nextInt();
                if(x < 1 || x > 7) //checks to see if the number is between 1 and 7
                {
                    System.out.println();
                    System.out.println("Number must be between 1 and 7");
                    error = true;
                }
                else
                {
                    error = false;
                }
            }
            else
            {
                System.out.println();
                System.out.println("Invalid Input, Try Again!");
                input.next();
                error = true;
            }
        }
        return x;
    }
}
