import greenfoot.*;
import java.util.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class TitleScreen extends World
{
    static Tree BST = new Tree();
    
    int times = 0;
    boolean upd = false;
    Label[] lbls = new Label[5];

    public TitleScreen(int score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1);

        Spaceship player = new Spaceship();
        addObject(player, 420, 725);

        for (int i = 0;i<5;i++) {
            lbls[i] = new Label(0, 50);
        }
        
        BST.add(score);
        getLargest(BST.root); int spacing = 275;
        for (int i = 0;i<5;i++) {
            addObject(lbls[i], 420, spacing);
            spacing += 75;
        }
    
    }
    
    public TitleScreen() {
        super(1420, 830, 1);

        Spaceship player = new Spaceship();
        addObject(player, 420, 725);
        int spacing = 275;
        for (int i = 0;i<5;i++) {
            lbls[i] = new Label(0, 50);
            addObject(lbls[i], 420, spacing);
            spacing += 75;
        }
    }


    public void act() {
        if(Greenfoot.isKeyDown("1")) {
            MyWorld game = new MyWorld(); 
            Greenfoot.setWorld(game);
        }
    }

    public void getLargest(Node root) {
        if (root == null || times >= 5) return;
        getLargest(root.right);
        for (int i = 0;i<root.freq;i++) {
            lbls[times].setValue(root.val);
            times++;
        }
        getLargest(root.left);
    }
}

class Node {
    int val, freq; Node left, right;
    public Node (int val) {
        this.val = val; this.freq = 1;
        right = null; left = null;
    }

}

class Tree {
    Node root;
    public Tree() {
        root = null;
    }

    
    public void add(int val) {
        if (root == null) {
            root = new Node(val);
        } else {
            add(root, val);
        }
    }
    public Node add(Node cur, int val) {
        if (cur == null) {
            return new Node(val);
        }
        if (val < cur.val) {
            cur.left = add(cur.left, val);
        } else if (val > cur.val) {
            cur.right = add(cur.right, val);
        } else {
            cur.freq++;
        }
        return cur;
    }
    
       

}
