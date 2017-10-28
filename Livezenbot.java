package LiveZen;
import robocode.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * LiveZen1 - a robot by (your name here)
 */
public class LiveZen1 extends AlphaBot
{
	/**
	 * run: LiveZen1's default behavior
	 */
	private double velocity;

	public void run() {
		// Initialization of the robot should be put here
		this.velocity = 150;
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			movement(this.velocity);
			search();
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
        fire(4);
    else if(distance > 400 && distance <= 600)
        fire(3);
    else if(distance > 200 && distance <= 400)
        fire(2);
    else if(distance < 200)
        fire(1);

	System.out.println("hello");
	}

	public void movement( double velocity ){

		turnLeft(360);
		ahead(this.velocity);

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
		back(10);
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		System.out.println("");
		movement(reverseMovement(this.velocity));
	}
	@Override
	 public void turnLeft(double degrees) {
        super.turnLeft(degrees / 10.0D);
		System.out.println(degrees/10.0D);
    }
}
