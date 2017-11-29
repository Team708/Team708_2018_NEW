package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;

import edu.wpi.first.wpilibj.command.Command;



/**
 *RotateAndDriveToLift
 * this command will utilize the vision data to drive the robot to the center of the lift target
 * and stop when it is at the lift stop at target distance
 */
public class RotateAndDriveToLift extends Command {
	
	private double		moveSpeed;
	private double		rotate;
	/**
	 * Constructor
	 * @param targetDistance - the distance to stop in front of the target
	 */
// VIET ARE WE STILL USING THE TARGET DISTANCE HERE - I THINK WE ARE ACTUALLY CALCULATING IT IN THE SUBSYSTEM
// Mreh mreh mreh, I'm Mrs. P, I want to delete the targetDistance, mreh mreh mreh.
    public RotateAndDriveToLift() {

 //        requires(Robot.drivetrain);
//        requires(Robot.visionLiftGear);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.visionLift.putLiftIsCentered(false);
    	Robot.visionLift.putLiftHasTarget(false);
    	Robot.visionLift.putLiftAtDistance(false);
    	Robot.visionLift.putLiftCounter(0);
    	Robot.visionLift.putLiftCurrentCenter(0);
    	
//    	Robot.visionLift.setLiftCamera();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.visionLift.liftProcessData();
    	rotate 		= Robot.visionLift.liftGetRotate();  
    	moveSpeed 	= Robot.visionLift.liftGetMove();   

 
    	Robot.drivetrain.haloDrive(-1 * moveSpeed, rotate, false);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Check if the sonar distance is less then the target Distance, end
//    	if (Robot.drivetrain.getSonarDistance() < targetDistance  && Robot.visionProcessor.wasCentered()){
//     	if (Robot.visionProcessor.isAtY() && Robot.visionProcessor.wasCentered()){
    	if (Robot.visionLift.liftIsCentered() && Robot.visionLift.liftIsAtDistance() ){
         		     		return true;
    	} 
    	 else if (Robot.visionLift.getLiftCounter() >= AutoConstants.SWEEP3_MAX){
 			Robot.led1.send_to_led(Constants.SET_TARGET_FOUND);
     		return true;
     	}

//    	else if (Robot.drivetrain.getSonarDistance() < targetDistance && Robot.visionProcessor.isHasTarget()) {
//    	else if (Robot.visionLiftGear.liftIsAtDistance() && Robot.visionLiftGear.liftIsHasTarget()) {
//    		return false;
//    	}
    	else
    	    return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.visionLift.putLiftCounter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
