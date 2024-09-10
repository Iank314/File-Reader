/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 4
 * CSE214.R30
 */
/**
 * Represents a tree with a root node and methods to manipulate and traverse the tree.
 */
public class Tree 
{
    private TreeNode root;

    /**
     * Constructs an empty Tree.
     */
    public Tree() 
    {
        root = null;
    }

    /**
     * Adds a TreeNode to the tree.
     *
     * @param label       the label of the new node
     * @param prompt      the prompt of the new node
     * @param message     the message of the new node
     * @param parentLabel the label of the parent node
     * @return true if the node was successfully added, otherwise false
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel) 
    {
        TreeNode newNode = new TreeNode(label, message, prompt);
        if (root == null) 
        {
            if (parentLabel.equals("root")) 
            {
                root = newNode;
                return true;
            } 
            else 
            {
                return false;
            }
        }
        TreeNode parentNode = getNodeReference(parentLabel);
        if (parentNode == null) 
        {
            return false;
        }
        return parentNode.addChild(newNode);
    }

    /**
     * Returns a reference to the TreeNode that has the given label.
     *
     * @param label the label of the node to find
     * @return a reference to the TreeNode with the given label, or null if not found
     */
    public TreeNode getNodeReference(String label) 
    {
        return getNodeReference(root, label);
    }

    private TreeNode getNodeReference(TreeNode node, String label) 
    {
        if (node == null) 
        {
            return null;
        }
        if (node.getLabel().equals(label)) 
        {
            return node;
        }
        for (TreeNode child : node.getChildren()) 
        {
            TreeNode result = getNodeReference(child, label);
            if (result != null) 
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Traverses the tree in preorder and prints the contents of the tree to the screen.
     */
    public void preOrder() 
    {
        if (root != null) 
        {
            root.preOrder();
        }
    }

    /**
     * Starts the question and answer session.
     */
    public void beginSession() 
    {
        beginSession(root);
    }

    private void beginSession(TreeNode node) 
    {
        if (node == null) 
        {
            return;
        }
        node.display();
        for (TreeNode child : node.getChildren()) 
        {
            if (child != null) 
            {
                beginSession(child);
            }
        }
    }
}
