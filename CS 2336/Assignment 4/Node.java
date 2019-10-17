public class Node
{
    public String text;
    public int freq;
    public Node prev;
    public Node next;
    public Node(String a, int b, Node c, Node d)
    {
        text = a;
        freq = b;
        prev = c;
        next = d;
    }
    public String getCharacter() //getter
    {
        return text;
    }
    public int getFreq() //getter
    {
        return freq;
    }
    public String formatString() //string format, shows text with frequency
    {
        String temp = new String(text);
        temp = "{" + temp + "}" + " (weight: {" + freq + "})";
        return temp;
    }
    public void increaseFreq() //increments frequency
    {
        freq = freq + 1;
    }
}
