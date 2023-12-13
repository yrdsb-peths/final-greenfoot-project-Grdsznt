import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    
    Label lives, scoreCard;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1, false);
        
        lives = new Label(0, 80);
        addObject(lives, 50, 50);
           
        Spaceship player = new Spaceship();
        addObject(player, 710, 540);
        
        lives.setValue(getObjects(Spaceship.class).get(0).lives);
        
        addObstacle();
    }
    
    public void addObstacle() {
        int randx = Greenfoot.getRandomNumber(1420);
        int randObs = Greenfoot.getRandomNumber(3);
        if (randObs == 0) {
            Stone s = new Stone();
            addObject(s, randx,30);
        } else if (randObs == 1) {
            Rock r = new Rock();
            addObject(r, randx, 30);
        }else if (randObs == 2){
            LittleRock lr = new LittleRock();
            addObject(lr, randx, 30);
        } else {
            PowerUp p = new PowerUp();
            addObject(p, randx, 30);
        }
        
    }
    
    public void setLives(int life) {
        lives.setValue(life);
    }
    
    public void gameOver() {
        Label over = new Label("GAME OVER", 100);
        addObject(over, 710, 415);
    }
}
