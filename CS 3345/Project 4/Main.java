import java.util.*;
import java.io.*;

public class Main
{
    public static void main (String[] args)
    {
        boolean isInteger = false; //determines if input.txt is String or Integer
        //if true = Integer
        //if false = String
        if(args.length == 2) //Must take in two arguments from command line. (names of input file and output file)
        {
            Scanner inputFile = null;
            PrintWriter outFile = null;
            File outputFile = new File(args[1]); //give output file the name given in command line (2nd argument)
            String line = ""; //will contain each line of input in input file.
            try
            {
                inputFile = new Scanner(new File(args[0])); //inputFile is equal to the input file entered in command line
                if (!outputFile.exists()) //if output file is not found, then create one.
                {
                    outputFile.createNewFile();
                }
                outFile = new PrintWriter(outputFile);
                line = inputFile.nextLine().trim(); //divide line into two
                RedBlackTree<Integer> integerTree = new RedBlackTree<>();
                RedBlackTree<String> stringTree = new RedBlackTree<>();
                //Checks whether input file nodes are integers or strings
                if(line.compareTo("Integer") == 0)
                {
                    isInteger = true;
                }
                else if(line.compareTo("String") == 0)
                {
                    isInteger = false;
                }
                else //if neither then produce an error message and exit
                {
                    outFile.print("Only works for objects Integers and Strings");
                    outFile.close();
                    System.exit(0);
                }
                while(inputFile.hasNextLine()) //go through entire input file
                {
                    line = inputFile.nextLine().trim();
                    String[] input = new String[line.length()]; //divide line in two
                    if(line.contains(":"))
                    {
                        input = line.split(":");
                    }
                    else
                    {
                        input[0] = line;
                    }

                    //Execute specific functions based on command written in input file.
                    switch(input[0])
                    {
                        case "Insert": //if says Insert
                            if(input[1] == null) //no value given
                            {
                                outFile.println("Error in Line: Insert");
                            }
                            else
                            {
                                try
                                {
                                    if(isInteger) //do this if it is an integer
                                    {
                                        boolean successfulInsert = integerTree.insert(Integer.parseInt(input[1]));
                                        outFile.println(successfulInsert);
                                    }
                                    else //dp this if it is a string
                                    {
                                        boolean successfulInsert = stringTree.insert(input[1]);
                                        outFile.println(successfulInsert);
                                    }
                                }
                                catch (IllegalArgumentException ex) //invalid value
                                {
                                    outFile.println("Error in insert: IllegalArgumentException raised");
                                }
                            }
                            break;
                        case "Contains": //if says contains
                            try
                            {
                                if(isInteger) //do this if integer
                                {
                                    boolean successfulContains = integerTree.contains(Integer.parseInt(input[1]));
                                    outFile.println(successfulContains);
                                }
                                else //do this if string
                                {
                                    boolean successfulContains = stringTree.contains(input[1]);
                                    outFile.println(successfulContains);
                                }

                            }
                            catch (Exception ex) //invalid value
                            {
                                outFile.println("Invalid input");
                            }
                            break;
                        case "PrintTree": //if it says PrintTree
                            if(isInteger)
                            {
                                String tree = integerTree.toString();
                                outFile.println(tree);
                            }
                            else
                            {
                                String tree = stringTree.toString();
                                outFile.println(tree);
                            }
                            break;
                        case "":
                            break;
                        default:
                            outFile.println("Error in Line: " + input[0]);
                    }
                    /*
                    if(input[0] == "Insert")
                    {
                        if(input[1] == null)
                        {
                            outFile.println("Error in Line: Insert");
                        }
                        else
                        {
                            try
                            {
                                if(i)
                                {
                                    boolean insert = itree.insert(Integer.parseInt(input[1]));
                                    outFile.println(insert);
                                }
                                else
                                {
                                    boolean insert = stree.insert(input[1]);
                                    outFile.println(insert);
                                }

                            }
                            catch (IllegalArgumentException ex)
                            {
                                outFile.println("Error in insert: IllegalArgumentException raised");
                            }
                        }
                    }
                    else if(input[0] == "Contains")
                    {
                        try
                        {
                            if(i)
                            {
                                boolean con = itree.contains(Integer.parseInt(input[1]));
                                outFile.println(con);
                            }
                            else
                            {
                                boolean con = stree.contains(input[1]);
                                outFile.println(con);
                            }
                        }
                        catch (Exception ex)
                        {
                            outFile.println("Invalid input");
                        }
                    }
                    else if(input[0] == "PrintTree")
                    {
                        if(i)
                        {
                            String str = itree.toString();
                            outFile.println(str);
                        }
                        else
                        {
                            String str = stree.toString();
                            outFile.println(str);
                        }
                    }
                    else if(input[0] == "")
                    {

                    }
                    else
                    {
                        outFile.println("Error in Line: " + input[0]);
                    }
                    */
                }
                inputFile.close();
                outFile.close();
            }
            catch(FileNotFoundException ex)
            {
                System.out.println( "Unable to open file '" + inputFile + "'");
            }
            catch(IOException ex)
            {
                System.out.println("Error reading file '" + inputFile + "'");
            }
        }
        else
        {
            System.out.println("Please Input Valid Arguments in Command Line");
        }
    }

}