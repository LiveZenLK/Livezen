import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * LiveZen1 - a robot by (your name here)
 */
public class Dalek extends CharlieBot
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


   // There's clearly an invsion of wall huggers and we need to beat them.
   // This game might as well as be called Spiderman,
   // Need to look over the code for CharlieBot
   // Figure out what we need to do to win.
   // Build a tracker


   int count = 0; // Keeps track of how long we've
 	// been searching for our target
 	double gunTurnAmt; // How much to turn our gun when searching
 	String trackName; // Name of the robot we're currently tracking

 	/**
 	 * run:  Tracker's main run function
 	 */
 	public void run() {
 		// Set colors
 		setColors(Color.red,Color.red,Color.red); // body,gun,radar

 		// Prepare gun
 		trackName = null; // Initialize to not tracking anyone
 		setAdjustGunForRobotTurn(false); // Keep the gun still when we turn
 		gunTurnAmt = 10; // Initialize gunTurn to 10

 		// Loop forever
 		while (true) {
 			// turn the Gun (looks for enemy)
 			turnRight(gunTurnAmt);
 			// Keep track of how long we've been looking
 			count++;
 			// If we've haven't seen our target for 2 turns, look left
 		// 	if (count > 2) {
 		// 		gunTurnAmt = -10;
 		// 	}
 			// If we still haven't seen our target for 5 turns, look right
 			if (count > 1) {
 				gunTurnAmt = 10;
 			}
 			// If we *still* haven't seen our target after 10 turns, find another target
 			if (count > 11) {
 				trackName = null;
 			}
 		}
 	}


 	/**
 	 * onScannedRobot:  Here's the good stuff
 	 */
 	public void onRobotDetected(ScannedRobotEvent e) {

 		// If we have a target, and this isn't it, return immediately
 		// so we can get more ScannedRobotEvents.
 		if (trackName != null && !e.getName().equals(trackName)) {
 			return;
 		}

 		// If we don't have a target, well, now we do!
 		if (trackName == null) {
 			trackName = e.getName();
 			out.println("Tracking " + trackName);
 		}
 		// This is our target.  Reset count (see the run method)
 		count = 0;
 		// If our target is too far away, turn and move toward it.
 		if (e.getDistance() > 800) {
 			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

 			turnRight(gunTurnAmt+75);
      fire(1);// Try changing these to setturnRight,
 			turnRight(e.getBearing()); // and see how much Tracker improves...
 			// (you'll have to make Tracker an AdvancedRobot)
 			ahead(e.getDistance() - 140);
 			return;
 		}

    if (e.getDistance() > 600 && e.getDistance() <= 800) {
 			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

 			turnRight(gunTurnAmt+35);
      fire(1.5);// Try changing these to setturnRight,
 			turnRight(e.getBearing()); // and see how much Tracker improves...
 			// (you'll have to make Tracker an AdvancedRobot)
 			ahead(e.getDistance() - 140);
 			return;
 		}

    if (e.getDistance() < 600 ) {
 			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

 			turnRight(gunTurnAmt+20);
      fire(1.5);// Try changing these to setturnRight,
 			turnRight(e.getBearing()); // and see how much Tracker improves...
 			// (you'll have to make Tracker an AdvancedRobot)
 			ahead(e.getDistance() - 140);
 			return;
 		}

    if (e.getDistance() < 400 ) {
 			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

 			turnRight(gunTurnAmt);
      fire(1.5);// Try changing these to setturnRight,
 			turnRight(e.getBearing()); // and see how much Tracker improves...
 			// (you'll have to make Tracker an AdvancedRobot)
 			ahead(e.getDistance() - 140);
 			return;
 		}

 		// Our target is close.
 		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
 		turnRight(gunTurnAmt);
 		fire(3);

 		// Our target is too close!  Back up.
 		if (e.getDistance() < 100) {
 			if (e.getBearing() > -90 && e.getBearing() <= 90) {
 				back(40);
 			} else {
 				ahead(40);
 			}
 		}
 		scan();
 	}

 	/**
 	 * onHitRobot:  Set him as our new target
 	 */
 	public void onHitRobot(HitRobotEvent e) {
 		// Only print if he's not already our target.
 		if (trackName != null && !trackName.equals(e.getName())) {
 			out.println("Tracking " + e.getName() + " due to collision");
 		}
 		// Set the target
 		trackName = e.getName();
 		// Back up a bit.
 		// Note:  We won't get scan events while we're doing this!
 		// An AdvancedRobot might use setBack(); execute();
 		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
 		turnRight(gunTurnAmt);
 		fire(3);
 		back(50);
 	}

  public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(60);
	}

 	/**
 	 * onWin:  Do a victory dance
 	 */
 	public void onWin(WinEvent e) {
 		for (int i = 0; i < 50; i++) {
 			turnRight(30);
 			turnLeft(30);
 		}
 	}
 }
