public class RedBlackTree<E extends Comparable<E>>
{
    static final boolean RED = false;
    static final boolean BLACK = true;
    private Node<E> nil = new Node<>(); //Leaf nodes are nil
    private Node<E> root = nil;

    //Inner Class
    static class Node<E extends Comparable<E>>
    {
        E element;
        Node<E> leftChild;
        Node<E> rightChild;
        Node<E> parent;
        boolean color; //False->RED and True->BLACK

        int numLeft = 0; //used for insertion and determining height
        int numRight = 0; //used for insertion and determining height


        Node() //default Constructor (no element given)
        {
            leftChild = null;
            rightChild = null;
            parent = null;
            color = true;

            numLeft = 0;
            numRight = 0;
        }
        Node(E e) //Constructor (element given)
        {
            element = e;
        }
    }

    public RedBlackTree() //Constructor
    {
        root.leftChild = nil;
        root.rightChild = nil;
        root.parent = nil;
    }

    private boolean isNil(Node<E> node) //checks whether node is nil. (NULL)
    {
        if(node == nil)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean insert(E e) //public function for insert, calls private function for insertion.
    {
        if(insert(new Node<>(e)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean insert(Node<E> newNode) //inserts newNode BUT tree will be unbalanced.
    {
        Node<E> nilNode = nil;
        Node<E> rootNode = root;
        while (!isNil(rootNode)) //determine where the newNode goes
        {
            nilNode = rootNode;
            if (newNode.element.compareTo(rootNode.element) < 0) //if newNode is less than current Node (root node)
            {
                rootNode.numLeft++; //go left
                rootNode = rootNode.leftChild; //root node gets updated, run through loop again with new root node
            }
            else if (newNode.element.compareTo(rootNode.element) > 0) //if newNode is greater than current Node (root node)
            {
                rootNode.numRight++; //go right
                rootNode = rootNode.rightChild; //root node gets updated, run through loop again with new root node
            }
            else
            {
                return false;
            }
        }
        newNode.parent = nilNode;
        //Based on the element of nilNode, make it left or right child of newNode
        if (isNil(nilNode))
        {
            root = newNode;
        }
        else if (newNode.element.compareTo(nilNode.element) < 0)
        {
            nilNode.leftChild = newNode;
        }
        else
        {
            nilNode.rightChild = newNode;
        }

        //add children nil nodes to new node;
        newNode.leftChild = nil;
        newNode.rightChild = nil;
        newNode.color = RED;

        insertNext(newNode); //Unbalanced tree after inserting newNode, call insertNext

        return true;
    }

    private void insertNext(Node<E> newNode)
    {
        Node<E> nilNode = nil;

        while (newNode.parent.color == RED) //Check for RBT property violation
        {
            if (newNode.parent == newNode.parent.parent.leftChild) //if newNode's parent is equal to the grandparent of newNode's leftchild
            {
                nilNode = newNode.parent.parent.rightChild;
                if (nilNode.color == RED) //Change color to BLACK, IF nilNode is red
                {
                    newNode.parent.color = BLACK;
                    nilNode.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                }
                else if (newNode == newNode.parent.rightChild) //if newNode is a rightChild and nilNode is black
                {
                    newNode = newNode.parent;
                    leftRotate(newNode); //Balance needed
                }
                else //if newNode is a leftChild and nilNode is black
                {
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    rightRotate(newNode.parent.parent); //Balance needed
                }
            }

            else //newNode's parent is the rightChild of it's parent
            {
                nilNode = newNode.parent.parent.leftChild;
                if (nilNode.color == RED)  //change color to black, if nilNode is Red
                {
                    newNode.parent.color = BLACK;
                    nilNode.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                }
                else if (newNode == newNode.parent.leftChild) //if nilNode is black and newNode is a leftChild
                {
                    newNode = newNode.parent;
                    rightRotate(newNode); //Balance needed
                }
                else //if nilNode is black and newNode is a rightChild
                {
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    leftRotate(newNode.parent.parent); //Balance needed
                }
            }
        }
        root.color = BLACK;
    }

    private void leftRotate(Node<E> newNode)
    {
        leftRotateNext(newNode); //update the numLeft and numRight values
        Node<E> node;
        node = newNode.rightChild;
        newNode.rightChild = node.leftChild;

        if (!isNil(node.leftChild)) //checks for leftChild of node
        {
            node.leftChild.parent = newNode;
        }
        node.parent = newNode.parent;

        if (isNil(newNode.parent)) //if newNode's parent is nil
        {
            root = node;
        }
        else if (newNode.parent.leftChild == newNode) //if newNode is equal to it's parent's leftChild
        {
            newNode.parent.leftChild = node;
        }
        else //if newNode is equal to it's parent's rightChild
        {
            newNode.parent.rightChild = node;
        }
        node.leftChild = newNode;
        newNode.parent = node; //Balance complete
    }

    private void leftRotateNext(Node<E> newNode)
    {
        if (isNil(newNode.leftChild) && isNil(newNode.rightChild.leftChild))
        {
            newNode.numLeft = 0;
            newNode.numRight = 0;
            newNode.rightChild.numLeft = 1;
        }
        else if (isNil(newNode.leftChild) && !isNil(newNode.rightChild.leftChild))
        {
            newNode.numLeft = 0;
            newNode.numRight = 1 + newNode.rightChild.leftChild.numLeft + newNode.rightChild.leftChild.numRight;
            newNode.rightChild.numLeft = 2 + newNode.rightChild.leftChild.numLeft + newNode.rightChild.leftChild.numRight;
        }
        else if (!isNil(newNode.leftChild) && isNil(newNode.rightChild.leftChild))
        {
            newNode.numRight = 0;
            newNode.rightChild.numLeft = 2 + newNode.leftChild.numLeft + newNode.leftChild.numRight;

        }
        else
        {
            newNode.numRight = 1 + newNode.rightChild.leftChild.numLeft + newNode.rightChild.leftChild.numRight;
            newNode.rightChild.numLeft = 3 + newNode.leftChild.numLeft + newNode.leftChild.numRight + newNode.rightChild.leftChild.numLeft
                    + newNode.rightChild.leftChild.numRight;
        }

    }
    private void rightRotate(Node<E> newNode)
    {
        rightRotateNext(newNode); //update the numLeft and numRight values
        Node<E> node = newNode.leftChild;
        newNode.leftChild = node.rightChild;

        if (!isNil(node.rightChild)) //checks for rightChild of node
        {
            node.rightChild.parent = newNode;
        }
        node.parent = newNode.parent;

        if (isNil(newNode.parent)) //if newNode's parent is nil
        {
            root = node;
        }
        else if (newNode.parent.rightChild == newNode) //if newNode is equal to it's parent's rightChild
        {
            newNode.parent.rightChild = node;
        }
        else //if newNode is equal to it's parent's leftChild
        {
            newNode.parent.leftChild = node;
        }
        node.rightChild = newNode;
        newNode.parent = node; //Balance Complete

    }

    private void rightRotateNext(Node<E> newNode)
    {
        if (isNil(newNode.rightChild) && isNil(newNode.leftChild.rightChild))
        {
            newNode.numRight = 0;
            newNode.numLeft = 0;
            newNode.leftChild.numRight = 1;
        }
        else if (isNil(newNode.rightChild) && !isNil(newNode.leftChild.rightChild))
        {
            newNode.numRight = 0;
            newNode.numLeft = 1 + newNode.leftChild.rightChild.numRight + newNode.leftChild.rightChild.numLeft;
            newNode.leftChild.numRight = 2 + newNode.leftChild.rightChild.numRight + newNode.leftChild.rightChild.numLeft;
        }
        else if (!isNil(newNode.rightChild) && isNil(newNode.leftChild.rightChild))
        {
            newNode.numLeft = 0;
            newNode.leftChild.numRight = 2 + newNode.rightChild.numRight + newNode.rightChild.numLeft;
        }
        else
        {
            newNode.numLeft = 1 + newNode.leftChild.rightChild.numRight + newNode.leftChild.rightChild.numLeft;
            newNode.leftChild.numRight = 3 + newNode.rightChild.numRight + newNode.rightChild.numLeft + newNode.leftChild.rightChild.numRight + newNode.leftChild.rightChild.numLeft;
        }
    }

    public boolean contains(E e)
    {
        Node<E> currentNode = root; //start at root and proceed down the tree
        while (!isNil(currentNode))
        {
            if (currentNode.element.equals(e)) //if element was found
            {
                return true;
            }
            else if (currentNode.element.compareTo(e) < 0)  //go right
            {
                currentNode = currentNode.rightChild;
            }
            else //go left
            {
                currentNode = currentNode.leftChild;
            }
        }
        return false; //if tree is empty
    }

    //public function for toString, calls private function for toString.
    public String toString()
    {
        String printTree = toString(root);
        return printTree;
    }

    private String toString(Node<E> node) //Prints in prOrderTraversal
    {
        if (node == nil)
        {
            return "";
        }

        String left = "";
        String right = "";
        String str = "";

        if (node.color == BLACK)
        {
            str = str + node.element + " ";
        }
        else
        {
            str = str + "*" + node.element + " ";
        }
        left = toString(node.leftChild);
        right = toString(node.rightChild);
        return str + left + right;
    }
}