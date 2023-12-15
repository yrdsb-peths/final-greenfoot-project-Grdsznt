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
    
    GreenfootImage ships[] = new GreenfootImage[5];
    
    boolean marked = false;
    boolean shot = false;
    public int lives = 3;
    private int speed = 5;
    public int lvl = 1, indx = 0, proj = 1, dashTime = 700, shootTime = 500;
    
    public Spaceship() {
        GreenfootImage image = getImage();  
        image.scale(75, 75);
        setImage(image);
        
        for (int i = 0;i<8;i++) {
            ships[i] = new GreenfootImage("images/ship" + i + ".png"); 
        }
    }
    
    public void handleUp() {
        if (lvl % 3 == 0) {
            // change the ship
            if (indx <= 7) {
                indx++;
            }
            if (indx == 1) {
                speed += 2;
            } else if (indx == 2) {
                speed++; dashTime -= 200;
            } else if (indx == 3) {
                shootTime -= 100;
            } else if (indx == 4) {
                proj++; speed++;
            } else if (indx == 5) {
                dashTime -= 100;
            } else if (indx == 6) {
                proj++; shootTime -= 50;
            } else if (indx == 7) {
                proj++; speed++;
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
            for (int i = proj;i>=0;i--) {
                Projectile p;
                if (i % 2 == 0) {
                    p = new Projectile(i);
                } else {
                    p = new Projectile(-i);
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
