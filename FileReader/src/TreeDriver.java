
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File; 


/**
 * Driver class for the Tree application.
 * Contains the main method and displays a menu to use.
 */
public class TreeDriver 
{
    private static Tree tree = new Tree();

    /**
     * Main method to run the Tree application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do 
        {
            displayMenu();
            choice = scanner.nextLine().toUpperCase();
            switch (choice) 
            {
                case "L":
                {
                    System.out.print("Enter the file name: ");
                    String fileName = scanner.nextLine();
                    File currentDirectory = new File(".");
                    try 
                    {
                        System.out.println("Current working directory: " + currentDirectory.getCanonicalPath());
                    } 
                     catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                    
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) 
                    {
                        tree = new Tree(); 
                        String line;
                        String rootLabel = reader.readLine().trim();
                        String rootPrompt = reader.readLine().trim();
                        String rootMessage = reader.readLine().trim();
                        tree.addNode(rootLabel, rootPrompt, rootMessage, "root");

                        while ((line = reader.readLine()) != null) 
                        {
                            line = line.trim();
                            if (line.isEmpty()) 
                            {
                                continue; 
                            }
                            String[] parentParts = line.split(" ");
                            String parentLabel = parentParts[0];
                            int numChildren = Integer.parseInt(parentParts[1]);
                            for (int i = 0; i < numChildren; i++) 
                            {
                                String childLabel = reader.readLine().trim();
                                String childPrompt = reader.readLine().trim();
                                String childMessage = reader.readLine().trim();
                                if (!tree.addNode(childLabel, childPrompt, childMessage, parentLabel)) 
                                {
                                    System.out.println("Failed to add node with label: " + childLabel);
                                }
                            }
                        }
                        System.out.println("Tree loaded successfully.");
                    } 
                    catch (IOException e) 
                    {
                        System.out.println("Error reading file: " + e.getMessage());
                    }
                    break;
               }
                case "H":
                {
                    if (tree == null || tree.getNodeReference("root") == null) 
                    {
                        System.out.println("No tree is set up. Please load a tree first.");
                        break;
                    }
                    startHelpSession(scanner);
                    break;
                }

                case "T":
                {
                    if (tree == null || tree.getNodeReference("root") == null) 
                    {
                        System.out.println("No tree is set up. Please load a tree first.");
                        break;
                    }
                    tree.preOrder();
                    break;
                }

                case "Q":
                {
                    System.out.println("Quitting the program.");
                    break;
                }

                default:
                {
                    System.out.println("Invalid option. Please try again.");
                    break;
                }
            }
        } 
        while (choice.equals("Q") == false);
    }

    /**
     * Displays the main menu.
     */
    private static void displayMenu() 
    {
        System.out.println("Menu:");
        System.out.println("L - Load input file and build a tree");
        System.out.println("H - Start help session");
        System.out.println("T - Traverse the tree in pre-order");
        System.out.println("Q - Quit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Starts the help session.
     */
    private static void startHelpSession(Scanner scanner) 
    {
        TreeNode currentNode = tree.getNodeReference("root");
        TreeNode previousNode = null;
        while (currentNode != null) 
        {
            System.out.println(currentNode.getMessage());
            TreeNode[] children = currentNode.getChildren();
            boolean hasChildren = false;
            for (int i = 0; i < children.length; i++) 
            {
                if (children[i] != null) 
                {
                    System.out.println((i + 1) + ") " + children[i].getPrompt());
                    hasChildren = true;
                }
            }
            if (!hasChildren) 
            {
                System.out.println("Thank you for using our automated help system.");
                return;
            }
            System.out.println("B) Go back to the previous menu.");
            System.out.println("0) Exit Session.");

            String choice = scanner.nextLine();
            if (choice.equals("0")) 
            {
                System.out.println("Exiting session.");
                return;
            } 
            else if (choice.equalsIgnoreCase("B")) 
            {
                currentNode = previousNode;
            } 
            else 
            {
                int childIndex;
                try 
                {
                    childIndex = Integer.parseInt(choice) - 1;
                    if (childIndex >= 0 && childIndex < children.length && children[childIndex] != null) 
                    {
                        previousNode = currentNode;
                        currentNode = children[childIndex];
                    } 
                    else 
                    {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } 
                catch (NumberFormatException e) 
                {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
