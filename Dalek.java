import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * LiveZen1 - a robot by (your name here)
 */
public class Dalek extends BravoBot
{



  // Checklist POST Test round
  // there's clearly a wall bot and most likely more will follow suit.
  // If there are more wall bots, they'll potentially kill each other.
  // If im not a wall bot, I'll have trouble surviving.
  // We have to figure out how to survive the longest while also not being a wall bot.
  // Move consitently, dont stop moving.
  // Can we change our behaviour at a certain game tick to become a wallbot?
  // Need to find the wall bot code.


   // New Rules. Round Two
   // We cant move the gun, meaning what's logically the best strategy?
   // I want to be able to move towards a target and keep firing
   // We'll test changing the firing method for more accuracy.
   // We'll have to turn the bot to fire as a little bit ahead of the next robot
   // Implement prediction? will be based on speed of projectile and, speed of enemy? But we can't get the enemy speed?.. or location? I think?..
   // We can assume that the enemy players are only going to move forward. based on previous two rounds.
   //
   //


	/**
	 * run: LiveZen1's default behavior
	 */
	private double velocity;

	public void run() {
		// Initialization of the robot should be put here
		this.velocity = 65;
		 setColors(Color.red,Color.red,Color.red); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			movement(this.velocity);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e){
    double distance = e.getDistance(); //get the distance of the scanned robot
    if(distance > 800) //this conditions adjust the fire force according the distance of the scanned robot.
        fire(5);
    else if(distance > 600 && distance <= 800)
        fire(5);
    else if(distance > 400 && distance <= 600)
        fire(5);
    else if(distance > 200 && distance <= 400)
        fire(5);
    else if(distance < 200)
        fire(5);

	  System.out.println("EXTERMINATE!");
	}

	public void movement( double velocity ){

		turnLeft(30);
		ahead(getVelocity());

	}

	public double reverseMovement (double x) {
		System.out.println("reversed");

	return -x;
	}

	public void search() {
		turnGunRight(360);
		turnGunLeft(360);
	}
	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		ahead(100);
		turnLeft(120);
	}

	void OnHitRobot(HitRobotEvent evnt){
    System.out.println("OUCH!");
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		System.out.println("");
		movement(reverseMovement(getVelocity()));
	}

  public double getVelocity(){
    return this.velocity;
  }

  public void setVelocity(double velocity){
    this.velocity = velocity;
  }

}
