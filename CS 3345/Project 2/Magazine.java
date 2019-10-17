public class Magazine implements IDedObject
{
    private int magazineID;
    private String magazineName;
    private String publisherName;

    //default constructor
    public Magazine()
    {
        magazineID = 0;
        magazineName = "";
        publisherName = "";
    }
    //overloaded constructor
    public Magazine(int ID, String name1, String name2)
    {
        magazineID = ID;
        magazineName = name1;
        publisherName = name2;
    }
    public int getID()
    {
        return magazineID;
    }
    public void printID()
    {
        System.out.println("Magazine ID: " + magazineID);
        System.out.println("Magazine Name: " + magazineName);
        System.out.println("Publisher Name: " + publisherName);
    }
}
