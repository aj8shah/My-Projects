public class AudioBook extends Book
{
    private double runningTime;

    public AudioBook() //default constructor
    {
        runningTime = 0.0;
    }
    public AudioBook(String a, String b, double c, int d, double e) //overloaded constructor
    {
        super(a,b,c,d);
        runningTime = e;
    }
    public double getRunningTime()
    {
        return runningTime;
    } //getter
    public void setRunningTime(double a)
    {
        runningTime = a;
    } //setter
    public String toString() //returns string with proper format
    {
        String title = getTitle();
        double price = getPrice() * 0.9;
        String author = getAuthor();
        int ISBN = getISBN();
        String info = "Title: " + title + " | " + "Author: " + author + " | " + "Price: $" + String.format( "%.2f", price) + " | " + "ISBN: " + ISBN + " | " + "RunningTime: " + runningTime;
        return info;
    }
}