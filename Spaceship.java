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
    
    GreenfootImage ships[] = new GreenfootImage[9];
    
    boolean marked = false;
    boolean shot = false;
    public int lives = 3;
    private int speed = 5;
    public int lvl = 1, indx = 1, proj = 1, dashTime = 700, shootTime = 500, projSpeed = 5;
    
    public Spaceship() {
        
        for (int i = 1;i<=8;i++) { // The images were 1-indexed
            ships[i] = new GreenfootImage("images/ship" + i + ".png"); 
            ships[i].scale(75, 75);
        }
        setImage(ships[indx]);
    }
    
    public void handleUp() {
        if (lvl % 3 == 0) {
            MyWorld world = (MyWorld) getWorld();
            // change the ship
            if (indx < 8) {
                indx++;
            } // technically don't need the if, only for clarity purposes
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
            setImage(ships[indx]); 
        }
    }
    
    public void act()
    {
        // TODO: move diagonal
        
        MyWorld world = (MyWorld) getWorld();
        
        world.setLives(lives);
        
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > dashTime || !marked)) {
                timer.mark(); marked = true;
                setLocation(getX(), getY() + 50*speed);
            } else {
                setLocation(getX(), getY() + speed);
            }
        } else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > dashTime || !marked)) {
                timer.mark(); marked = true;
                setLocation(getX() + 50*speed, getY());
            } else {
                setLocation(getX() + speed, getY());
            }
        } else if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > dashTime || !marked)) {
                timer.mark(); marked = true;
                setLocation(getX() - 50*speed, getY());
            } else {
                setLocation(getX() - speed, getY());
            }
            
        } else if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            if (Greenfoot.isKeyDown("shift") && (timer.millisElapsed() > dashTime || !marked)) {
                timer.mark(); marked = true;
                setLocation(getX(), getY() - 50*speed);
            } else {
                setLocation(getX(), getY() - speed);
            }
        }
        
        if (Greenfoot.isKeyDown("space") && (projectileTimer.millisElapsed() > shootTime || !shot)) {
            projectileTimer.mark(); shot = true;
            for (int i = 0;i<proj;i++) {
                Projectile p;
                if (i % 2 == 0) {
                    p = new Projectile(i, projSpeed);
                } else {
                    p = new Projectile(-i, projSpeed);
                }
                getWorld().addObject(p, getX(), getY()-25);
            }
            
        }
        
        if (getX() < 0) {
            setLocation(0, getY());
        } else if (getX() > 1430) {
            setLocation(1430, getY());
        } else if (getY() < 0) {
            setLocation(getX(), 0);
        } else if (getY() > 840) {
            setLocation(getX(), 840);
        }
        
        
        if(isTouching(LittleRock.class)){
            removeTouching(LittleRock.class);
            lives--;
            if (lives == 0) {
                world.gameOver();
            }else {
                world.addObstacle();
            }
            // world.increaseScore();
            
        } else if (isTouching(Rock.class)) {
            removeTouching(Rock.class);
            lives--;
            if (lives == 0) {
                world.gameOver();
            } else {
                world.addObstacle();
            }
            // world.increaseScore();
            
        } else if (isTouching(Stone.class)) {
            removeTouching(Stone.class);
            lives--;
            if (lives == 0) {
                world.gameOver();
            } else {
                world.addObstacle();
            }
            // world.increaseScore();
            
        }
        
    }
}
