//Aj Shah
//ajs170530
//Assignment 4
// This program takes in text input from the user and builds a tree from the leaves to the root using nodes.

import java.util.ArrayList;
import java.util.Scanner;

public class Shah_Aj_A4
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Node> nodeList = new ArrayList<>(); //holds each node from input

        System.out.print("Please enter text: ");
        String text = input.nextLine();
        int size = text.length();
        while(text == "") //if empty validation
        {
            System.out.println("Text cannot be empty!");
            System.out.println("Please enter text: ");
            text = input.nextLine();
        }
        for(int i = 0; i < size; i++)
        {
            String split = text.substring(i, i+1); //first character in string form
            Node node = new Node(split, 1, null, null); //new node for character
            if(nodeList.contains(node)) //if same character appears
            {
                int temp = nodeList.indexOf(node);
                node = nodeList.get(temp);
                nodeList.remove(node); //remove same node
                node.increaseFreq(); //increase frequency by 1 if same character appears
            }
            int j;
            for(j = 0; (j < nodeList.size()) && (nodeList.get(j).getFreq() <= node.getFreq()); j++); //breaks at correct j
            nodeList.add(j, node); //add node to arraylist
        }
        printTable(nodeList);

        while(nodeList.size() > 1) //continue until you reach root node
        {
            System.out.println("Ready for the next step");
            input.nextLine(); //Hit Enter

            Node one = nodeList.get(0); //copy the first two nodes
            Node two = nodeList.get(1);
            nodeList.remove(0); //remove the first two nodes
            nodeList.remove(0);

            //combine node into 1
            Node node2 = new Node(one.getCharacter() + two.getCharacter(), one.getFreq() + two.getFreq(), one, two);
            System.out.println("Combined node " + one.formatString() + " with node " + two.formatString() + " to get node " + node2.formatString());
            int k;
            for(k = 0; (k < nodeList.size()) && (nodeList.get(k).getFreq() <= node2.getFreq()); k++); //breaks at correct j
            nodeList.add(k, node2); //add new node to arraylist
            printTable(nodeList);
        }
        System.out.println("DONE");
    }
    //prints arraylist
    public static void printTable(ArrayList<Node> a)
    {
        System.out.println("Character\tCount");
        for(int i = 0; i < a.size(); i++)
        {
            Node temp = a.get(i);
            System.out.println(temp.getCharacter() + "\t\t\t" + temp.getFreq());
        }
        System.out.println();
    }
}
