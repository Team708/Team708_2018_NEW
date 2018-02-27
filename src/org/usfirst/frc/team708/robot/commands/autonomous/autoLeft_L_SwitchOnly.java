package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.intakeCube.*;
import org.usfirst.frc.team708.robot.commands.arm.*;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.telescope.*;
import org.usfirst.frc.team708.robot.commands.pneumatics.*;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
/**
 * this runs the automode for:
 * Start On Side of Field from driver station view 	= LEFT
 * SwitchLocation 									= LEFT

 */
public class autoLeft_L_SwitchOnly extends CommandGroup {

    public autoLeft_L_SwitchOnly() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new Send("In autoLeft_L_SwitchOnly - SWITCH"));
    	addSequential(new GearShift1());
    	
       	//drive to the switch
    	addSequential(new DriveCurvatureForTime(.50, .6, false, 1));
//    	addSequential(new DriveCurvatureToEncoderOrTime(.5, .6, false, 30, 1));

    	
    	// drop 1st cube in switch
    	addSequential(new AutoIntakeOut(.5));


    	addSequential(new Send("finished"));    	
    	
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
