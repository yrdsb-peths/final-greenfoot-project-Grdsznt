import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Power Up class to upgrade your spaceship
 * 
 * @author Edwin Dong
 */
public class PowerUp extends Actor
{
    // Initialize a direction (only 2 options), an Image array and an Animation timer
    public boolean dir = false;
    GreenfootImage[] spin = new GreenfootImage[24];
    SimpleTimer aniTimer = new SimpleTimer();
    
    // animation index for the array
    public int indx = 1;
    
    /**
     * Class constructor for PowerUp
     * 
     * @param dir A direction to send the power up in (either diagonally to the left or diagonally to the right)
     */
    public PowerUp(boolean dir) {
        // Set the instance variable direction
        this.dir = dir;
        
        // Initialize the spin array to house frames of the power up
        for (int i = 0;i<24;i++) {
            spin[i] = new GreenfootImage("images/pup" + i + ".png"); 
            spin[i].scale(75, 75);
        }
        
        // Initially set the rock's image to the first one
        setImage(spin[0]);
        
        // Mark the timer for the animation
        aniTimer.mark();
    }
    
    /**
     * Method to animate the Power Up
     * 
     */
    public void animate() {
        // If the timer has not yet reached 75 milliseconds, exit
        if (aniTimer.millisElapsed() < 100) return;
        
        // Otherwise, mark timer
        aniTimer.mark();
        
        // Set the PowerUp's image to the next one in the array
        setImage(spin[indx]);
        
        // Increase the index, make it wrap around
        indx = (indx+1) % 24;
    }
    public void act()
    {
        // Get the world
        MyWorld world = (MyWorld) getWorld();
        
        // Animate the Power Up
        animate();
        
        // Make the Power Up move in a given direction
        if (dir) {
            setLocation(getX()+4, getY()+4);
        } else {
            setLocation(getX()-4, getY()+4);
        }
        
        // If reaches border, set to other side
        if (getX() > 1430) {
            setLocation(0, getY());
        } else if (getX() < 0) {
            setLocation(1430, getY());
        }
        
        // If the power up is at the bottom border
        if (getY() >= 835) {
            // If there are more than 0 lives, decrement the number of obstacles
            if (world.getObjects(Spaceship.class).get(0).lives != 0) {
                world.numObs--;
                
                // If there are no more obstacles, get a random number between 0 and world's difficulty
                // Then increment to spawn at least one, then add it to the world
                if (world.numObs == 0) {
                    int rand = Greenfoot.getRandomNumber(world.difficulty);
                    rand++;
                    for (int i = 0;i<rand;i++) {
                        world.addObstacle();
                    }
                    world.numObs = rand;
                }
            }            
            
            // Remove the power up from the world
            world.removeObject(this);
        }
    }
}
