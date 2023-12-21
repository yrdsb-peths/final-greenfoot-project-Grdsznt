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
    
    int spd = 5, spread;
    
    public Projectile (int spread, int spd){
        GreenfootImage image = getImage();  
        image.scale(15, 45);
        setImage(image);
        this.spread = spread; this.spd = spd;
    }
    public void act()
    {
        setLocation(getX()+spread, getY()-spd);
        MyWorld world = (MyWorld) getWorld();
        if (getY() < -5) {
            world.removeObject(this); return;
        }
        int rand = Greenfoot.getRandomNumber(world.difficulty);
        if(isTouching(LittleRock.class)){
            removeTouching(LittleRock.class);
            world.increaseScore(1);
            world.numProj--;
        } else if (isTouching(Rock.class)) {
            removeTouching(Rock.class);
            world.increaseScore(2);
            world.numProj--;
        } else if (isTouching(Stone.class)) {
            removeTouching(Stone.class);
            world.increaseScore(0);
            world.numProj--;
        } else if (isTouching(PowerUp.class)) {
            removeTouching(PowerUp.class);
            world.numProj--;
            world.increaseScore(3);
            Spaceship s = world.getObjects(Spaceship.class).get(0);
            s.lvl++;
            if (s.indx < 8) {
                world.getObjects(Spaceship.class).get(0).handleUp();
            }
        
        }
        if (world.numProj == 0) {
            for (int i = 0;i<rand;i++) {
                world.addObstacle();
            }
            world.numProj = rand;
        }
          
    }
}
