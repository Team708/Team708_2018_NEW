package org.usfirst.frc.team708.robot.commands.pneumatics;

//import java.awt.Robot;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team708.robot.commands.pneumatics.*;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsClimber;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShiftClimberLow extends Command {

    private double shift;
    private boolean done;

    public ShiftClimberLow() {
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	shift = OI.operatorGamepad.getAxis(Gamepad.shoulderAxisRight);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (shift == -1)
    	{
    		done = false;
        	Robot.pneumaticsClimber.reverse();
    	}
    	else
    	{
    		done = true;
        	Robot.pneumaticsClimber.forward();
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
