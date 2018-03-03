package org.usfirst.frc.team708.robot.commands.arm;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickMoveArm extends Command {
	
	private double moveSpeed;
	
    public JoystickMoveArm() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 moveSpeed = OI.operatorGamepad.getAxis(Gamepad.leftStick_Y);     	
//    	SmartDashboard.putNumber("Arm speed", moveSpeed);	// Encoder reading
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

 //   	moveSpeed = OI.operatorGamepad.getAxis(Gamepad.leftStick_X); 
//
    	//check if joystick axis is in deadzone. Change movespeed to 0 if it is
    	if((moveSpeed <= Constants.ARM_DEADZONE && moveSpeed >= -Constants.ARM_DEADZONE) || 
    	   (Robot.arm.armDown()) && (moveSpeed <= Constants.ARM_DEADZONE)){
        	Robot.arm.manualMove(0.0);
        	return true;
    	}
    	else if ((Robot.arm.getAngle() >= Constants.ARM_MAX) && (moveSpeed > 0)) {
        	Robot.arm.manualMove(0.0);
        	return true;
		}
		else {
        	Robot.arm.manualMove(moveSpeed);
        	return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}