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
//    	requires(Robot.arm);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
        //   o  o
       	// \    )  /   USE TELESCOPER METHOD (ask Mike) TO RETRACT ARM FIRST
       	//   {:::}
    	
    	if(Robot.arm.getAngle() > Constants.FEEDER_STATION_HEIGHT)
    		Robot.arm.moveMotor(Constants.ARM_REVERSE);
    	if(Robot.arm.getAngle() < Constants.FEEDER_STATION_HEIGHT)
    		Robot.arm.moveMotor(Constants.ARM_FORWARD);
    	}    	
  

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Have a range in case the RIO can't get the angle exact.
    	if (Robot.arm.getAngle() > Constants.FEEDER_STATION_HEIGHT - 5 && Robot.arm.getAngle() < Constants.FEEDER_STATION_HEIGHT + 5)
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