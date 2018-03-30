package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.intakeCube.*;
import org.usfirst.frc.team708.robot.Robot;
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
public class autoLeft_R_SwitchOnly extends CommandGroup {

    public autoLeft_R_SwitchOnly() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	addSequential(new ReleaseCubeAuto()); /*intake closed*/
    	addSequential(new GearShift1());
    	
       	//drive to the switch
    	
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(20, .6, true, 1));

    	addSequential(new DriveCurvatureToEncoderOrTime(.8, .8, false, 40, 1));
    	addSequential(new DriveStraightToEncoderDistanceOrTime(82, .8, true, 2));

    	addSequential(new DriveCurvatureToDegreesOrTime(.8, -.8, false, -80, 2));
    	addSequential(new DriveStraightToEncoderDistanceOrTime(27, .7, true, 3));
 	
    	addSequential(new AutoIntakeOut(.5));
    	
    	// pull back and face the center cubes
    	addSequential(new DriveCurvatureToDegreesOrTime(-.8, .4, false, 40, 1));
    	addSequential(new DriveCurvatureToDegreesOrTime(-.8, -.4, false, 40, 1));
    	
    	// grab 2nd cube from the center
		addSequential(new ControlArmToGround());
    	addParallel(new AutoIntakeIn(1.0));  
    	addSequential(new DriveStraightToEncoderDistanceOrTime(24, .8, true, 1));

    	// Moving robot to the switch
		addSequential(new ControlArmToSwitch());
    	addSequential(new TurnToDegrees(.8, 90));
    	addSequential(new DriveCurvatureToDegreesOrTime(.6, -.6, false, 90, 1));
    	
    	// drop 2nd cube in switch
    	addSequential(new AutoIntakeOut(.5));
    	addSequential(new Send("finished"));    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       	Robot.pneumaticsClimber.forward();
    	Robot.pneumaticsCube.IntakeOn();
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
