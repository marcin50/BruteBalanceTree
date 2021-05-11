/**
 * BruteBalanceTree.java
 * <p>
 * Marcin Lenart 
 * Course code removed to prevent plagarism
 * This application generates a balanced LinkedBinarySearchTree
 * It uses the balance method when required to maintain an efficient tree structure of O(log n)
 * The tree is output using the printTree method.
 * <p>
 * An override is used on the LinkedBinarySearchTree methods so that the program can force a balance
 * each time an element is modified.
 */

import jsjf.ArrayUnorderedList;
import jsjf.BinaryTreeNode;
import jsjf.LinkedBinarySearchTree;

import jsjf.exceptions.ElementNotFoundException;
import jsjf.exceptions.EmptyCollectionException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BruteBalanceTree<T> extends LinkedBinarySearchTree<T> {


    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its natural order.  Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    @Override
    public void addElement(T element) {
        System.out.println("Element added: " + element);
        super.addElement(element);
        balance();
    }

    /**
     * Removes the first element that matches the specified target
     * element from the binary search tree and returns a reference to
     * it.  Throws a ElementNotFoundException if the specified target
     * element is not found in the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException {
        System.out.println("Attempting to remove element: " + targetElement);
        T result = super.removeElement(targetElement);
        balance();
        return result;
    }

    /**
     * Removes elements that match the specified target element from
     * the binary search tree. Throws a ElementNotFoundException if
     * the specified target element is not found in this tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException {
        System.out.println("Removing all occurrences of element: " + targetElement);
        super.removeAllOccurrences(targetElement);
        balance();
    }

    /**
     * Removes the node with the least value from the binary search
     * tree and returns a reference to its element.  Throws an
     * EmptyCollectionException if this tree is empty.
     *
     * @return a reference to the node with the least value
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        System.out.println("Removing smallest element");
        T result = super.removeMin();
        balance();
        return result;
    }

    /**
     * Removes the node with the highest value from the binary
     * search tree and returns a reference to its element. Throws an
     * EmptyCollectionException if this tree is empty.
     *
     * @return a reference to the node with the highest value
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        System.out.println("Removing largest element");
        T result = super.removeMax();
        balance();
        return result;
    }

    /**
     * Method used to balance the binary tree
     * Creates an in-order list and calls the partitionBalance helper method
     * Ideas borrowed from the quickSort partition strategy of Chapter 18
     */
    private void balance() {
        //only balance if the tree has more than 2 elements.
        if (size() > 2) {
            System.out.println("Balancing Tree...");

            Iterator<T> iter = iterator();
            List<T> inOrderList = new ArrayList<>(size());

            while (iter.hasNext()) {
                inOrderList.add(iter.next());
            }
            int max = inOrderList.size() - 1; //index of largest element

            //sets the root as the new balanced tree via the helper method
            root = partitionBalance(inOrderList, 0, max);
        }
    }

    /**
     *Creates a new balanced binary tree by partitioning each middle section of the list
     *Ideas borrowed from the quickSort partition strategy of Chapter 18
     * @param inOrderList the list to be sorted
     * @param min the minimum index in the range to be sorted
     * @param max  the maximum index in the range to be sorted
     * @return middle node or resulting recursive parent node
     */
    private BinaryTreeNode partitionBalance(List inOrderList, int min, int max) {

        if (min <= max) {
            int middle = (min + max) / 2;//split current partition in half
            //set the middle index as the current root
            BinaryTreeNode partitionNode = new BinaryTreeNode(inOrderList.get(middle));
            //recursive call for each left and right child of the current root
            partitionNode.setLeft(partitionBalance(inOrderList, min, middle - 1));
            partitionNode.setRight(partitionBalance(inOrderList, middle + 1, max));

            return partitionNode;
        } else return null;
    }

    /**
     * Helper method for visual tree representation output
     * @param quantity the amount of spaces required
     */
    public static void printWhiteSpace(int quantity) {
        for (int x = 0; x < quantity; x++)
            System.out.print(" ");

    }

    /**
     * Generates a list containing the final tree structure used for output display.
     * The helper method displayPrintTree is called to output the list
     *
     * The code is an edited version of the Java Foundations iteratorLevelOrder method from the
     * LinkedBinaryTree class.
     * The main modifications are: null was changed to "x" to allow for visual output.
     * Also, null elements were added to node children when required.
     */
    public void printTree() {
        ArrayUnorderedList<BinaryTreeNode<T>> nodes = new ArrayUnorderedList<BinaryTreeNode<T>>();
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        BinaryTreeNode<T> current;
        nodes.addToRear(root);

        while (!nodes.isEmpty()) {
            current = nodes.removeFirst();

            if (current != null) {
                tempList.addToRear(current.getElement());
                if (current.getLeft() != null)
                    nodes.addToRear(current.getLeft());
                else
                    nodes.addToRear(null); //add null for visual representation (changes to x)
                if (current.getRight() != null)
                    nodes.addToRear(current.getRight());
                else
                    nodes.addToRear(null); //add null for visual representation (changes to x)
            } else
                //replaced null with a visual representation as the character "x"
                tempList.addToRear((T) "x");
        }
        //output the final list
        displayPrintTree(tempList);
    }

    /**
     * Outputs the tree in a visual triangle format where "x" represents a null element
     * Spacing alignments were implemented as closely as possible.
     * @param treeList The final tree list to be output
     */
    public void displayPrintTree(ArrayUnorderedList treeList) {

        int nodeCounter = 0; // increments for every tree node
        int levelCounter = 0; // increments for every tree level

        while (!treeList.isEmpty()) {
            nodeCounter++;
            /*Checks if a new level is required and adds new line
             * Original similar representation:
             if (nodeCounter == 1 || nodeCounter == 2 || nodeCounter == 4 || nodeCounter == 8 || nodeCounter == 16) { */
            if (((Math.log(nodeCounter) / Math.log(2)) % 2 == 0) || ((Math.log(nodeCounter) / Math.log(2)) % 2 == 1)) {
                System.out.println(""); //adds new level
                printWhiteSpace((getHeight() + 1 - levelCounter) * 2); //adds spacing in front of new level
                levelCounter++;
            }
            System.out.print(treeList.removeFirst() + " "); //prints nodes and spacing at current level

        }
        System.out.println("");//add line after tree is formed
    }
}
