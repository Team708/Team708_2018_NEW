package org.usfirst.frc.team708.robot.commands.arm;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
//import org.team708.robot.subsystems.*;
//import org.usfirst.frc.team708.robot.subsystems.Loader;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Nick, Mike, Josh
 */
public class ControlArmToGround extends Command {
	
	private double runTime;
	
    public ControlArmToGround(double runTime) {
    	requires(Robot.arm);
    	this.runTime = runTime;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	}    	


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//do we neeed a timer here to keep the arm motor from spinning too long
    	//maybe check voltage draw and encoder change?
    	
    	if(Robot.arm.armDown() || this.timeSinceInitialized() >= runTime) {
        	Robot.arm.stop();
        	Robot.arm.resetArmEncoder();
        	return true;
    	}	
    	else
    	{
        	Robot.arm.moveMotor(Constants.ARM_REVERSE); 
    	    return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
     Robot.arm.stop();

    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}