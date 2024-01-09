import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUp extends Actor
{
    /**
     * Act - do whatever the PowerUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean dir = false;
    public int indx = 0;
    GreenfootImage[] spin = new GreenfootImage[23];
    SimpleTimer aniTimer = new SimpleTimer();
    
    public PowerUp(boolean dir) {
        this.dir = dir;
        
        for (int i = 0;i<23;i++) {
            spin[i] = new GreenfootImage("images/pup" + i + ".png"); 
            spin[i].scale(75, 75);
        }
        
        // initially set the image to the first one
        setImage(spin[0]);
        
        // mark the timer
        aniTimer.mark();

    }
    
    public void animate() {
        if (aniTimer.millisElapsed() < 100) return;
        
        aniTimer.mark();
        
        setImage(spin[indx]);
        
        indx = (indx+1) % 23;
    }
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        
        animate();
        
        if (dir) {
            setLocation(getX()+4, getY()+4);
        } else {
            setLocation(getX()-4, getY()+4);
        }
        
        
        if (getX() > 1430) {
            setLocation(0, getY());
        } else if (getX() < 0) {
            setLocation(1430, getY());
        }
        if (getY() >= 835) {
            if (world.getObjects(Spaceship.class).get(0).lives != 0) {
                world.numObs--;
                if (world.numObs == 0) {
                    int rand = Greenfoot.getRandomNumber(world.difficulty);
                    rand++;
                    for (int i = 0;i<rand;i++) {
                        world.addObstacle();
                    }
                    world.numObs = rand;
                }
            }            
            world.removeObject(this);
        }
    }
}
