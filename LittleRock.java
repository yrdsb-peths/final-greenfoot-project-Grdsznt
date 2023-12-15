import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LittleRock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LittleRock extends Actor
{
    /**
     * Act - do whatever the LittleRock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean dir = false;
    
    public LittleRock(boolean dir) {
        this.dir = dir;
    }
    
    public void act()
    {
        if (dir) {
            setLocation(getX()+2, getY()+4);
        } else {
            setLocation(getX()-2, getY()+4);
        }
        
        MyWorld world = (MyWorld) getWorld();
        
        if (getX() > 1430) {
            setLocation(0, getY());
        } else if (getX() < 0) {
            setLocation(1430, getY());
        }
        if (getY() >= 835) {
            world.getObjects(Spaceship.class).get(0).lives--;
            if (world.getObjects(Spaceship.class).get(0).lives == 0) {
                world.gameOver();
            }else {
                world.addObstacle();
            }
            world.removeObject(this);
        }
    }
}
