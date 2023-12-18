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
    
    int upLvl = 5, spread;
    
    public Projectile (int spread){
        GreenfootImage image = getImage();  
        image.scale(15, 45);
        setImage(image);
        this.spread = spread;
    }
    public void act()
    {
        setLocation(getX()+spread, getY()-upLvl);
        MyWorld world = (MyWorld) getWorld();
        if (getY() < -5) {
            world.removeObject(this); return;
        }
        if(isTouching(LittleRock.class)){
            removeTouching(LittleRock.class);
            // world.increaseScore();
            world.addObstacle();
        } else if (isTouching(Rock.class)) {
            removeTouching(Rock.class);
            // world.increaseScore();
            world.addObstacle();
        } else if (isTouching(Stone.class)) {
            removeTouching(Stone.class);
            // world.increaseScore();
            world.addObstacle();
        } else if (isTouching(PowerUp.class)) {
            removeTouching(PowerUp.class);
            world.addObstacle();
            upLvl++; 
            Spaceship s = world.getObjects(Spaceship.class).get(0);
            s.lvl++;
            if (s.indx < 8) {
                world.getObjects(Spaceship.class).get(0).handleUp();
            }
        
        }
          
    }
}
