package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.command.Command;

//SMP - commented out vision from this
/**
 *
 */
public class RotateAndDriveToTarget extends Command {
	
	private double 		targetDistance;
	private double		moveSpeed;
	private double		rotate;
	
	/**
	 * Constructor
	 * @param rotationSpeed
	 * @param goalDegrees
	 */
    public RotateAndDriveToTarget(double targetDistance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
//SMP NOT IN YET        requires(Robot.visionProcessor);
        
        this.targetDistance = targetDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.visionProcessor.processData();
//    	rotate = -Robot.visionProcessor.getRotate();  // was + made -
// //   	moveSpeed = Robot.visionProcessor.getMove();
//    	//If target is in view or if it was centered, move forward
//    	if (Robot.visionProcessor.isHasTarget()){
//    		moveSpeed = -0.5; // was + made -
//    	} //If target was/is centered, moveforward and stop rotation 
//    	else if (Robot.visionProcessor.wasCentered()) {
//    		moveSpeed = -0.3; //was + made -
//    		rotate = 0.0;
//    	} //If target is not in view or was not centered, don't move forward.
//    	else {
//    		moveSpeed = 0.0;
//    	}
    	
//    	Robot.drivetrain.haloDrive(moveSpeed, rotate, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

    	//Check if the sonar distance is less then the target Distance, end
//   // 	if (Robot.drivetrain.getSonarDistance() < targetDistance  && Robot.visionProcessor.wasCentered()){
//    	if (Robot.visionProcessor.isAtY() && Robot.visionProcessor.wasCentered()){
//    		return true;
//    	}
////    	else if (Robot.drivetrain.getSonarDistance() < targetDistance && Robot.visionProcessor.isHasTarget()) {
//    	else if (Robot.visionProcessor.isAtY() && Robot.visionProcessor.isHasTarget()) {
//    		return true;
//    	}
//    	
    	return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
