package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.arm.*;
import org.usfirst.frc.team708.robot.commands.telescope.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class MoveArmTeleToScaleCG extends CommandGroup {

   
    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();

    }
	
    public  MoveArmTeleToScaleCG() {


//    	addSequential(new Send("Moving the arm and tele to scale"));
    	addParallel(new ControlArmToScale());
    	addSequential(new ControlTeleToScale());
//    	addSequential(new Send("done moving arm and tele scale"));    
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
