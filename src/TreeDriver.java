//Khandaker Abid    115478345   214 R30

/**
 * The TreeDriver class houses the UI and main method of the program which uses Scanner to take in inputs and also read and convert tailored text files to a tree,
 *      as specified in the assignment. Imports Scanner, File, and FileNotFoundException classes and uses 2 helper methods, one for
 *      converting inputs to uppercase and one for reading a formatted file properly.
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class TreeDriver {
    public static void main(String[] args) {
        String menu = "L - Load a Tree.\n" +
                "H - Begin a Help Session.\n" +
                "T - Traverse the Tree in preorder.\n" +
                "Q - Quit\n" +
                "Choice> ";
        Scanner s = new Scanner(System.in);
        Tree tree = null;
        String name = "";
        char choice = ' ';
        char x;
        out:
        while(true) {
            try{
                System.out.print(menu);
                x = norm(s.nextLine().charAt(0));
                System.out.println();
                switch(x) {
                    case 'L':
                        System.out.print("Alright! Please give me the file name of this new tree> ");
                        name = s.nextLine();
                        try {
                            tree = new Tree();
                            readFile(name, tree);
                        } catch (FileNotFoundException e) {
                            System.out.println("Sorry, this text file cannot be found. Please try another text file.");
                        }
                        System.out.println("\nTree created successfully!");
                        break;
                    case 'H':
                        if(tree == null) {
                            System.out.println("Sorry, a tree was not created yet for this session. Please build a tree with 'L' first and try again.");
                            break;
                        }
                        if(tree.root.isLeaf()) {
                            System.out.println("Sorry, the tree is bare and has no choices for the root. Please add more to the tree and try again.");
                            break;
                        }
                        System.out.println("Sure! Help session starting...\n");
                        TreeNode cur = tree.root;
                        while(!cur.isLeaf()) {
                            System.out.println(cur.getMessage());
                            for (int i = 0; i < cur.getLinkSum(); i++) {
                                System.out.println(((int) (i + 1)) + ") " + cur.getLinks()[i].getPrompt());
                            }
                            System.out.print("B) Go Back.\n0) Exit Session.\nChoice> ");
                            choice = s.nextLine().charAt(0);
                            if(choice == 'B' || choice == 'b') {
                                if(cur.getParent()!=null) {
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
                                System.out.println("\nAlright! Exiting the help session...\n");
                                continue out;
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
                        System.out.println("Thank you for using this automated help service!");
                        break;
                    case 'T':
                        System.out.println("Alright! Traversing the tree in preorder...\n");
                        try {
                            tree.preOrder();
                        } catch(NullPointerException e) {
                            System.out.println("Sorry, a tree was not created yet for this session. Please build a tree with 'L' first and try again.");
                        }
                        break;
                    case 'Q':
                        System.out.println("Alright! Thank you for using our services. Terminating gracefully...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Sorry, your input isn't a specified option. Please try again.");
                }
                System.out.println();
            } catch(Exception e) {
                System.out.println("Error with your input! Please try the program again.");
                System.out.println();
            }
        }
    }
    /**
     * Normalizing function that converts a lowercase character input to uppercase assuming it's a letter.
     * @param x the character input being normalized
     * @return the input, which is either what it already was or uppercase if it was a lowercase letter previously.
     */
    public static char norm(char x) {
        if(97<=x && x<=122) {
            return (char)(x-32);
        } else {
            return x;
        }
    }
    /**
     * Helper method which helps read a formatted file as per the assignment and imports the info correctly onto a Tree
     * @param x the String of the text file itself (like "meepmeep.txt")
     * @param t the Tree object of which info from the file is being imported to
     * @throws FileNotFoundException if a file with the String name of x isn't found in the directory (assumed shouldn't be a problem)
     */
    public static void readFile(String x, Tree t) throws FileNotFoundException {
        File file = new File(x);
        Scanner read = new Scanner(file);
        String l; String p; String m; String parentl; int counter = 0; String temp;
        do {
            l = read.nextLine().trim();
        } while(l.isEmpty());
        do {
            p = read.nextLine().trim();
        } while(p.isEmpty());
        do {
            m = read.nextLine().trim();
        } while(m.isEmpty());
        t.addNode(l, p, m, "");
        while (read.hasNextLine()) {
            do {
                parentl = read.nextLine();
            } while(parentl.isEmpty());
            out:
            for(int i = 0; i < parentl.length(); i++) {
                if(parentl.charAt(i) == ' ') {
                    counter = Integer.parseInt(parentl.substring(i).trim());
                    parentl = parentl.substring(0, i);
                    break out;
                }
            }
            for(int i = 0; i < counter; i++) {
                do {
                    l = read.nextLine().trim();
                } while (l.isEmpty());
                do {
                    p = read.nextLine().trim();
                } while (p.isEmpty());
                do {
                    m = read.nextLine().trim();
                } while (m.isEmpty());
                t.addNode(l, p, m, parentl);
            }
        }
    }
}
