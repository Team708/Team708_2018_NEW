package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Send extends Command {
	
	String output;
	
    public Send(String output) {
    	this.output = output;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Event", output);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
