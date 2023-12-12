import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    private int obstacle = 0;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1, false);
        
        Spaceship player = new Spaceship();
        addObject(player, 710, 540);
        
        addObstacle();
    }
    
    public void addObstacle() {
        int randx = Greenfoot.getRandomNumber(1420);
        if (obstacle == 1) {
            Stone s = new Stone();
            addObject(s, randx,30);
        } else if (obstacle == 2) {
            Rock r = new Rock();
            addObject(r, randx, 30);
        }else {
            LittleRock lr = new LittleRock();
            addObject(lr, randx, 30);
        }
        obstacle++; obstacle %= 3;
    }
}
