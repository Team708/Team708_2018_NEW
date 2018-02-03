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
public class ControlArmToScale extends Command {
	
    public ControlArmToScale() {
    	requires(Robot.arm);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
        //   o  o
       	// \    )  /   USE TELESCOPER METHOD (ask Mike) TO RETRACT ARM FIRST
       	//   {:::}
    	
    	Robot.arm.setAngle((int)(Constants.SCALE_HEIGHT - Robot.arm.getAngle()));
    	}    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(); //B/c empty methods cause bugs.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;		
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
