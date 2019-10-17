public class LazyBinarySearchTree
{
    //inner class
    private class TreeNode
    {
        int key;
        TreeNode leftChild;
        TreeNode rightChild;
        boolean deleted;

        //Constructor;
        public TreeNode(int a)
        {
            key = a;
            leftChild = null;
            rightChild = null;
            deleted = false;
        }
        //accessors
        public int getKey()
        {
            return key;
        }
        //mutators
        public void setKey(int a)
        {
            key = a;
        }
        //accessors
        public TreeNode getLeftChild()
        {
            return leftChild;
        }
        //mutators
        public void setLeftChild(TreeNode a)
        {
            leftChild = a;
        }
        //accessors
        public TreeNode getRightChild()
        {
            return rightChild;
        }
        //mutators
        public void setRightChild(TreeNode a)
        {
            rightChild = a;
        }
        //accessors
        public boolean getDeleted()
        {
            return deleted;
        }
        //mutators
        public void setDeleted(boolean a)
        {
            deleted = a;
        }
    }

    private TreeNode root; //first node

    //Constructor
    public LazyBinarySearchTree()
    {
        root = null;
    }

    public boolean insert(int key) throws IllegalArgumentException
    {
        //if key is not between 1 and 99
        if(key < 1 || key > 99)
        {
            throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
        }
        //if tree is empty
        else if(root == null)
        {
            root = new TreeNode(key);
            return true;
        }
        //if tree has nodes
        else
        {
            TreeNode currentNode = root;
            boolean logicalInsert = false;
            //left side of tree
            if(key < currentNode.getKey())
            {
                while(currentNode.getLeftChild() != null)
                {
                    currentNode = currentNode.getLeftChild();
                }
                currentNode.setLeftChild(new TreeNode(key));
                logicalInsert = true;
            }
            //right side of tree
            else if(key > currentNode.getKey())
            {
                while(currentNode.getRightChild() != null)
                {
                    currentNode = currentNode.getRightChild();
                }
                currentNode.setRightChild(new TreeNode(key));
                logicalInsert = true;
            }
            //duplicate node found
            else
            {
                if(currentNode.getDeleted())
                {
                    currentNode.setDeleted(false);
                    logicalInsert = true;
                }
                else
                {
                    logicalInsert = false;
                }
            }
            return logicalInsert;
        }

    }
    public boolean delete(int key) throws IllegalArgumentException
    {
        //if key is not between 1 and 99
        if(key < 1 || key > 99)
        {
            throw new IllegalArgumentException("Error in delete: IllegalArgumentException raised");
        }
        //if tree is empty
        else if(root == null)
        {
            return false;
        }
        else
        {
            TreeNode currentNode = root;
            while(currentNode != null)
            {
                //left side
                if(key < currentNode.getKey())
                {
                    currentNode = currentNode.getLeftChild();
                }
                //right side
                else if(key > currentNode.getKey())
                {
                    currentNode = currentNode.getRightChild();
                }
                //if key is same
                else if(key == currentNode.getKey())
                {
                    //lazy deletion
                    if(currentNode.getDeleted() == false)
                    {
                        currentNode.setDeleted(true);
                        return true;
                    }
                    //already deleted
                    else
                    {
                        return false;
                    }
                }
            }
            return false;
        }
    }
    public int findMin()
    {
        //if empty
        if(root == null)
        {
            return -1;
        }
        TreeNode currentNode = root;
        //goes through left side of tree until reaches bottom
        while(currentNode.getLeftChild() != null)
        {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode.getKey();
    }
    public int findMax()
    {
        //if empty
        if(root == null)
        {
            return -1;
        }
        TreeNode currentNode = root;
        //goes through right side of tree until reaches bottom
        while(currentNode.getRightChild() != null)
        {
            currentNode = currentNode.getRightChild();
        }
        return currentNode.getKey();
    }
    public boolean contains(int key) throws IllegalArgumentException
    {
        //if key is not between 1 and 99
        if(key < 1 || key > 99)
        {
            throw new IllegalArgumentException("Error in contains: IllegalArgumentException raised");
        }
        //if tree is empty
        else if(root == null)
        {
            return false;
        }
        TreeNode currentNode = root;
        while(currentNode != null)
        {
            //left side
            if(key < currentNode.getKey())
            {
                currentNode = currentNode.getLeftChild();
            }
            //right side
            else if(key > currentNode.getKey())
            {
                currentNode = currentNode.getRightChild();
            }
            //key found
            else if(key == currentNode.getKey())
            {
                if(currentNode.getDeleted() == false)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }
    public String toString()
    {
        String s = "";
        s = preOrderTraversal(root);
        return s;
    }
    private String preOrderTraversal(TreeNode node)
    {
        //if empty
        if(node == null)
        {
            return "";
        }
        String s = "";
        if(node.getDeleted() == true)
        {
            s = s + "*" + node.getKey() + " ";
        }
        else
        {
            s = s + node.getKey() + " ";
        }
        String left = preOrderTraversal(node.getLeftChild());
        String right = preOrderTraversal(node.getRightChild());
        return s + left + right;
    }
    public int height()
    {
        return height(root);
    }
    private int height(TreeNode node)
    {
        //if empty
        if(node == null)
        {
            return -1;
        }
        int left = height(node.getLeftChild());
        int right = height(node.getRightChild());
        if(left > right)
        {
            return left + 1;
        }
        //if right is greater than left or equal
        else
        {
            return right + 1;
        }
    }
    public int size()
    {
        if(root == null)
        {
            return 0;
        }
        else
        {
            return size(root, 0);
        }
    }
    private int size(TreeNode node, int counter)
    {
        if(node != null)
        {
            counter = counter + 1;
            //Calculate size of Left Side
            counter = size(node.getLeftChild(), counter);
            //Calculate size of Right Side
            counter = size(node.getRightChild(), counter);
        }
        return counter;
    }
}
