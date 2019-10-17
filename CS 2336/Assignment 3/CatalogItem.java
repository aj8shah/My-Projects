abstract public class CatalogItem
{
    private String title;
    private double price;

    public CatalogItem() //default constructor
    {
        title = "";
        price = 0.0;
    }
    public CatalogItem(String a, double b) //overloaded constructor
    {
        super();
        title = a;
        price = b;
    }
    public String getTitle()
    {
        return title;
    } //getter
    public double getPrice()
    {
        return price;
    } //getter
    public void setTitle(String a)
    {
        title = a;
    } //setter
    public void setPrice(double b)
    {
        price = b;
    } //setter
}
