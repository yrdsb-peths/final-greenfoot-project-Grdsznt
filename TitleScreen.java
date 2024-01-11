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
    private Tree BST = new Tree();
    
    int lastScore = 0;
    ArrayList<Integer> highScores;
    boolean upd = false;
    Label scoreLabel;

    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1);

        Spaceship player = new Spaceship();
        addObject(player, 420, 725);

        scoreLabel = new Label(0, 32);
        highScores = new ArrayList<Integer>();
        
        addObject(scoreLabel, getWidth()/2, getHeight()/2);
        // inOrder(BST.root, 5, 0);
        

    }

    public void addScore(int score) {
        lastScore = score;
        scoreLabel.setValue(score);
        
        highScores.add(score);
        for(int existingScore: highScores){
            System.out.println(existingScore);    
        }
        
        // BST.add(BST.root, score);
    }

    public void act() {

        if(Greenfoot.isKeyDown("1")) {
            MyWorld game = new MyWorld(this); // Pass this instance of TitleScreen to MyWorld
            Greenfoot.setWorld(game);
        }
        if (Greenfoot.isKeyDown("3")) {
            inOrder(BST.root, 5, 0);
        }
    }

    public void inOrder(Node root, int times, int spacing) {
        if (root == null || times >= 5) return;
        inOrder(root.left, times, spacing + 50);
        for (int i = 0;i<root.freq;i++) {
            Label lbl = new Label(0, 65);
            lbl.setValue(root.val);
            addObject(lbl, 300, spacing);
            spacing += 50; times++;
        }
        inOrder(root.right, times, spacing + 50);
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

    public Node add(Node cur, int val) {
        if (cur == null) return new Node(val);
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
