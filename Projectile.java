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
        
        if (getY() < -5) {
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this); return;
        }
        
        if(isTouching(LittleRock.class)){
            MyWorld world = (MyWorld) getWorld();
            removeTouching(LittleRock.class);
            world.increaseScore(1);
            world.numObs--;
            if (world.numObs == 0) {
                int rand = Greenfoot.getRandomNumber(world.difficulty);
                rand++;
                for (int i = 0;i<rand;i++) {
                    world.addObstacle();
                }
                world.numObs = rand;
            }

        } else if (isTouching(Rock.class)) {
            MyWorld world = (MyWorld) getWorld();
            removeTouching(Rock.class);
            world.increaseScore(2);
            world.numObs--;
            if (world.numObs == 0) {
                int rand = Greenfoot.getRandomNumber(world.difficulty);
                rand++;
                for (int i = 0;i<rand;i++) {
                    world.addObstacle();
                }
                world.numObs = rand;
            }

        } else if (isTouching(Stone.class)) {
            MyWorld world = (MyWorld) getWorld();
            removeTouching(Stone.class);
            world.increaseScore(0);
            world.numObs--;
            if (world.numObs == 0) {
                int rand = Greenfoot.getRandomNumber(world.difficulty);
                rand++;
                for (int i = 0;i<rand;i++) {
                    world.addObstacle();
                }
                world.numObs = rand;
            }

        } else if (isTouching(PowerUp.class)) {
            MyWorld world = (MyWorld) getWorld();
            removeTouching(PowerUp.class);
            world.numObs--;
            world.increaseScore(3);
            if (world.numObs == 0) {
                int rand = Greenfoot.getRandomNumber(world.difficulty);
                rand++;
                for (int i = 0;i<rand;i++) {
                    world.addObstacle();
                }
                world.numObs = rand;
            }
            Spaceship s = world.getObjects(Spaceship.class).get(0);
            s.lvl++;
            if (s.indx < 8) {
                world.getObjects(Spaceship.class).get(0).handleUp();
            }
        }
        
          
    }
}
