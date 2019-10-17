//Aj Shah
//CS 3345.004
//Professor Chida
//Project 6

import java.util.ArrayList;
import java.util.List;

class Root
{
    private List<String> root;
    private String city;

    //Constructor
    public Root()
    {
        root = new ArrayList<>();
    }

    //getter
    public String getNode()
    {
        return this.city;
    }
    //setter
    public void setNode(String Node)
    {
        this.city = Node;
    }

    public void add(String city)
    {
        root.add(city);
    }
    public void delete(String city)
    {
        root.remove(city);
    }

    public Boolean exists(String city)
    {
        if(root.contains(city))
        {
            return true;
        }
        return false;
    }
}