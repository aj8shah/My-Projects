import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class main
{
    public static void main(String args[])
    {
        //no command line arguments
        if(args.length < 1)
        {
            System.out.println("Give Command line arguments");
            System.exit(0);
        }
        else
        {
            Scanner input = null;
            PrintWriter out = null;
            LazyBinarySearchTree lbst = new LazyBinarySearchTree();
            String text = "";
            try
            {
                input = new Scanner(new File(args[0])); //input
                File output = new File(args[1]); //output
                if(!output.exists()) //if there is no output file
                {
                    output.createNewFile();
                    out = new PrintWriter(output);
                    while(input.hasNextLine())
                    {
                        text = input.nextLine().trim();
                        String[] str = new String[text.length()]; //divide input (Ex. Insert: 3)
                        if(text.contains(":"))
                        {
                            str = text.split(":"); //divide
                        }
                        else
                        {
                            str[0] = text;
                        }
                        //Command Options
                        if(str[0] == "Insert")
                        {
                            //command given but no value
                            if(str[1] == null)
                            {
                                out.println("Error in Line: Insert");
                            }
                            else
                            {
                                try
                                {
                                    boolean insert = lbst.insert(Integer.parseInt(str[1]));
                                    out.println(insert);
                                }
                                catch (IllegalArgumentException ex)
                                {
                                    out.println("Error in insert: IllegalArgumentException raised");
                                }
                            }
                        }
                        else if(str[0] == "Delete")
                        {
                            try
                            {
                                boolean delete = lbst.delete(Integer.parseInt(str[1]));
                                out.println(delete);
                            }
                            catch (IllegalArgumentException ex)
                            {
                                out.println("Error in insert: IllegalArgumentException raised");
                            }
                        }
                        else if(str[0] == "FindMin")
                        {
                            int min = lbst.findMin();
                            out.println(min);
                        }
                        else if(str[0] == "FindMax")
                        {
                            int max = lbst.findMax();
                            out.println(max);
                        }
                        else if(str[0] == "Contains")
                        {
                            try
                            {
                                boolean contains = lbst.contains(Integer.parseInt(str[1]));
                                out.println(contains);
                            }
                            catch (Exception ex)
                            {
                                out.println("Invalid input");
                            }
                        }
                        else if(str[0] == "PrintTree")
                        {
                            String printTree = lbst.toString();
                            out.println(printTree);
                        }
                        else if(str[0] == "Height")
                        {
                            int height = lbst.height();
                            out.println(height);
                        }
                        else if(str[0] == "Size")
                        {
                            int size = lbst.size();
                            out.println(size);
                        }
                        else
                        {
                            out.println("Error in Line: " + str[0]);
                        }
                    }
                    //close files
                    input.close();
                    out.close();
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println( "Unable to open file " + input);
            }
            catch(IOException ex)
            {
                System.out.println("Error reading file" + input);
            }
        }
    }
}
