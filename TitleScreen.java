import greenfoot.*;
import java.util.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The titlescreen for the game, holds the high scores
 * 
 * @author Edwin Dong
 */

public class TitleScreen extends World
{
    // Create a static "Tree" Object to hold the scores for every instance
    static Tree BST = new Tree();
    
    // Number of currently displayed scores
    int displayedScores = 0;
    
    // Array of labels to display the scores
    Label[] lbls = new Label[5];

    /**
     * Class constructor for TitleScreen 
     * 
     * @param score The Previous Score After Losing
     */
    public TitleScreen(int score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1);
        
        // Add a spaceship object to the screen
        Spaceship player = new Spaceship();
        addObject(player, 420, 725);
            
        // Initialize labels in the array
        for (int i = 0;i<5;i++) {
            lbls[i] = new Label(0, 50);
        }
        
        // Add the previous score after losing to the Binary Search Tree
        BST.add(score);
        
        // Set the labels to the largest five scores 
        getLargestFive(BST.root); 
        
        // The y Position to display the labels  
        int yPos = 275;
        for (int i = 0;i<5;i++) {
            addObject(lbls[i], 420, yPos);
            yPos += 75;
        }
    }
    
    /**
     * Class constructor for TitleScreen (Overloaded)
     * Handles if there is no score to input
     * 
     */
    public TitleScreen() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1);
        
        // Add a spaceship object to the screen
        Spaceship player = new Spaceship();
        addObject(player, 420, 725);
            
        // Initialize labels in the array
        for (int i = 0;i<5;i++) {
            lbls[i] = new Label(0, 50);
        }
        
        // The y Position to display the labels  
        int yPos = 275;
        for (int i = 0;i<5;i++) {
            addObject(lbls[i], 420, yPos);
            yPos += 75;
        }
    }

    /**
     * Method that is infinitely looped 
     * 
     * @param None
     * @return None
     */
    public void act() {
        // If the key "1" is pessed down, create a new world and start the game
        if(Greenfoot.isKeyDown("1")) {
            MyWorld game = new MyWorld(); 
            Greenfoot.setWorld(game);
        }
    }

    /**
     * Method to set the labels to the largest 5 scores in the Binary Search Tree 
     * (Query the largest 5 scores)
     * 
     * @param root The node that gets passed in to traverse the tree
     * @return none
     */
    public void getLargestFive(Node parent) {
        // If the current Node is Null, or we've already displayed 5 scores, end the method call
        if (parent == null || displayedScores >= 5) return;
        
        // Recurse the right subtree of the Binary Search Tree
        // It traverses to the largest elements since it's a binary search tree
        // which is sorted with the left child smaller than the parent smaller than
        // the right child
        getLargestFive(parent.right);
        
        // At the current method call, loop for how many of the scores
        // have the same value (to allow for duplicates), then
        // set the label to the value of the current Node
        for (int i = 0;i<parent.freq;i++) {
            lbls[displayedScores].setValue(parent.val);
            displayedScores++;
        }
        
        // Recurse the left subtree of the Binary Search Tree
        // This setup (right, parent, left) traverses the tree so that
        // the largest elements are traversed first and the smallest
        // elements are traversed last, sorting it in descending order
        getLargestFive(parent.left);
        
        // Total time complexity O(heightofthetree + 5) -> O(h) -> worst case is (O(n))
    }
}


/**
 * Node class that stores the value, frequency, and references 
 * to left and right children
 * 
 */
class Node {
    // One node's value and frequency
    int val; 
    int freq; 
    
    // Left and right children
    Node left;
    Node right;
    
    /**
     * Class constructor that initializes a Node
     * 
     * @param val The value the Node should take
     */
    public Node (int val) {
        // sets the instance variables
        this.val = val; this.freq = 1;
        
        // sets the left and right child to be null initially
        right = null; left = null;
    }

}


/**
 * Tree class to implement a Binary Search Tree 
 * 
 */
class Tree {
    // Initialize the root node
    Node root;
    
    /**
     * Class constructor, initializes the root as null 
     * 
     */
    public Tree() {
        root = null;
    }

    /**
     * Method to insert a value into the tree 
     * 
     * @param val The value to insert
     * @return None
     */
    public void add(int val) {
        // If the root is null, make a new root, otherwise, call another add method
        if (root == null) {
            root = new Node(val);
        } else {
            add(root, val);
        }
    }
    
    /**
     * Method to insert a value into the tree (Overloaded)
     * 
     * @param cur the Node that is used to traverse the tree
     * @param val The value to insert
     * @return Node a Node that is returned to insert into the tree
     */
    public Node add(Node cur, int val) {
        // If the current Node is null, return a new Node with the value
        if (cur == null) {
            return new Node(val);
        }
        
        // Implements the sorted nature of a binary search tree
        // If the value you're trying to insert is less than the current
        // Node's value, recurse to the left.
        // If the value you're trying to insert is greater than the current Node's
        // value, recurse it to the right.
        // Keep recursing until you reach a null spot to add a node (it's returned)
        // If the value you're trying to insert is equal to the current value
        // Don't create a new Node, but instead increment the frequency of the value
        if (val < cur.val) {
            cur.left = add(cur.left, val);
        } else if (val > cur.val) {
            cur.right = add(cur.right, val);
        } else {
            cur.freq++;
        }
        
        // Return the current node (to make the recursion and method work and to not change
        // any other node other than the inserted one)
        return cur;
    }
}