//Khandaker Abid    115478345   214 R30
/**
 * The Tree class is the class overlooking a collection of 9-ary TreeNodes. Has only a root reference.
 */
import java.util.Scanner;
public class Tree {
    public TreeNode root;
    /**
     * Default no-arg constructor, also the only one used.
     */
    public Tree() {
        root = null;
    }
    /**
     * Getter method for the root node of the Tree
     * @return the root node of the Tree
     */
    public TreeNode getRoot() {
        return root;
    }
    /**
     * Setter method for the root node of the Tree
     * @param root the new root node of the Tree
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    /**
     * Creates a new node instantly with the given parameters and appends it to the end of the node with parentLabel as its label in the tree.
     *      If it's a root being added, parentLabel is ignored.
     * @param label the label of the appended node
     * @param prompt the prompt of the appended node
     * @param message the message of the appended node
     * @param parentLabel the label of the node the new node is being appended to
     * @return true if the addition was successful, false if not
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel) {
        if(root==null) {
            root = new TreeNode(label, message, prompt);
            return true;
        }
        TreeNode parent = this.getNodeReference(parentLabel);
        if(parent == null) {
            return false;
        } else {
            try {
                parent.setLink(new TreeNode(label, message, prompt, parent));
                return true;
            } catch(IllegalArgumentException ex) {
                return false;
            }
        }
    }
    /**
     * Traverses the tree through this method's associated TreeNode counterpart searching for the right label on a node
     * @param label the label being searched through the nodes
     * @return the target node if it was found, null if it wasn't found
     */
    public TreeNode getNodeReference(String label) {
        if(root!=null) {
            TreeNode[] place = new TreeNode[1];
            try {
                TreeNode x = root.getNodeReference(label, place);
            } catch (Exception e) {
                return place[0];
            }
        }
        return null;
    }
    /**
     * Prints the Tree's node contents in PreOrder. Calls on the TreeNode preOrder counterpart.
     */
    public void preOrder() {
        if(root!=null) {
            root.preOrder();
        }
    }
    /**
     * Begins a question-answer session similar to the help option in the main method of this assignment.
     *      Created for rubric and specification, but I found a better way to implement quesitoning in the main method
     *      so I didn't need to call on this.
     * @throws EmptyTreeException if the tree is empty
     */
    public void beginSession() throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException();
        }
        Scanner s = new Scanner(System.in);
        TreeNode cur = root;
        char choice;
        while(!cur.isLeaf()) {
            System.out.println(cur.getMessage());
            for (int i = 0; i < cur.getLinkSum(); i++) {
                System.out.println(((int) (i + 1)) + ") " + cur.getLinks()[i].getPrompt());
            }
            System.out.print("B) Go Back.\n0) Exit Session.\nChoice> ");
            choice = s.nextLine().charAt(0);
            if (choice == 'B' || choice == 'b') {
                if (cur.getParent() != null) {
                    cur = cur.getParent();
                    System.out.println();
                } else {
                    System.out.println("\nSorry, there's no prompt before this. Please continue.\n");
                }
                continue;
            }
            if(choice<48||choice>57) {
                System.out.println("\nSorry, your input isn't of a specified option. Please try again.\n");
                continue;
            }
            choice = (char)(choice-48);
            if(choice == 0) {
                System.out.println("\nAlright! Exiting...\n");
                return;
            }
            choice--;
            if(cur.getLinkSum()<=choice) {
                System.out.println("\nSorry, your input isn't of a specified option. Please try again.\n");
                continue;
            }
            cur = cur.getLinks()[choice];
            System.out.println();
        }
        System.out.println(cur.getMessage() + "\n");
        System.out.println("That's all. Thank you!");
        //This works, but I found a better way to do what this method does that is incompatible with this method's signature,
        //so I just didn't use this method. It was never stated it needs to be used, just implemented correctly, so it should be fine
    }
}
