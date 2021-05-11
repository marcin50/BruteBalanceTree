/**
 * BruteBalanceTreeDriver.java
 * <p>
 * Marcin Lenart 
 * Course code removed to prevent plagarism
 * This application generates a balanced BruteBalanceTree.
 * Two tests are performed to demonstrate the balancing techniques implemented.
 * Test 1: demonstrates balancing after adding new elements to the tree.
 * Test 2: demonstrates balancing after removing elements from the tree.
 */

public class BruteBalanceTreeDriver {
    public static void main(String[] args) {
        test1();
        test2();

    }

    //adding and balancing test
    public static void test1() {
        System.out.println("\n****Test 1- Adding and balancing****");
        BruteBalanceTree testTree1 = new BruteBalanceTree();

        testTree1.addElement(8);
        testTree1.addElement(2);
        testTree1.printTree();

        testTree1.addElement(19);
        testTree1.printTree();
        System.out.println("\"At this point balancing didn't affect the expected structure.\"\n");

        testTree1.addElement(19);
        testTree1.printTree();

        testTree1.addElement(79);
        testTree1.printTree();
        System.out.println("\"At this point the right child of 19 would have been too long. \"");
        System.out.println("\"The tree has been rebalanced to maintain efficiency. \"\n");
        System.out.println("***************************************");

    }

    //removing and balancing test
    public static void test2() {
        System.out.println("\n****Test 2 - Removing and balancing****");
        System.out.println("Generated tree with 15 elements...");
        BruteBalanceTree testTree2 = new BruteBalanceTree();

        testTree2.addElement(8);
        testTree2.addElement(1);
        testTree2.addElement(5);
        testTree2.addElement(30);
        testTree2.addElement(4);
        testTree2.addElement(9);
        testTree2.addElement(77);
        testTree2.addElement(3);
        testTree2.addElement(36);
        testTree2.addElement(19);
        testTree2.addElement(77);
        testTree2.addElement(2);
        testTree2.addElement(96);
        testTree2.addElement(12);
        testTree2.addElement(3);
        testTree2.printTree();

        testTree2.removeElement(9);
        testTree2.printTree();

        testTree2.removeAllOccurrences(3);
        testTree2.printTree();

        testTree2.removeMin();
        testTree2.printTree();

        System.out.println("***************************************");
    }

}
