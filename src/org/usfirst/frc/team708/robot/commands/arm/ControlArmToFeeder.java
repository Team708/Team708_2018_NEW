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
public class ControlArmToFeeder extends Command {
	
    public ControlArmToFeeder() {
    	requires(Robot.arm);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	}    	
  

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       	if(Robot.arm.getAngle() >= Constants.FEEDER_STATION_HEIGHT - Constants.ARM_TOLERANCE)
    		Robot.arm.moveMotor(Constants.ARM_REVERSE);
       	else
    		Robot.arm.moveMotor(Constants.ARM_FORWARD);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Have a range in case the RIO can't get the angle exact.
    	if (Robot.arm.getAngle() >= Constants.FEEDER_STATION_HEIGHT - Constants.ARM_TOLERANCE && 
			      Robot.arm.getAngle() <= Constants.FEEDER_STATION_HEIGHT + Constants.ARM_TOLERANCE)
		return true;	
	else
		return false;
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