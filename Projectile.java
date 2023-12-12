import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int upLvl = 5;
    
    public Projectile (){
        GreenfootImage image = getImage();  
        image.scale(15, 45);
        setImage(image);
    }
    public void act()
    {
        setLocation(getX(), getY()-upLvl);
        if (getY() < -5) {
            getWorld().removeObject(this);
        }
    }
}
