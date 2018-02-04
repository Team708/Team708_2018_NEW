package org.usfirst.frc.team708.robot.commands.telescope;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
//import org.team708.robot.subsystems.arm.*;
//import org.usfirst.frc.team708.robot.subsystems.Loader;
import org.usfirst.frc.team708.robot.util.Gamepad;

//import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ControlTeleDown extends Command {

    public ControlTeleDown() {
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
