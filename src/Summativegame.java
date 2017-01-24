import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author manok5757
 */
public class Summativegame extends JComponent implements KeyListener {

    MP3Player music = new MP3Player(new File("music.mp3"));
    
    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // create a variable to move the cannon up 
    boolean moveUp = false;
    //create a variable to move the cannon down
    boolean moveDown = false;
    // create speed when moving up
    int upSpeed = -15;
    // create speed when moving down
    int downSpeed = 15;
    // create boolean to see if game starts
    boolean begin = false;
    // import picture
    BufferedImage cannonimg = loadImage("cannon.png");
    // import a picture for the cannon ball
    BufferedImage cannonBall = loadImage("Cannonball.png");
    // import a picture for the wall'
    BufferedImage targetBackground = loadImage("Brickwall.png");
    // import a picture for the moving target
    BufferedImage target = loadImage("Target.png");
    // import a picture for the floor
    BufferedImage marbleFloor = loadImage("Marblefloor.png");
    // import an image of the blue background
    BufferedImage wallpaper = loadImage("wallpaperBackground.png");
    // import a game over image
    BufferedImage gameOver = loadImage("gameOver.png");
    // import an image that says press enter to restart
    BufferedImage enterRestart = loadImage("EnterAgain.png");
    // import the picture for the start screen
    BufferedImage startScreen = loadImage("cannontitle.png");
    // import a picture for the start button
    BufferedImage startButton = loadImage("startbutton.png");
    // import a picture for the up and down arrow keys
    BufferedImage upDown = loadImage("upDownGood.png");
    // import a picture for the spacebar
    BufferedImage spacebar = loadImage("space.png");
    // start off with the game not on
    boolean start = false;
    // create a shape for the rectangle to go in
    Rectangle cannon = new Rectangle(6, 525, 100, 76);
    // set a variable to say the distance from y
    int distanceY = 0;
    // movment speed
    int moveSpeed = 8;
    // create a boolean for the shooting of the cannon
    boolean shoot = false;
    // add a colour for the background
    Color gold = new Color(242, 242, 12);
    // make a rectangle for the cannon ball to be in
    Rectangle ball = new Rectangle(107, cannon.y - 3, 30, 30);
    // set ball speed
    int ballSpeed = 8;
    // create a rectangle for the moving target to be in
    Rectangle Target = new Rectangle(740, 200, 80, 90);
    // create a rectangle for the game over to be in
    Rectangle gameDone = new Rectangle(170, -10, 500, 260);
    // create boolean to see if target hits bottom 
    boolean hitBottom = false;
    // create a boolean to see if target hits top
    boolean hitTop = false;
    // create a boolean for when the target begins to move
    boolean targetStartMove = true;
    // create a boolean to see if things speed up
    boolean speedIncrease = false;
    // create a base move speed for the target
    int targetSpeed = 2;
    // set up 3 lives for the player
    //boolean life1 = false;
    //boolean life2 = false;
    //boolean life3 = false;
    // set a variable for the the font
    Font timer = new Font("Calibri", Font.BOLD, 42);
    boolean timerStart = false;
    int time = 660;
    // create a boolean to pause between levels
    boolean levelPause = false;
    // create a boolean which resets the game in its original state
    boolean gameFresh = true;
    // set a variable for the score font
    Font scoreRunning = new Font("Lucida Bright Regular", Font.BOLD, 34);
    // keep track of the score
    int score = 0;
    // set a font for the final score display
    Font scoreFinal = new Font("Rockwell Extra Bold", Font.BOLD, 31);
    // create an int to track level number
    int level = 1;
    // create a boolean for the start screen
    boolean beginScreen = true;
    // create a font foe the instructions
    Font instructions = new Font("Calibiri", Font.BOLD, 35);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if (beginScreen) {
        // create a starting screen
            g.drawImage(startScreen, 0, 0, 800, 600, null);
            // draw in the start button
            g.drawImage(startButton, 47, 500, 400, 80, null);
            // draw in the title of the game (CANNON RUSH)
            
            // dont let the players move
            moveDown = false;
            moveUp = false;
            start = false;
        }
        
