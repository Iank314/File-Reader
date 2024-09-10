/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 4
 * CSE214.R30
 */
/**
 * Represents a tree node with up to nine children and additional string data.
 */
public class TreeNode 
{
    private TreeNode[] children;
    private String label;
    private String message;
    private String prompt;

    /**
     * Constructs a TreeNode with the specified string data and initializes the children array.
     *
     * @param label   the label for the node
     * @param message the message associated with the node
     * @param prompt  the prompt associated with the node
     */
    public TreeNode(String label, String message, String prompt) 
    {
        this.label = label;
        this.message = message;
        this.prompt = prompt;
        this.children = new TreeNode[9]; 
    }

    /**
     * Gets the child nodes.
     *
     * @return the array of child nodes
     */
    public TreeNode[] getChildren() 
    {
        return children;
    }

    /**
     * Adds a child node to the first available position in the children array.
     *
     * @param child the child node to add
     * @return true if the child was added, otherwise false
     */
    public boolean addChild(TreeNode child) 
    {
        for (int i = 0; i < children.length; i++) 
        {
            if (children[i] == null) 
            {
                children[i] = child;
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the label of the node.
     *
     * @return the label of the node
     */
    public String getLabel() 
    {
        return label;
    }

    /**
     * Gets the message associated with the node.
     *
     * @return the message associated with the node
     */
    public String getMessage() 
    {
        return message;
    }

    /**
     * Gets the prompt associated with the node.
     *
     * @return the prompt associated with the node
     */
    public String getPrompt() 
    {
        return prompt;
    }

    /**
     * Displays the message or traverses to display prompts.
     */
    public void display() 
    {
        System.out.println(message);
        for (TreeNode child : children) 
        {
            if (child != null) 
            {
                System.out.println(" - " + child.getPrompt());
            }
        }
    }

    /**
     * Traverses the tree in preorder and prints the contents of the tree to the screen.
     */
    public void preOrder() 
    {
        System.out.println("Label: " + label);
        System.out.println("Prompt: " + prompt);
        System.out.println("Message: " + message + "\n");
        for (TreeNode child : children) 
        {
            if (child != null) 
            {
                child.preOrder();
            }
        }
    }
}