package org.usfirst.frc.team708.robot.commands.pneumatics;

import java.awt.Robot;

import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class PneumaticsManual extends Command {


    public PneumaticsManual() {
//    	requires(Robot.feeder);
//    	requires(Robot.intake_ball);
//    	requires(Robot.drivetrain);
//    	requires(Robot.shooter);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.PneumaticsTest.set(DoubleSolenoid.Value.kForward);
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
    	Robot.feeder.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
