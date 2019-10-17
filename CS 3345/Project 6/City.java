//Aj Shah
//CS 3345.004
//Professor Chida
//Project 6

import java.util.Comparator;

class City implements Comparator<City>
{
    public String city;
    public int cost;

    public City() {}

    public City(String city, int cost)
    {
        this.city = city;
        this.cost = cost;
    }

    public int compare(City node1, City node2)
    {
        if (node1.cost < node2.cost)
        {
            return -1;
        }
        if (node1.cost > node2.cost)
        {
            return 1;
        }
        return 0;
    }
}
