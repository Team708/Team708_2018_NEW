package org.usfirst.frc.team708.robot.commands.pneumatics;

//import java.awt.Robot;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team708.robot.commands.pneumatics.*;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsCube;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.Robot;

public class SqueezeCube extends Command {

    private double grab;
    private boolean done;
    
    public SqueezeCube() {
    	requires(Robot.pneumaticsCube);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	grab = OI.operatorGamepad.getAxis(Gamepad.shoulderAxisRight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	

    	if (grab==1) {
    		done = false;
    		Robot.pneumaticsCube.IntakeOn();
    	}
    	else
    	{
    		done = true;
	    	Robot.pneumaticsCube.IntakeOff();
//        Robot.pneumaticsCube.toggleIntake();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(done);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
