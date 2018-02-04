package org.usfirst.frc.team708.robot.commands.telescope;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Nick, Mike, Josh
 */
public class ControlTeleToGround extends Command {
	
    public ControlTeleToGround() {
//    	requires(Robot.arm);
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
    	
    	if(Robot.tele.telescopeDown()) {
        	Robot.tele.stop();
        	Robot.tele.resetTeleEncoder();
        	return true;
    	}	
    	else
    	{
        	Robot.tele.moveMotor(Constants.TELE_REVERSE); 
    	    return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
     Robot.tele.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}