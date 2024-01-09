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
    public GreenfootImage[] spin = new GreenfootImage[24];
    SimpleTimer aniTimer = new SimpleTimer();
    public int indx = 0;

    public LittleRock(boolean dir) {
        this.dir = dir;
        for (int i = 0;i<24;i++) {
            spin[i] = new GreenfootImage("images/lr" + i + ".png"); 
        }
        
        setImage(spin[0]);
        
        aniTimer.mark();
    }
    
    public void animate() {
        if (aniTimer.millisElapsed() < 75) return;
        
        aniTimer.mark();
        
        setImage(spin[indx]);
        
        indx = (indx+1) % 24;
    }
    
    
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        animate();
        
        if (dir) {
            setLocation(getX()+2, getY()+4);
        } else {
            setLocation(getX()-2, getY()+4);
        }
                
        if (getX() > 1430) {
            setLocation(0, getY());
        } else if (getX() < 0) {
            setLocation(1430, getY());
        }
        if (getY() >= 835) {
            Spaceship s = world.getObjects(Spaceship.class).get(0);
            s.lives--;
            world.setLives(s.lives);
            if (s.lives == 0) {
                world.gameOver();
                
            } else {
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
