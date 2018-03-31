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
 * ScaleLocation 									= LEFT
 */
public class autoLeft_LL extends CommandGroup {

    public autoLeft_LL() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	
    	addSequential(new SqueezeCubeAuto());
    	addSequential(new GearShift1());
    	addSequential(new ShiftClimberHigh());

    	
       	// drive to the scale
    	addSequential(new DriveCurvatureToEncoderOrTime(1.0, .02, false, 200, 2));

    	// move arm and tele up and continue to the scale    	
    	addParallel(new ControlArmToScale());
    	addSequential(new ControlTeleToScale());
    	addSequential(new DriveStraightToEncoderDistanceOrTime(36, .6, true, 2));
    	
    	// drop 1st cube in scale 
		addSequential(new ReleaseCubeAuto());
		addSequential(new WaitCommand(1.0));
		addSequential(new DriveStraightToEncoderDistanceOrTime(30, .6, false, 1));
		
		// turn towards the cubes and get ready to intake
		addSequential(new ControlTeleToGround());
		
    	addSequential(new TurnToDegrees(1.0, 142)); // originally 145
    	addSequential(new FindCube(1.0));

		addSequential(new ControlArmToGround());
    	
    	// vision track the cube and intake
    	addParallel(new AutoIntakeIn(2.0));    	
    	addSequential(new DriveStraightToEncoderDistanceOrTime(30, .8, true, 2));
    	
//    	// drop 2nd cube into the switch
    	addSequential(new ReleaseCubeAuto());
    	addSequential(new ControlArmToSwitch());
    	addSequential(new DriveStraightToEncoderDistanceOrTime(12, .8, true, 1));
    	addSequential(new AutoIntakeOut(.5));
   
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
