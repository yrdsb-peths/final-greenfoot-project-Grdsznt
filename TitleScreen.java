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

    final int MM = (int) 1e5 + 3;
    int bit[] = new int [MM];
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1);
        
        Spaceship player = new Spaceship();
        addObject(player, 420, 725);
        
    }
    
    public void addScore(int score) {
        pq.add(score);
    }
    
    public void upd(int x, int val) {
        for (; x <= MM; x += (x & -x)) {
            bit[x] += val;
        }
    }
    
    public int qryRnk(int x) {
        int sum = 0;
        for (; x > 0; x -= (x & -x)) {
            sum += bit[x];
        }
        return sum;
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("1")) {
            MyWorld game = new MyWorld();
            Greenfoot.setWorld(game);
        }
    }
}
