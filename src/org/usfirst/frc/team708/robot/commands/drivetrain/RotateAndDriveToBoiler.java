package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *RotateAndDriveToBoiler
 * this command will utilize the vision data to drive the robot to the center of the boiler
 * and stop when it is at the any of the target stop at distances
 */
public class RotateAndDriveToBoiler extends Command {
	
	private double		moveSpeed;
	private double		rotate;
	
	private int         count;
	
	/**
	 * Constructor
	 * @param targetDistance - the distance to stop in front of the target
	 */

//	public RotateAndDriveToBoiler(double bstopAtDistance){
	public RotateAndDriveToBoiler(double bstopHeight){

//        requires(Robot.drivetrain);
//        requires(Robot.visionBoiler);
        
        // save the distance
//        Robot.visionBoiler.putBoilerStopAtDistance(bstopAtDistance);
        
        // save the height
//        Robot.visionBoiler.putBoilerStopAtHeight(bstopHeight);
        Robot.visionBoiler.putBoilerStopAtHeight(AutoConstants.STOP_AT_BOILER_HEIGHT);


    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.visionBoiler.putBoilerIsCentered(false);
    	Robot.visionBoiler.putBoilerHasTarget(false);
    	Robot.visionBoiler.putBoilerAtDistance(false);
    	Robot.visionBoiler.putBoilerAtHeight(false);
    	Robot.visionBoiler.putBoilerCounter(0);
    	Robot.visionBoiler.putBoilerCurrentCenter(0);
    	Robot.visionBoiler.putBoilerCurrentHeight(0);
    	count = 0 ;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.visionBoiler.boilerProcessData();
    	rotate 	  = Robot.visionBoiler.boilerGetRotate();  
//    	moveSpeed = Robot.visionBoiler.boilerGetMove();


//    	Robot.drivetrain.haloDrive(moveSpeed, rotate, false);
    	Robot.drivetrain.haloDrive(0, rotate, false);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.visionBoiler.boilerIsCentered()){
//     	if (Robot.visionBoiler.boilerIsAtHeight() && Robot.visionBoiler.boilerIsCentered()){
    		SmartDashboard.putBoolean("boiler is done", true);
    		Robot.led1.send_to_led(Constants.SET_TARGET_FOUND);
    		return true;
    	}
//    	else if (Robot.visionBoiler.getBoilerCounter() >= AutoConstants.SWEEP3_MAX){
//    		SmartDashboard.putBoolean("boiler is in sweep", true);
//    		return true;
//    	}
    	else {
//    		SmartDashboard.putNumber("boiler is running", count++);

    		return false;
    	}
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.visionBoiler.putBoilerCounter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
