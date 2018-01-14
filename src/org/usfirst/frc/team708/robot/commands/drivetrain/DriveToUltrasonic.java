package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToUltrasonic extends Command{
    
    private final int 	ERROR_ZONE = 3;
    private double 		targetDistance;
    private double 		moveSpeed;
	private double 		tolerance;
	private double 		minValue;
	private double 		maxValue;
    
    public DriveToUltrasonic(int targetDistance, double minValue, double maxValue, double tolerance) {

    	requires(Robot.drivetrain);
        
        this.targetDistance = targetDistance;
        this.tolerance = tolerance;
        this.minValue = minValue;
        this.maxValue = maxValue;
//        drivetrain.setUltrasonicDistance ((targetDistance - ERROR_ZONE), (targetDistance + ERROR_ZONE), false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	moveSpeed = Robot.drivetrain.moveByUltrasonic(targetDistance, minValue, maxValue, tolerance);
    	Robot.drivetrain.haloDrive(moveSpeed, 0.0, false);
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (moveSpeed == 0.0);
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