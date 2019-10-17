public class DVD extends CatalogItem
{
    private String director;
    private int year;
    private int dvdcode;

    public DVD() //default constructor
    {
        director = "";
        year = 0;
        dvdcode = 0;
    }
    public DVD(String a, String b, double c, int d, int e) //overloaded constructor
    {
        super(a,c); //a->Title and c->Price
        director = b;
        year = d;
        dvdcode = e;
    }
    public String getDirector()
    {
        return director;
    } //getter
    public int getYear() //getter
    {
        return year;
    }
    public int getDvdcode()
    {
        return dvdcode;
    } //getter
    public void setDirector(String a)
    {
        director = a;
    } //setter
    public void setYear(int b) //setter
    {
        year = b;
    }
    public void setDvdcode(int c)
    {
        dvdcode = c;
    } //setter
    public String toString() ////returns string with proper format
    {
        String title = getTitle();
        double price = getPrice();
        String info = "Title: " + title + " | " + "Director: " + director + " | " + "Price: $" + String.format( "%.2f", price) + " | " + "Year: " + year + " | " + "DvdCode: " + dvdcode;
        return info;
    }

}
