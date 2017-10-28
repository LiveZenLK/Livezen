import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * LiveZen1 - a robot by (your name here)
 */
public class Livezenbot extends AlphaBot
{
	/**
	 * run: LiveZen1's default behavior
	 */

   // LINK TO EXCEL SHEET : https://docs.google.com/spreadsheets/d/1FSfENi4A38rAx8164rR_tD4RpWI5eAGiJUji72JDSew/edit?usp=sharing
   
	private double velocity;

	public void run() {
		// Initialization of the robot should be put here
		this.velocity = 80;
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

		turnLeft(3600);
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
		back(50);
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
	@Override
	 public void turnLeft(double degrees) {
        super.turnLeft(degrees / 10.0D);
		System.out.println(degrees/10.0D);
    }

  public double getVelocity(){
    return this.velocity;
  }

  public void setVelocity(double velocity){
    this.velocity = velocity;
  }


  // Checklist POST Test round
  // there's clearly a wall bot and most likely more will follow suit.
  // If there are more wall bots, they'll potentially kill each other.
  // If im not a wall bot, I'll have trouble surviving.
  // We have to figure out how to survive the longest while also not being a wall bot.
  // Move consitently, dont stop moving.
  // Can we change our behaviour at a certain game tick to become a wallbot?
  // Need to find the wall bot code.

  // LINK TO EXCEL SHEET : https://docs.google.com/spreadsheets/d/1FSfENi4A38rAx8164rR_tD4RpWI5eAGiJUji72JDSew/edit?usp=sharing
}
