import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Myworld is the game world that you play in.
 * 
 * @author Edwin Dong
 */
public class MyWorld extends World
{
    
    // int to track score and the boost constant of score
    int boost = 1;
    int score = 0;

    // Accessible public ints that hold the current number of obstacles and difficulty
    public int numObs = 1;
    public int difficulty = 3;
    
    // Labels to display the number of lives and the score
    Label lives;
    Label scoreCard;
    
    // Colors to use for the labels
    Color black = new Color(0, 0, 0);
    Color white = new Color(255, 255, 255);
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1420, 830, 1, false);
        
        // Create and display a label of lives of the ship
        lives = new Label(0, 60);
        lives.setFillColor(black);
        addObject(lives, 84, 80);
        
        // Create and display a label of the score
        scoreCard = new Label(0, 65);
        scoreCard.setLineColor(white);
        addObject(scoreCard, 1260, 87);
        
        // Create and add a spaceship object to the world
        Spaceship player = new Spaceship();
        addObject(player, 710, 540);
        
        // Set the lives label to the number of initial lives
        lives.setValue(getObjects(Spaceship.class).get(0).lives);
        
        // Call the addObstacle Method 
        addObstacle();
    }

    /**
     * Adds an Obstacle to the world
     * @param none
     * @return none
     */
    public void addObstacle() {
        // Get a random number between 0-1420 for an x position
        int randx = Greenfoot.getRandomNumber(1421);
        
        // Get a random number to between 0-3 to choose the type of obstacle
        int randObs = Greenfoot.getRandomNumber(4);
        
        // Get a random direction for the obstacle to move in
        boolean randDir = (Greenfoot.getRandomNumber(2) == 0);
        
        // Depending on what the obstacle is, spawn an object of an obstacle
        // at the randomly generated x coordinate and the y coordinate 
        // close to the top of the screen
        if (randObs == 0) {
            Stone s = new Stone(randDir);
            addObject(s, randx,30);
        } else if (randObs == 1) {
            Rock r = new Rock(randDir);
            addObject(r, randx, 30);
        } else if (randObs == 2){
            LittleRock lr = new LittleRock(randDir);
            addObject(lr, randx, 30);
        } else {
            PowerUp p = new PowerUp(randDir);
            addObject(p, randx, 30);
        }

    }
    /**
     * Sets the lives of the ship
     * @param life It's the amount of lives
     * @return None
     */
    public void setLives(int life) {
        lives.setValue(life);
    }
    /**
     * Displays a "Game over" label, creates a new titlescreen, and sets the world
     * @param None
     * @return None
     */
    public void gameOver() {
        //Create a Label and add it to the screen
        Label over = new Label("GAME OVER", 100);
        over.setLineColor(white);
        addObject(over, 710, 415);
        
        // Delay and set world to a new titlescreen
        Greenfoot.delay(120);
        TitleScreen titleScreen = new TitleScreen(score);
        Greenfoot.setWorld(titleScreen);
    }
    
    /**
     * Handles the score whenever an obstacle is broken
     * @param identifier The unique identifier for the obstacle
     * @return None
     */
    public void increaseScore(int identifier) { 
        // Update score
        if (identifier == 0) {
            score += 25 * boost;
        } else if (identifier == 1) {
            score += 75 * boost;
        } else if (identifier == 2) {
            score += 35 * boost;
        } else if (identifier == 3) {
            boost++;
        }
        
        // Set the score label to the current score
        scoreCard.setValue(score);
    }
}
