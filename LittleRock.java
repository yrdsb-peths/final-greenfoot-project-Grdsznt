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
    public void act()
    {
        setLocation(getX(), getY()+4);
        MyWorld world = (MyWorld) getWorld();
        
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
