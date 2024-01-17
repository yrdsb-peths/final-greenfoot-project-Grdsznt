import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Large Stone Obstacle
 * 
 * @author Edwin Dong
 */
public class Stone extends Actor
{
    // Initialize a direction (only 2 options), an Image array and an Animation timer
    public boolean dir = false;
    public GreenfootImage[] spin = new GreenfootImage[12];
    SimpleTimer aniTimer = new SimpleTimer();
    
    // animation index for the array
    public int indx = 1;

    /**
     * Class constructor for Stone
     * 
     * @param dir A direction to send the stone in (either diagonally to the left or diagonally to the right)
     */
    public Stone(boolean dir) {
        // Set the instance variable direction
        this.dir = dir;
        
        // Initialize the spin array to house frames of the stone
        for (int i = 0;i<12;i++) {
            spin[i] = new GreenfootImage("images/stone" + i + ".png"); 
        }
        
        // Initially set the stone's image to the first one
        setImage(spin[0]);
        
        // Mark the timer for the animation
        aniTimer.mark();
    }
    
    /**
     * Method to animate the stone
     * 
     */
    public void animate() {
        // If the timer has not yet reached 100 milliseconds, exit
        if (aniTimer.millisElapsed() < 100) return;
        
        // Otherwise, mark timer
        aniTimer.mark();
        
        // Set the stone's image to the next one in the array
        setImage(spin[indx]);
        
        // Increase the index, make it wrap around
        indx = (indx+1) % 12;
    }
    
    public void act()
    {
        // Get the world
        MyWorld world = (MyWorld) getWorld();
        
        // Call the animate method
        animate();
        
        // Move the stone in the given direction
        if (dir) {
            setLocation(getX()+2, getY()+4);
        } else {
            setLocation(getX()-2, getY()+4);
        }
        
        // If at left or right border, set to the opposite side (wrap around)
        if (getX() > 1430) {
            setLocation(0, getY());
        } else if (getX() < 0) {
            setLocation(1430, getY());
        }
        // If the stone is at the bottom border
        if (getY() >= 835) {
             // Get the current spaceship, Decrement the lives and set the lives label in the world
            Spaceship s = world.getObjects(Spaceship.class).get(0);
            s.lives--;
            world.setLives(s.lives);
            
            // If there are no more lives, display game over
            if (s.lives == 0) {
                world.gameOver();
            } else {
                // Otherwise, decrement the number of obstacles
                world.numObs--;
                
                if (world.numObs == 0) {
                    // If there are no more obstacles, get a random number between 0 and the world's difficulty
                    // and also increment the random number since we want to spawn
                    // at least one obstacle
                    int rand = Greenfoot.getRandomNumber(world.difficulty);
                    rand++;
                    
                    // Add the obstacles to the world
                    for (int i = 0;i<rand;i++) {
                        world.addObstacle();
                    }
                    
                    // Set the number of obstacles to the amount generated
                    world.numObs = rand;
                }
            }
            
            // Delete the current stone
            world.removeObject(this);
        }
    }
}
