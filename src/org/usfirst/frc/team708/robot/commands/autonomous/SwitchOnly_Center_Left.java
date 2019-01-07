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
public class SwitchOnly_Center_Left extends CommandGroup {

    public SwitchOnly_Center_Left() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	addSequential(new ReleaseCubeAuto()); /*intake closed*/  
    	addSequential(new GearShift1());
    	
       	//drive to the switch
    	addSequential(new DriveCurvatureToDegreesOrTime(.8, -.7, false, -40, 2));
    	addSequential(new DriveCurvatureToDegreesOrTime(.8, .5, false, 40, 1));
    	addSequential(new DriveStraightToEncoderDistanceOrTime(10, .6, true, 1)); //JNP shortened from 12 to 6

    	// drop 1st cube in switch
    	addSequential(new AutoIntakeOut(1.0));
    	
    	// pull back and face the center cubes
    	addSequential(new DriveCurvatureToDegreesOrTime(-.8, -.6, false, -40, 1));
    	addSequential(new TurnToDegrees(.8, 65));
    	addSequential(new DriveStraightToEncoderDistanceOrTime(6, .8, false, 1));
    
    	addSequential(new FindCube(2.0));

    	// grab 2nd cube from the center
		addSequential(new ControlArmToGround(2));
    	addParallel(new AutoIntakeIn(2.0));  
    	addSequential(new DriveStraightToCubeOrTime(45, .6, true, 3));
    	addSequential(new AutoIntakeInForTime(1.0));  
    	addSequential(new DriveStraightToEncoderDistanceOrTime(3, .6, false, 1));

    	// Moving robot to the switch
		addSequential(new ControlArmToSwitch(2.0));
    	addSequential(new TurnToDegrees(.9, -90));
    	
    	addSequential(new DriveCurvatureToDegreesOrTime(.8, .7, false, 95, 1)); //JNP shortened from 2 to 1 second
    	
    	// drop 2nd cube in switch
    	addSequential(new AutoIntakeOut(1.0));
    	
    	//go to back side of switch   JNP added this section
    	addSequential(new ActivateButterfly());
    	addSequential(new DriveCurvatureToDegreesOrTime(-1.0, .6, true, 250, 3));
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