        if (!beginScreen) {

        // set a colour for the background
            if (gameFresh) {
                g.setColor(gold);
                // draw the background of the game and leave a gap for the floor
                g.drawImage(wallpaper, 0, 0, 780, 562, null);
                // set a colour for the floor
                g.setColor(Color.BLUE);
                // draw the floor
                g.drawImage(marbleFloor, 0, 563, WIDTH, 38, null);
                // set the colour of the cannon'
                g.setColor(Color.red);
        // create a cannon barrel

                // set the colour of the wheel of the cannon
                g.setColor(Color.GREEN);

                // draw in the cannon
                g.drawImage(cannonimg, cannon.x, cannon.y, cannon.width, cannon.height, null);

                // draw in the target background
                g.drawImage(targetBackground, 740, 0, 80, 563, null);

        
                // if the game is started
                // draw in the onscreen controls after the start screen is passed
        if(!beginScreen &&!start){
            // draw in the space bar
            g.drawImage(spacebar, 44, 140, 400, 70, null);
            // draw in the up down
            g.drawImage(upDown, 32, 55, 100, 70, null);
            // set the colour to green
            g.setColor(Color.GREEN);
            // set the font for the written instructions
            g.setFont(instructions);
            // draw in the instructions beside the up and down
            g.drawString(" = Start Game & Move Up and Down", 140, 100);
            // draw in the instructions beside the spacebar
            g.drawString(" = Shoot", 454, 192);
        }
                if (start) {

                    // set the font for the countdown timer
                    g.setFont(timer);

                    // draw in the timer
                    g.drawString("Time remaining: " + time / 60, WIDTH / 4 + 10, 50);

                    // set the colour to black
                    g.setColor(Color.black);
                    // set the font for the score
                    g.setFont(scoreRunning);

                    // draw in the score
                    g.drawString("Score: " + score, 200, 592);
                    // draw in the current level
                    g.drawString("Level: " + level, 390, 592);
                    // have a running timer at the top
                    g.setFont(null);
                    // draw the target in
                    g.drawImage(target, Target.x, Target.y, Target.width, Target.height, null);
                    // draw the cannon ball if the spacebar is pressed
                    if (shoot) {

                        g.drawImage(cannonBall, ball.x, cannon.y - 5, ball.width, ball.height, null);
                    }
                    // make sure the ball doesn't go past the wall
                    if (ball.x >= 750) {
                        ball.x = 750;
                    }
                    // if the ball reaches the end of the xcreen make it dissapear
                    if (ball.x >= 710) {
                        ball.x = 100;
                        // make shoot be false so the user can reshoot
                        shoot = false;
                    }

            //if (life1) {
                    // g.drawImage(lifeLost, 500, 560, 18, 18, null);
                    //}
                    //if (life2) {
                    //   g.drawImage(lifeLost, 530, 560, 18, 18, null);
                    ///}
                    //if (life3) {
                    //   g.drawImage(lifeLost, 560, 560, 18, 18, null);
                    //}
            // if the player hits the target
                    //if(speedIncrease&&ball.x<500){
                    //  g.drawImage(speedUp, 100, 200, 100, 100, null);
                    //}
                }
            
            } else {
                // set a black bacground ASK MR LAMONT
                g.setColor(Color.BLUE);
                // draw in the background
                g.drawRect(0, 0, 800, 600);
                // draw in the image that says press enter to restart
                g.drawImage(enterRestart, 178, 450, 500, 280, null);
                // draw in the game over
                g.drawImage(gameOver, gameDone.x, gameDone.y, gameDone.width, gameDone.height, null);
                // draw in the users final score
                g.setFont(scoreFinal);
                g.drawString("You Reached Level " + level + " With a Score of " + score, 8, 400);

            }

        }

// GAME DRAWING ENDS HERE
    }

    public BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            File file = new File(filename);
            img = ImageIO.read(file);
        } catch (Exception e) {
            // if there is an error, print it
            e.printStackTrace();
        }
        return img;
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        music.play();
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            // only let game commence when the start screen is passed
            if (!beginScreen) {

                // only let the game be played if the player has not lost
                if (gameFresh) {

                    // begin the game only if the arrows are moved
                    if (start) {
                        // begin the timer
                        timerStart = true;
                        // if the player is pressing the up arrow
                        if (moveUp) {
                            // make them move up
                            cannon.y = cannon.y - moveSpeed;

                        }
                        // if they are pressing the down arrow 
                        if (moveDown) {
                            // make them move down
                            cannon.y = cannon.y + moveSpeed;
                        }

                        // start the countdown timer!
                        time = time - 1;
                        // if the countdown timer is equal to zero, begin the process of resetting the game
                        if (time == 0) {
                            // all drawings dissapear
                            gameFresh = false;
                        }

                        // do not allow the players to move while their cannon is firing
                        if (shoot) {
                            moveUp = false;
                            moveDown = false;
                        }
                // set barriers
                        // if the cannon passes these point in the y axis revert them to their original place
                        if (cannon.y > 532) {
                            cannon.y = 530;
                        }
                        if (cannon.y < 0) {
                            cannon.y = 4;
                        }

                        // if space is pressed
                        if (shoot) {
                            // make cannon ball move
                            ball.y = cannon.y;
                            ball.x = ball.x + ballSpeed;
                        }

                        // if the ball reaches the end of the screen allow the user to reshoot
                        if (ball.x >= 750) {

                            shoot = false;
                        }

                        // make the target on the wall move up and down
                        if (targetStartMove) {
                            Target.y = Target.y + targetSpeed;
                        }
                        // if the target hits the floor 
                        if (Target.y > 473) {
                            // set the boolean that makes the intial target movement to false so it stops moving down
                            targetStartMove = false;
                            // state it is true that the target hit the bottom
                            hitBottom = true;
                            // state that it is false the target hit the top of the screen
                            hitTop = false;
                        }
                        // if it hits the bottom
                        if (hitBottom) {
                            // make it move up
                            Target.y = Target.y - targetSpeed;
                        }
                        // if the target hits the top
                        if (Target.y < 0) {
                            // state that it is true that it hit the top
                            hitTop = true;
                            // state that it iss false that it hit the bottom
                            hitBottom = false;
                        }
                        // when it hits the top
                        if (hitTop) {
                            // make it move down
                            Target.y = Target.y + targetSpeed;
                        }

                        // get hit detection between the cannonball and the moving target working
                        if (ball.x + ball.width >= Target.x && ball.y >= Target.y && ball.y + ball.height <= Target.y + Target.height + 30) {
                            // increase the target spped by 1 everytime the target is hit
                            targetSpeed = targetSpeed + 1;

                            speedIncrease = true;
                            // stop the timer
                            time = 660;
                            // allow all the drawings to be redrawn
                            gameFresh = true;
                            // add to the score
                            score = score + 5;
                            // move the level up by 1
                            level = level + 1;
                        }
                // if they stay in one spot for 7 seconds end the game
                        
                // see if a life is lost
                        //if (ball.x + ball.width >= Target.x && (ball.y + ball.height) - Target.y >= 250 || ball.x + ball.width >= Target.x && ((ball.y + ball.height) - Target.y) * -1 >= 200) {
                // if the ball does not hit the target by a certain amount
                    }
                }

            }

            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        Summativegame game = new Summativegame();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);
        frame.addKeyListener(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // if down is pressed
        if (key == KeyEvent.VK_DOWN) {
            // start the game
            start = true;
            // allow the user to move down
            moveDown = true;
            // if the user is shooting
            if (shoot) {
                // do not allow them to move down
                moveDown = false;
            }
        }
        // if up is pressed
        if (key == KeyEvent.VK_UP) {
            moveUp = true;
            start = true;
            // if the user is in the process of shooting
            if (shoot) {
                // do not allow them to move up
                moveUp = false;

            }
        }
        // if spacebar is pressed
        if (key == KeyEvent.VK_SPACE) {
            shoot = true;

        }
        // if the enter key is pressed
        if (key == KeyEvent.VK_ENTER) {

            // reset the game
            gameFresh = true;
            score = 0;
            time = 660;
            start = false;
            // revert the target speed to original
            targetSpeed = 2;
            // put the rectangle back in its orignal place
            cannon.y = 525;
            level = 1;
        }
        // if the a key is pressed
        if (key == KeyEvent.VK_A) {
            beginScreen = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_DOWN) {
            moveDown = false;
        }
        if (key == KeyEvent.VK_UP) {
            moveUp = false;
        }

    }
}
