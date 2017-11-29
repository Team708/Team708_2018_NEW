package org.usfirst.frc.team708.robot.commands.visionProcessor;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SonarOverride extends Command {
	
    public SonarOverride() {
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.drivetrain.sonarOverride < 2){
    		Robot.drivetrain.sonarOverride += 1;
    	} else {
    		Robot.drivetrain.sonarOverride = 0;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
