import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Projectile Class (For Spaceship)
 * 
 * @author Edwin Dong
 */
public class Projectile extends Actor
{
    // Instance variables for the speed and the spread of the projectiles
    int spd = 5, spread;
    
    /**
     * Class constructor for PowerUp
     * 
     * @param spread Added to the x value of the move (makes it move diagonally)
     * @param spd Speed of the projectile
     */
    public Projectile (int spread, int spd){
        // Scale the current Image
        GreenfootImage image = getImage();  
        image.scale(15, 45);
        setImage(image);
        
        // Set the instance variables of spread and speed
        this.spread = spread; this.spd = spd;
    }
    public void act()
    {
        // Make the projectile move spread x and spd y
        setLocation(getX()+spread, getY()-spd);
        
        // If the projectile reached the top border, remove the projectile and exit act
        if (getY() < -5) {
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this); return;
        }
        
        // If it's touching any obstacle or powerup, remove it
        if(isTouching(LittleRock.class)){
            MyWorld world = (MyWorld) getWorld();
            removeTouching(LittleRock.class);
            
            // Increase score and decrement the number of obstacles
            world.increaseScore(1);
            world.numObs--;
            
            // If there are no more obstacles, get a random number between 0 and the world's difficulty
            // and also increment the random number since we want to spawn
            // at least one obstacle
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
            
            // Increase the Score and decrement the number of obstacles
            world.increaseScore(2);
            world.numObs--;
            
            // If there are no more obstacles, get a random number between 0 and the world's difficulty
            // and also increment the random number since we want to spawn at least one obstacle
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
            
            // Increase the Score and decrement the number of obstacles
            world.increaseScore(0);
            world.numObs--;
            
            // If there are no more obstacles, get a random number between 0 and the world's difficulty
            // and also increment the random number since we want to spawn at least one obstacle
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
            
            // Increase the Score and decrement the number of obstacles
            world.increaseScore(3);
            world.numObs--;

            // If there are no more obstacles, get a random number between 0 and the world's difficulty
            // and also increment the random number since we want to spawn at least one obstacle
            if (world.numObs == 0) {
                int rand = Greenfoot.getRandomNumber(world.difficulty);
                rand++;
                for (int i = 0;i<rand;i++) {
                    world.addObstacle();
                }
                world.numObs = rand;
            }
            
            // Increase the level of the saceship
            Spaceship s = world.getObjects(Spaceship.class).get(0);
            s.lvl++;
            
            // If not at the last level, Level Up the spaceship
            if (s.indx < 8) {
                world.getObjects(Spaceship.class).get(0).handleUp();
            }
        }  
    }
}
