import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spaceship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spaceship extends Actor
{
    /**
     * Act - do whatever the Spaceship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer projectileTimer = new SimpleTimer();
    boolean marked = false;
    boolean shot = false;
    public int lives = 3;
    
    public Spaceship() {
        GreenfootImage image = getImage();  
        image.scale(75, 75);
        setImage(image);
    }
    private int speed = 5;
    public int lvl = 1;
    public void act()
    {
        // TODO: move diagonal
        
        MyWorld world = (MyWorld) getWorld();
        
        world.setLives(lives);
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > 700 || !marked)) {
                timer.mark(); marked = true;
                setLocation(getX(), getY() + 50*speed);
            } else {
                setLocation(getX(), getY() + speed);
            }
        } else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > 700 || !marked)) {
                timer.mark(); marked = true;
                setLocation(getX() + 50*speed, getY());
            } else {
                setLocation(getX() + speed, getY());
            }
        } else if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > 700 || !marked)) {
                timer.mark(); marked = true;
                setLocation(getX() - 50*speed, getY());
            } else {
                setLocation(getX() - speed, getY());
            }
            
        } else if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > 700 || !marked)) {
                timer.mark(); marked = true;
                setLocation(getX(), getY() - 50*speed);
            } else {
                setLocation(getX(), getY() - speed);
            }
        }
        
        if (Greenfoot.isKeyDown("space") && (projectileTimer.millisElapsed() > 500 || !shot)) {
            projectileTimer.mark(); shot = true;
            Projectile p = new Projectile();
            getWorld().addObject(p, getX(), getY()-25);
        }
        
        if (getX() < 0) {
            setLocation(0, getY());
        } else if (getX() > 1430) {
            setLocation(1430, getY());
        }
        
        if(isTouching(LittleRock.class)){
            removeTouching(LittleRock.class);
            lives--;
            // world.increaseScore();
            world.addObstacle();
        } else if (isTouching(Rock.class)) {
            removeTouching(Rock.class);
            lives--;
            // world.increaseScore();
            world.addObstacle();
        } else if (isTouching(Stone.class)) {
            removeTouching(Stone.class);
            lives--;
            // world.increaseScore();
            world.addObstacle();
        }
        
    }
}
