//Aj Shah
//CS 3345.004
//Professor Chida
//Project 6

import java.util.*;

public class DPriorityQueue
{
    private int cost;
    private int time;
    private List<String> nodeList;
    private Set<String> List;
    private PriorityQueue<City> priorityQueue;
    public String costAndTimeData[][];
    public String vS;
    public String vD;
    public List<Root> visitedNodeList;
    public HashMap<String, Integer> minCost;
    public HashMap<String, Integer> minTime;

    public DPriorityQueue(List<String> nodeList)
    {
        this.nodeList = nodeList;
        List = new HashSet<>();
        priorityQueue = new PriorityQueue<>(new City());
        visitedNodeList = new ArrayList<>();
        minCost = new HashMap<>();
        minTime = new HashMap<>();
    }

    public void dijkastraAlgorithm(String costInTime[][], String requiredPath[])
    {
        String vertexEval;
        vS = requiredPath[0];
        vD = requiredPath[1];
        if(requiredPath[2].equalsIgnoreCase("C") || requiredPath[2].equalsIgnoreCase("T"))
        {
            cost = 3;
            time = 2;
        }
        else
        {
            System.out.println("Error in .txt file, options must be either T or C");
        }

        this.costAndTimeData = costInTime;
        for (String vertex : nodeList)
        {
            minCost.put(vertex, Integer.MAX_VALUE);
            minTime.put(vertex, Integer.MAX_VALUE);
        }

        priorityQueue.add(new City(vS, 0));
        minCost.replace(vS,0);
        minTime.replace(vS, 0);
        while (!priorityQueue.isEmpty())
        {
            vertexEval = getVertex();
            Root evalNodeList = new Root();
            evalNodeList.setNode(vertexEval);
            List.add(vertexEval);
            nextClosestNode(vertexEval, evalNodeList);
            if(!doesVertexExist(visitedNodeList, vertexEval))
            {
                visitedNodeList.add(evalNodeList);
            }
        }
    }

    private String getVertex()
    {
        String node = priorityQueue.remove().city;
        return node;
    }

    private boolean doesVertexExist(List<Root> listOfVisitedNode, String node)
    {
        for (Root path : listOfVisitedNode)
        {
            if(path.getNode().equals(node))
            {
                return true;
            }
        }
        return false;
    }

    private void nextClosestNode(String evalNode, Root evalNodeList)
    {
        int dist;
        for (int i = 0; i< costAndTimeData.length; i++)
        {
            if(!costAndTimeData[i][0].equals(evalNode))
            {
                continue;
            }
            String temp;
            for (int j = 0; j < nodeList.size(); j++)
            {
                temp = nodeList.get(j);
                if(!costAndTimeData[i][1].equals(temp))
                {
                    continue;
                }
                if (!List.contains(temp))
                {
                    dist = minCost.get(evalNode) + Integer.parseInt(costAndTimeData[i][cost]);
                    if (dist < minCost.get(temp))
                    {
                        minCost.replace(temp,dist);
                        minTime.replace(temp, minTime.get(evalNode) + Integer.parseInt(costAndTimeData[i][time]));
                        for (Root path : visitedNodeList)
                        {
                            if(path.exists(temp))
                            {
                                path.delete(temp);
                            }
                            break;
                        }
                        evalNodeList.add(temp);
                    }
                    priorityQueue.add(new City(temp, minCost.get(temp)));
                }
            }
        }
    }

    public static List<String> rootPath(List<Root> visitedCity, String temp)
    {
        List<String> completePath = new ArrayList<String>();
        for(Root path : visitedCity)
        {
            if(!path.exists(temp))
            {
                continue;
            }
            completePath = rootPath(visitedCity, path.getNode());
            completePath.add(temp);
            return completePath;
        }
        completePath.add(temp);
        return completePath;
    }
}