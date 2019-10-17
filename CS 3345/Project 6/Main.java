//Aj Shah
//CS 3345.004
//Professor Chida
//Project 6

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        if(args.length == 3)
        {
            String timeCost[][];
            String pathList[][];
            BufferedReader flightData;
            BufferedReader requestedFlightData;
            List<String> nodeList;
            PrintWriter output = new PrintWriter(args[2]);
            try
            {
                flightData = new BufferedReader(new FileReader(args[0]));
                requestedFlightData = new BufferedReader(new FileReader(args[1]));
                String temp;
                nodeList = new ArrayList<>();

                BufferedReader linesReader = new BufferedReader(new FileReader(args[0]));
                int numLinesFlightData = 0;
                while (linesReader.readLine() != null)
                {
                    numLinesFlightData++;
                }
                linesReader.close();

                BufferedReader linesReader2 = new BufferedReader(new FileReader(args[1]));
                int numLinesRequestedFlightData = 0;
                while (linesReader2.readLine() != null)
                {
                    numLinesRequestedFlightData++;
                }
                linesReader2.close();

                timeCost = new String[numLinesFlightData][4];
                pathList = new String[numLinesRequestedFlightData][3];
                int a = 0;
                int b;
                String tempNode;
                while((temp = flightData.readLine()) != null)
                {
                    b = 0;
                    StringTokenizer data = new StringTokenizer(temp,"|"); //delimiter
                    int c = 1;
                    while(c <= 2)
                    {
                        if(!nodeList.contains(tempNode = data.nextToken()))
                        {
                            timeCost[a][b++] = tempNode;
                            nodeList.add(tempNode);
                        }
                        else
                        {
                            timeCost[a][b++] = tempNode;
                        }
                        c++;
                    }

                    while(data.hasMoreTokens())
                    {
                        timeCost[a][b++] = data.nextToken();
                    }
                    a++;
                }
                a = 0;
                while((temp = requestedFlightData.readLine()) != null)
                {
                    b = 0;
                    StringTokenizer data = new StringTokenizer(temp,"|");
                    while(data.hasMoreTokens())
                    {
                        pathList[a][b++] = data.nextToken();
                    }
                    a++;
                }
                a = 1;
                for(String requestedPath[] : pathList)
                {
                    if(!(nodeList.contains(requestedPath[0])&& nodeList.contains(requestedPath[1])))
                    {
                        output.println("NO FLIGHT AVAILABLE FOR THE REQUEST");
                        continue;
                    }

                    DPriorityQueue priorityQueue = new DPriorityQueue(nodeList);
                    priorityQueue.dijkastraAlgorithm(timeCost, requestedPath);
                    for (String vertex : nodeList)
                    {
                        if(!vertex.equals(priorityQueue.vD))
                        {
                            continue;
                        }
                        List<String> list = DPriorityQueue.rootPath(priorityQueue.visitedNodeList, priorityQueue.vD);
                        output.print(list.size() - 1 + "|");
                        for (int k = 0; k < list.size(); k++)
                        {
                            output.print(list.get(k) + "|");
                        }
                        output.println(priorityQueue.minTime.get(vertex) + "|" + priorityQueue.minCost.get(vertex));
                        break;
                    }
                    a++;
                }
            }
            catch (Exception error)
            {
                System.out.println("Error:" + error.toString());
            }
            output.close();
        }
        else
        {
            System.out.println("Please Input Valid Arguments in Command Line");
        }
    }
}
