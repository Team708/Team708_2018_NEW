package org.usfirst.frc.team708.robot.commands.pneumatics;

import java.awt.Robot;
import java.time.Instant;

import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team708.robot.commands.pneumatics.*;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsTest;
import org.usfirst.frc.team708.robot.*;

public class PneumaticsManual extends Command {
	
	public PneumaticsManual() {
    	
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	PneumaticsTest.set(DoubleSolenoid.Value.kForward);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(false);
    }

    // Called once after isFinished returns true
    protected void end() {
    	PneumaticsTest.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
