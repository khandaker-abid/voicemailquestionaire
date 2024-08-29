//Khandaker Abid    115478345   214 R30
/**
 * The TreeNode class is programmed to be a node in a 9-ary tree, with an array of 9 possible links, a label, message, prompt, and
 * added lastly an optional link to the object's parent.
 */
public class TreeNode {
    private String label;
    private String message;
    private String prompt;
    private TreeNode[] links;
    private int linkSum;
    private final int NUM_CHILD = 9;
    private TreeNode parent;
    /**
     * Default no-arg constructor, not used
     */
    public TreeNode() {
        label = null;
        message = null;
        prompt = null;
        links = new TreeNode[NUM_CHILD];
        linkSum = 0;
        parent = null;
    }
    /**
     * A constructor specifically used for a root because of a lack of parent link. Considered the primary constructor pre-extra credit.
     * @param lbl the label of the node (1, 1-1, etc.)
     * @param msg the message associated with the node (answers a question usually)
     * @param prmt the prompt of the node (usually a question)
     */
    public TreeNode(String lbl, String msg, String prmt) {
        label = lbl;
        message = msg;
        prompt = prmt;
        links = new TreeNode[NUM_CHILD];
        linkSum = 0;
        parent = null;
    }
    /**
     * My program's primary constructor for a typical node which adds a link to the node's parent out of convenience.
     * @param lbl the label of the node (1, 1-1, etc.)
     * @param msg the message associated with the node (answers a question usually)
     * @param prmt the prompt of the node (usually a question)
     * @param prnt a link to the parent of the current node in the tree (so null for root)
     */
    public TreeNode(String lbl, String msg, String prmt, TreeNode prnt) {
        label = lbl;
        message = msg;
        prompt = prmt;
        links = new TreeNode[NUM_CHILD];
        linkSum = 0;
        parent = prnt;
    }
    /**
     * Getter method for label of the node
     * @return the label of the node
     */
    public String getLabel() {
        return label;
    }
    /**
     * Getter method for message of the node
     * @return the message of the node
     */
    public String getMessage() {
        return message;
    }
    /**
     * Getter method for prompt of the node
     * @return the prompt of the node
     */
    public String getPrompt() {
        return prompt;
    }
    /**
     * Getter method for child links of the node
     * @return the child links of the node
     */
    public TreeNode[] getLinks() {
        return links;
    }
    /**
     * Getter method for number of defined links of the node
     * @return the number of defined links of the node
     */
    public int getLinkSum() {
        return linkSum;
    }
    /**
     * Getter method for parent of the node
     * @return the parent of the node
     */
    public TreeNode getParent() {
        return parent;
    }
    /**
     * Setter method for the label of the node
     * @param label the label of the node
     */
    public void setLabel(String label) {
        this.label = label;
    }
    /**
     * Setter method for the message of the node
     * @param message the message of the node
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Setter method for the prompt of the node
     * @param prompt the prompt of the node
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    /**
     * Setter method for the parent of the node
     * @param parent the parent of the node
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
    /**
     * Setter method for a child link of the node
     * @param x a child link of the node
     */
    public void setLink(TreeNode x) throws IllegalArgumentException {
        if(linkSum == NUM_CHILD) {
            throw new IllegalArgumentException();
        }
        links[linkSum] = x;
        linkSum++;
    }
    /**
     * Quick helper method to know when a node has no children (leaf)
     * @return true if no children, false otherwise
     */
    public boolean isLeaf() {
        return(linkSum==0);
    }
    /* My original recursive function that had problems
    public TreeNode getNodeReference(String lbl) {
        if(label.equals(lbl)) {
            return this;
        }
        for(int i = 0; i < linkSum; i++) {
            links[i].getNodeReference(lbl, x);
        }
        return null;
    }*/
    /**
     * Creative recursive tree check method which traverses a tree to find the target node to put to a reference storage out of scope.
     *      Meant to throw an exception if successful.
     * @param lbl the label being searched for in the nodes
     * @param x preferably a single-indexed array which will be a storage accessor for the target node after the method ends.
     * @return null if there's no target node in the tree
     * @throws Exception specifically when the target node is found so that the exception, by courtesy of Java, breaks the recursive stack
     *      causing efficiency and aiding against stack overflows. In a way the "return" function of my entire recursion.
     */
    public TreeNode getNodeReference(String lbl, TreeNode[] x) throws Exception{
        if(label.equals(lbl)) {
            x[0] = this;
            throw new Exception();
        }
        for(int i = 0; i < linkSum; i++) {
            links[i].getNodeReference(lbl, x);
        }
        return null;
    }
    /**
     * Traverses and prints the nodes in PreOrder. Naturally prints children from left to right from array linear progression.
     */
    public void preOrder() {
        System.out.println(this);
        for(int i = 0; i < linkSum; i++) {
            links[i].preOrder();
        }
    }
    /**
     * Returns a string representation which is printed in preOrder()
     * @return a string representation of a node.
     */
    public String toString() {
        return("Label: " + label + "\nPrompt: " + prompt + "\nMessage: " + message + "\n");
    }
}