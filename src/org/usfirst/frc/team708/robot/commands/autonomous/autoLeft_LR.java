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
 * ScaleLocation 									= RIGHT
 */
public class autoLeft_LR extends CommandGroup {

    public autoLeft_LR() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	addSequential(new SqueezeCubeAuto());
        addSequential(new Send("In autoLeft_LR - SWITCH"));
    	addSequential(new GearShift1());
    	addSequential(new ShiftClimberHigh());
    	
       	// drive to the scale
    	addSequential(new DriveCurvatureToEncoderOrTime(1.0, .03, false, 170, 3));   	
    	addSequential(new TurnToDegrees(.8, 55));
       	addSequential(new DriveStraightToEncoderDistanceOrTime(165, .8, true, 4));	
    	addSequential(new TurnToDegrees(.8, -80));
    	
    	// move arm and tele up and continue to the scale    	
    	addParallel(new ControlArmToScale());
    	addSequential(new ControlTeleToScale());
    	addSequential(new DriveStraightToEncoderDistanceOrTime(40, .6, true, 4));
    	
    	// drop 1st cube in scale 
    	addSequential(new ReleaseCubeAuto());
    	addSequential(new WaitCommand(1.0));
		addSequential(new DriveStraightToEncoderDistanceOrTime(30, .6, false, 1));
		
		// turn towards the cubes and get ready to intake
		addSequential(new ControlTeleToGround());
		addSequential(new ControlArmToGround());
    	addSequential(new TurnToDegrees(1.0, -145)); //maybe change to 140?
    	
    	// vision track the cube and intake
//    	addSequential(new FindCube());
    	addParallel(new AutoIntakeIn(3.0));    	
    	addSequential(new DriveStraightToEncoderDistanceOrTime(30, .8, true, 3));
    	addSequential(new SqueezeCubeAuto());
    	addSequential(new DriveStraightToEncoderDistanceOrTime(12, .7, false, 1));
    	
    	// place cube into the grabber
//    	addSequential(new TurnToDegrees(1.0, 140));
//    	// turn the robot and get ready for scale
////    	addSequential(new DriveCurvatureToDegreesOrTime(-1.0, -.9, false, -170, 2));
//    	
//    	addSequential(new ControlArmToScale());
//    	addSequential(new ControlTeleToScale());
////    	
////    	// drive to the scale
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(36, .8, 1));
//    	addSequential(new ReleaseCubeAuto());

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
