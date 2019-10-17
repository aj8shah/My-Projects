public class Book extends CatalogItem
{
    private String author;
    private int ISBN;

    public Book() //default constructor
    {
        author = "";
        ISBN = 0;
    }
    public Book(String a, String b, double c, int d) //overloaded constructor
    {
        super(a, c); //a->Title and c->Price
        author = b;
        ISBN = d;
    }
    public String getAuthor()
    {
        return author;
    } //getter
    public int getISBN()
    {
        return ISBN;
    } //getter
    public void setAuthor(String a)
    {
        author = a;
    } //setter
    public void setISBN(int b) //setter
    {
        ISBN = b;
    }
    public String toString() //returns string with proper format
    {
        String title = getTitle();
        double price = getPrice();
        String info = "Title: " + title + " | " + "Author: " + author + " | " + "Price: $" + String.format( "%.2f", price) + " | " + "ISBN: " + ISBN;
        return info;
    }

}
