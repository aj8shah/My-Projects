public class SinglyLinkedList<AnyType extends IDedObject>
{
    //Inner Node Class
    class Node
    {
        private AnyType data;
        private Node link;
        //default constructor
        public Node()
        {
            link = null;
            data = null;
        }
        //Overloaded constructor
        public Node(AnyType d, Node n)
        {
            data = d;
            link = n;
        }
        //getter
        public AnyType getData()
        {
            return data;
        }
        //getter
        public Node getLink()
        {
            return link;
        }
        //setter
        public void setData(AnyType d)
        {
            data = d;
        }
        //setter
        public void setLink(Node n)
        {
            link = n;
        }
    }

    private Node head;
    private int size;
    //constructor
    public SinglyLinkedList()
    {
        head = null;
        size = 0;
    }
    public void makeEmpty()
    {
        head = null;
        size = 0;
    }
    public AnyType findID(int ID)
    {
        Node nptr = head;
        while(nptr != null) //return null if the list is empty or ID cannot be found
        {
            if(nptr.getData().getID() == ID)
            {
                return nptr.getData();
            }
            else
            {
                nptr = nptr.getLink();
            }
        }
        return null;
    }
    public boolean insertAtFront(AnyType x)
    {
        Node node = new Node(x, null);
        if(head == null) //empty list. simply make node head
        {
            head = node;
            size++;
            return true;
        }
        Node nptr = head;
        while(nptr != null)
        {
            if(nptr.getData().getID() == x.getID()) //if ID already exists
            {
                return false;
            }
            else //if ID doesn't exist
            {
                nptr = nptr.getLink();
            }
        }
        node.setLink(head);
        head = node; //inserts at front
        size++;
        return true;
    }
    public AnyType deleteFromFront()
    {
        if(head == null) //empty list.
        {
            return null;
        }
        else
        {
            AnyType x = head.getData();
            head = head.getLink();
            size--;
            return x;
        }
    }
    public AnyType delete(int ID)
    {
        if(head == null) //empty list
        {
            return null;
        }
        else if(head.getData().getID() == ID) //if the ID is the head node
        {
            AnyType x = head.getData();
            head = head.getLink();
            size--;
            return x;
        }
        else
        {
            AnyType x = null;
            Node ptr = head;
            Node nptr = head.getLink();

            while(nptr != null)
            {
                if(nptr.getData().getID() == ID)
                {
                    x = nptr.getData();
                    ptr.setLink(nptr.getLink());
                    size--;
                    break;
                }
                else
                {
                    ptr = nptr;
                    nptr = nptr.getLink();
                }
            }
            return x;
        }
    }
    public void printAllRecords()
    {
        if(head == null) //if list is empty
        {
            System.out.println("No Records Found!");
        }
        Node nptr = head;
        while(nptr != null)
        {
            nptr.getData().printID();
            nptr = nptr.getLink();
        }
    }
}
