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
    	addSequential(new DriveStraightToEncoderDistanceOrTime(94, .8, true, 2));

    	addSequential(new DriveCurvatureToDegreesOrTime(.8, -.8, false, -85, 2)); 
    	addSequential(new DriveStraightToEncoderDistanceOrTime(48, .7, true, 1)); // increase the distance moving forward... 30 inches?
 	
    	addSequential(new AutoIntakeOut(1.0));
    	
    	// pull back and face the center cubes
    	addSequential(new DriveCurvatureToDegreesOrTime(-.8, .6, false, 40, 1));
    	addSequential(new TurnToDegrees(.8, -50)); // original 45
    	addSequential(new DriveStraightToEncoderDistanceOrTime(6, .8, false, 1));
    	
    	addSequential(new FindCube(2.0));
    	
    	// grab 2nd cube from the center
		addSequential(new ControlArmToGround());
    	addParallel(new AutoIntakeIn(2.0));  
    	addSequential(new DriveStraightToCubeOrTime(30, .6, true, 2));
    	addSequential(new AutoIntakeInForTime(1.0));  
    	addSequential(new DriveStraightToEncoderDistanceOrTime(3, .6, false, 1));
    	
    	// Moving robot to the switch
		addSequential(new ControlArmToSwitch(2.0));
    	addSequential(new TurnToDegrees(.9, 90));
    	addSequential(new DriveCurvatureToDegreesOrTime(.9, -.8, false, -79, 2));
    	
    	// drop 2nd cube in switch
    	addSequential(new AutoIntakeOut(1.0));
    	
    	//go to back side of switch   JNP added this section
    	addSequential(new ActivateButterfly());
    	addSequential(new DriveCurvatureToDegreesOrTime(-1.0, -.3, true, -180, 3));
    	addSequential(new WaitCommand(1.0));
    	addSequential(new ActivateButterfly());    	
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
