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
    // TODO: fix star
    // Comment stuff
    // Implement score and add word "lives" to make it easier
    // Make smooth ani
    
    int boost = 1, score = 0;
    public int numProj = 1, difficulty = 3;
    Label lives, scoreCard;
    Color black = new Color(0, 0, 0), white = new Color(255, 255, 255);
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1, false);
        
        lives = new Label(0, 60);
        lives.setFillColor(black);
        addObject(lives, 84, 80);
        
        scoreCard = new Label(0, 65);
        scoreCard.setLineColor(white);
        addObject(scoreCard, 1260, 87);
           
        Spaceship player = new Spaceship();
        addObject(player, 710, 540);
        
        lives.setValue(getObjects(Spaceship.class).get(0).lives);
        
        addObstacle();
    }
    
    public void addObstacle() {
        int randx = Greenfoot.getRandomNumber(1421);
        int randObs = Greenfoot.getRandomNumber(5);
        boolean randDir = (Greenfoot.getRandomNumber(2) == 0);
        if (randObs == 1) {
            Stone s = new Stone(randDir);
            addObject(s, randx,30);
        } else if (randObs == 2) {
            Rock r = new Rock(randDir);
            addObject(r, randx, 30);
        }else if (randObs == 3){
            LittleRock lr = new LittleRock(randDir);
            addObject(lr, randx, 30);
        } else {
            PowerUp p = new PowerUp(randDir);
            addObject(p, randx, 30);
        }
        
    }
    
    public void setLives(int life) {
        lives.setValue(life);
    }
    
    public void gameOver() {
        Label over = new Label("GAME OVER", 100);
        over.setLineColor(white);
        addObject(over, 710, 415);
    }
    
    public void increaseScore(int identifier) {
        if (identifier == 0) {
            score += 25 * boost;
        } else if (identifier == 1) {
            score += 75 * boost;
        } else if (identifier == 2) {
            score += 35 * boost;
        } else if (identifier == 3) {
            boost++;
        }
        scoreCard.setValue(score);
    }
}
