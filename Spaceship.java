import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Spaceship Class.
 * 
 * @author Edwin Dong 
 */
public class Spaceship extends Actor
{
    // Initialize 2 timers, a sound and an array for changing image
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer projectileTimer = new SimpleTimer();
    GreenfootSound projectileSound = new GreenfootSound("blaster.mp3");
    GreenfootImage ships[] = new GreenfootImage[9];
    
    // Initialize 2 booleans to set a limit on dash and shooting
    boolean marked = false;
    boolean shot = false;
    
    // Initialize 2 ints for the ship's lives and the speed
    public int lives = 3;
    private int speed = 5;
    public int lvl = 1;
    
    // Index for array to change image
    public int indx = 1;
    
    // Number of projectiles, time to shoot (ms), time to dash (ms), and projectile seed
    private int proj = 1;
    private int dashTime = 700;
    private int shootTime = 500;
    private int projSpeed = 5;
    
    /**
     * Class constructor for SpaceShip
     * 
     */
    public Spaceship() {
        // The images were 1-indexed
        for (int i = 1;i<=8;i++) { 
            ships[i] = new GreenfootImage("images/ship" + i + ".png");
            
            // scale the image
            ships[i].scale(75, 75);
        }
        
        // Set the initial image
        setImage(ships[indx]);
    }
    
    /**
     * Method to power up the ship
     * 
     */
    public void handleUp() {
        // For every 3 levels, upgrade the ship
        if (lvl % 3 == 0) {
            // Get the world
            MyWorld world = (MyWorld) getWorld();
            
            // Change the ship
            if (indx < 8) {
                indx++;
            }
            
            // Depending on what ship it is, upgrade different traits
            if (indx == 2) {
                speed += 2; projSpeed += 2;
            } else if (indx == 3) {
                speed++; dashTime -= 200;
            } else if (indx == 4) {
                shootTime -= 100;  projSpeed++;
            } else if (indx == 5) {
                proj++; speed++; world.difficulty += 3;
            } else if (indx == 6) {
                dashTime -= 100; projSpeed++;
            } else if (indx == 7) {
                proj++; shootTime -= 50; world.difficulty += 2;
            } else if (indx == 8) {
                proj++; speed++; projSpeed++; world.difficulty++;
            }
            
            // Set the image to the ship upgrade
            setImage(ships[indx]); 
        }
    }
    
    public void act()
    {   
        // Handle moving and dashing
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            // If shift and last dash was greater than dashTime or I never marked the timer
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > dashTime || !marked)) {
                // Mark timer
                timer.mark(); marked = true;
                
                // Dash
                setLocation(getX(), getY() + 50*speed);
            } else {
                setLocation(getX(), getY() + speed);
            }
        } else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            // If shift and last dash was greater than dashTime or I never marked the timer
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > dashTime || !marked)) {
                // Mark timer
                timer.mark(); marked = true;
                
                // Dash
                setLocation(getX() + 50*speed, getY());
            } else {
                setLocation(getX() + speed, getY());
            }
        } else if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            // If shift and last dash was greater than dashTime or I never marked the timer
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > dashTime || !marked)) {
                // Mark timer
                timer.mark(); marked = true;
                
                // Dash
                setLocation(getX() - 50*speed, getY());
            } else {
                setLocation(getX() - speed, getY());
            }
            
        } else if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            // If shift and last dash was greater than dashTime or I never marked the timer
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > dashTime || !marked)) {
                // Mark timer
                timer.mark(); marked = true;
                
                // Dash
                setLocation(getX(), getY() - 50*speed);
            } else {
                setLocation(getX(), getY() - speed);
            }
        }
        
        // If space is down and haven't shot in shootTime or never marked shot timer
        if (Greenfoot.isKeyDown("space") && (projectileTimer.millisElapsed() > shootTime || !shot)) {
            // Mark timer
            projectileTimer.mark(); shot = true;
            
            // Play the sound
            projectileSound.play();
            
            // Spawn the number of projectiles proj
            for (int i = 0;i<proj;i++) {
                Projectile p;
                
                // Make the projectiles point in opposite directions
                if (i % 2 == 0) {
                    p = new Projectile(i, projSpeed);
                } else {
                    p = new Projectile(-i, projSpeed);
                }
                
                // Add the projectile to the world
                getWorld().addObject(p, getX(), getY()-25);
            }
            
        }
        
        // If at left, right, up or down border, don't let it go out
        if (getX() < 0) {
            setLocation(0, getY());
        } else if (getX() > 1430) {
            setLocation(1430, getY());
        } else if (getY() < 0) {
            setLocation(getX(), 0);
        } else if (getY() > 840) {
            setLocation(getX(), 840);
        }
        
        // If the ship touches any of the obstacles, remove the obstacles and take away a life
        if(isTouching(LittleRock.class)){
            MyWorld world = (MyWorld) getWorld();
            removeTouching(LittleRock.class);
            lives--;
            world.setLives(lives);
            if (lives == 0) {
                world.gameOver();
            }else {
                world.numObs--;
                if (world.numObs == 0) {
                    // Increment the random number by one to avoid spawning 0 obstacles
                    int rand = Greenfoot.getRandomNumber(world.difficulty)+1;
                    for (int i = 0;i<rand;i++) {
                        world.addObstacle();
                    }
                    
                    // Set the number of obstacles
                    world.numObs = rand;
                }
            } 
        } else if (isTouching(Rock.class)) {
            MyWorld world = (MyWorld) getWorld();
            removeTouching(Rock.class);
            lives--;
            world.setLives(lives);
            if (lives == 0) {
                world.gameOver();
            } else {
                world.numObs--;
                if (world.numObs == 0) {
                    // Increment the random number by one to avoid spawning 0 obstacles
                    int rand = Greenfoot.getRandomNumber(world.difficulty)+1;
                    for (int i = 0;i<rand;i++) {
                        world.addObstacle();
                    }
                    
                    // Set the number of obstacles
                    world.numObs = rand;
                }
            }
        } else if (isTouching(Stone.class)) {
            MyWorld world = (MyWorld) getWorld();
            removeTouching(Stone.class);
            lives--;
            world.setLives(lives);
            if (lives == 0) {
                world.gameOver();
            } else {
                world.numObs--;
                if (world.numObs == 0) {
                    // Increment the random number by one to avoid spawning 0 obstacles
                    int rand = Greenfoot.getRandomNumber(world.difficulty)+1;
                    for (int i = 0;i<rand;i++) {
                        world.addObstacle();
                    }
                    
                    // Set the number of obstacles
                    world.numObs = rand;
                }
            } 
        }
    }
}
