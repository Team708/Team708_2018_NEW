package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightToEncoderDistanceOrTimeOrGear extends Command {

	private double 		targetDistance;
	private final double rotate = 0.0;
	private double 		speed;
	
	private double maxTime;
	
	private boolean goForward;
	
    public DriveStraightToEncoderDistanceOrTimeOrGear(double distance, double maxTime, boolean goForward) {
    	this(distance, Constants.DRIVE_MOTOR_MAX_SPEED, goForward, maxTime);
    }
    
//    public DriveStraightToEncoderDistanceOrTimeOrGear(double distance, double speed, double maxTime) {
//    	this(distance, speed, true, maxTime);
//    }
    
    public DriveStraightToEncoderDistanceOrTimeOrGear(double distance, double speed, boolean goForward, double maxTime) {
    	// Use requires() here to declare subsystem dependencies
//    	requires(Robot.drivetrain);
//    	requires(Robot.intake_gear);
    	
    	if(goForward) {
    		targetDistance = distance;
    		this.speed = speed;
    	} 
    	else {
    		targetDistance = -distance;
    		this.speed = -speed;
    	}
    	this.goForward = goForward;
    	
    	this.setTimeout(maxTime);
    }

    // Called just before this Command runs the first time
    // Enables the PIDController (if it was not already) before attempting to drive straight
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.resetGyro();
//    	Robot.drivetrain.enable();
    	Robot.drivetrain.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.haloDrive(speed, rotate, false);
//    	Robot.drivetrain.haloDrive(Math708.getPercentError
//    			(Robot.drivetrain.getEncoderDistance(), targetDistance), rotate);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(goForward) {
        	return (Robot.drivetrain.getEncoderDistance() >= targetDistance) || isTimedOut() || Robot.intake_gear.hasGear();
        } 
        else {
        	return (Robot.drivetrain.getEncoderDistance() <= targetDistance) || isTimedOut() || Robot.intake_gear.hasGear();
        }
     
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.disable();
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
