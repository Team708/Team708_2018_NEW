package org.usfirst.frc.team708.robot.commands.telescope;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@author Nick, Mike, Josh
 */
public class ControlTeleToScale extends Command {
	
    public ControlTeleToScale() {
//    	requires(Robot.arm);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	}    	
  

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.tele.getAngle() >= Constants.TELE_SCALE_HEIGHT - Constants.TELE_TOLERANCE)
    		Robot.tele.moveMotor(Constants.TELE_FORWARD);
    	else
    		Robot.tele.moveMotor(Constants.TELE_REVERSE);
    	}    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Have a range in case the RIO can't get the angle exact.
    	if (Robot.tele.getAngle() >= Constants.TELE_SCALE_HEIGHT - Constants.TELE_TOLERANCE && 
    			      Robot.tele.getAngle() <= Constants.TELE_SCALE_HEIGHT + Constants.TELE_TOLERANCE)
    		return true;	
    	else
    		return false;
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