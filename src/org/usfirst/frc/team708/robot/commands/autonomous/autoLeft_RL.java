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
 * SwitchLocation 									= RIGHT
 * ScaleLocation 									= LEFT
 */
public class autoLeft_RL extends CommandGroup {

    public autoLeft_RL() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	addSequential(new SqueezeCubeAuto()); /*intake open*/
    	addSequential(new Send("In autoLeft_RL -- SCALE"));
    	addSequential(new GearShift1());
    	addSequential(new ShiftClimberHigh());
     
    	//drive to the scale
       	addSequential(new DriveCurvatureToEncoderOrTime(1.0, .02, false, 206, 3));

    	// move arm and tele up and continue to the scale    	
    	addParallel(new ControlArmToScale());
    	addSequential(new ControlTeleToScale());
    	addSequential(new DriveStraightToEncoderDistanceOrTime(48, .6, true, 2));
    	
    	// drop 1st cube in scale 
		addSequential(new ReleaseCubeAuto());
		addSequential(new WaitCommand(1.0));
		addSequential(new DriveStraightToEncoderDistanceOrTime(30, .6, false, 1));
		
		// turn towards the cubes and get ready to intake
		addSequential(new ControlTeleToGround());

		addSequential(new TurnToDegrees(1.0, 142)); 
    	addSequential(new FindCube(1.0));

		addSequential(new ControlArmToGround(2));
    	
    	// vision track the cube and intake
    	addParallel(new AutoIntakeIn(4.0));    	
    	addSequential(new DriveStraightToEncoderDistanceOrTime(33, .7, true, 3));
    //	addSequential(new SqueezeCubeAuto());
    	addSequential(new DriveStraightToEncoderDistanceOrTime(12, .7, false, 1));

    	// place cube into the grabber
//    	addSequential(new TurnToDegrees(1.0, -140));
//    	// turn the robot and get ready for scale
////    	addSequential(new DriveCurvatureToDegreesOrTime(-1.0, -.9, false, -170, 2));
//    	
//    	addSequential(new ControlArmToScale());
//    	addSequential(new ControlTeleToScale());
////    	
////    	// drive to the scale
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(60, .8, 1));
//    	addSequential(new ReleaseCubeAuto());
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
