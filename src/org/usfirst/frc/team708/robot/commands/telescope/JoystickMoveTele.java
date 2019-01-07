package org.usfirst.frc.team708.robot.commands.telescope;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickMoveTele extends Command {
	
  	private double moveSpeed;

    public JoystickMoveTele() {
        // Use requires() here to declare subsystem dependencies
      requires(Robot.tele);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	moveSpeed = OI.operatorGamepad.getAxis(Gamepad.rightStick_Y);
    	if (moveSpeed > 0) {
    	    Robot.pneumaticsClimber.reverse();
    	}
    	else {
    	    Robot.pneumaticsClimber.forward();
    	}
  }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

    	//check if joystick axis is in deadzone. Change movespeed to 0 if it is
//    	if ((Robot.tele.telescopeDown()) && (moveSpeed <= Constants.TELE_DEADZONE) ||
//    	    (moveSpeed <= Constants.TELE_DEADZONE && moveSpeed >= -Constants.TELE_DEADZONE)) 
//    	{							
//        	Robot.tele.manualMove(0.0);
//        	return true;
//    	}
//    	else {
    		// check if the telescope arm is fully extended
    		if ((Robot.tele.getAngle() >= Constants.TELE_MAX) && (moveSpeed > 0)) {
            	Robot.tele.manualMove(0.0);
            	return true;
    		}
    		else {

            	Robot.tele.manualMove(moveSpeed);
            	return false;
            	}
//    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}