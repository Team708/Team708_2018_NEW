package org.usfirst.frc.team708.robot.commands.visionProcessor;

import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FindCube extends Command {
	
    public FindCube() {
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.visionProcessor);
//        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    // ADD BACK IN LATER	Robot.visionProcessor.processData();
        Robot.drivetrain.haloDrive(0.0, Robot.visionProcessor.getRotate(), false);
    

//    Robot.drivetrain.haloDrive(0.0, .5, false);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.visionProcessor.isCentered();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
