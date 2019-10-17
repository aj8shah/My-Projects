import java.io.*;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length == 4)
        {
            PrintWriter report = null;
            PrintWriter sorted = null;
            PrintWriter unsorted = null;

            File reportFile = new File(args[1]); //report.txt
            File unsortedFile = new File(args[2]); //unsorted.txt
            File sortedFile = new File (args[3]); //sorted.txt
            int size = Integer.parseInt(args[0]); //size of array

            try
            {
                if (!reportFile.exists())
                {
                    reportFile.createNewFile();
                }
                if (!sortedFile.exists())
                {
                    sortedFile.createNewFile();
                }
                if (!unsortedFile.exists())
                {
                    unsortedFile.createNewFile();
                }

                report = new PrintWriter(reportFile);
                sorted = new PrintWriter(sortedFile);
                unsorted = new PrintWriter(unsortedFile);

                ArrayList<Integer> firstElementStrategy = QuickSorter.generateRandomList(size);
                for (int i = 0; i < firstElementStrategy.size(); i++)
                {
                    unsorted.println(firstElementStrategy.get(i));
                }

                ArrayList<Integer> randomElementStrategy = new ArrayList<Integer>();
                for (int i = 0; i < firstElementStrategy.size(); i++)
                {
                    randomElementStrategy.add(firstElementStrategy.get(i));
                }

                ArrayList<Integer> medianOfThreeRandomElementsStrategy = new ArrayList<Integer>();
                for (int i = 0; i < firstElementStrategy.size(); i++)
                {
                    medianOfThreeRandomElementsStrategy.add(firstElementStrategy.get(i));
                }

                ArrayList<Integer> medianOfThreeElementsStrategy = new ArrayList<Integer>();
                for (int i = 0; i < firstElementStrategy.size(); i++)
                {
                    medianOfThreeElementsStrategy.add(firstElementStrategy.get(i));
                }

                report.println("Array Size = " + size);
                report.println("FIRST_ELEMENT : " + QuickSorter.timedQuickSort(firstElementStrategy, QuickSorter.PivotStrategy.FIRST_ELEMENT));
                report.println("RANDOM_ELEMENT : " + QuickSorter.timedQuickSort(randomElementStrategy, QuickSorter.PivotStrategy.RANDOM_ELEMENT));
                report.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS : " + QuickSorter.timedQuickSort(medianOfThreeRandomElementsStrategy, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS));
                report.println("MEDIAN_OF_THREE_ELEMENTS : " + QuickSorter.timedQuickSort(medianOfThreeElementsStrategy, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS));

                for (int i = 0; i < firstElementStrategy.size(); i++)
                {
                    sorted.println(randomElementStrategy.get(i));
                }

                report.close();
                sorted.close();
                unsorted.close();
                System.out.println("Report is written to " + args[1]);
                System.out.println("Unsorted list is written to " + args[2]);
                System.out.println("Sorted list is written to " + args[3]);
            }

            catch (FileNotFoundException fileException) {
                System.out.println(fileException.getMessage());
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        else
            System.out.println("Please Input Valid Arguments in Command Line");
    }
}