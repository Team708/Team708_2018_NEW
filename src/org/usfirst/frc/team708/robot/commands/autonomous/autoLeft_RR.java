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
 * ScaleLocation 									= RIGHT
 */
public class autoLeft_RR extends CommandGroup {

    public autoLeft_RR() {
  
    	// put this here in case you want to just do a drive straight over the line
    	//    	addSequential(new DriveStraightToEncoderDistanceOrTime(70, .8, true, 3));

    	
    	addSequential(new SqueezeCubeAuto()); /*intake closed*/
    	
        addSequential(new Send("In autoLeft_R_SwitchOnly - SWITCH"));
    	addSequential(new GearShift1());
    	
       	//drive to the switch
    	
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(20, .6, true, 1));
    	
    	// gyro turn to turn 90 degrees
    	addSequential(new DriveCurvatureToDegreesOrTime(.8, .7, true, 80, 2));


//    	addSequential(new DriveCurvatureToEncoderOrTime(.8, .8, false, 40, 1));
    	addSequential(new DriveStraightToEncoderDistanceOrTime(82, .8, true, 2));

    	addSequential(new DriveCurvatureToDegreesOrTime(.8, -.8, false, -85, 2));
    	addSequential(new DriveStraightToEncoderDistanceOrTime(32, .7, true, 3));
 	
    	addSequential(new AutoIntakeOut(.5));
    	addSequential(new Send("finished"));    	
    	
    	
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	addSequential(new ReleaseCubeAuto()); /*intake open*/
//    	
//        addSequential(new Send("In autoLeft_RR - OTHER SCALE"));
//    	addSequential(new GearShift1());
//    	
//       	//drive to the alley 
//    	addSequential(new DriveCurvatureForTime(1.0, .6, false, 1));
////    	addSequential(new DriveCurvatureToEncoderOrTime(1.0, .6, false, 20, 1));
//
//    	// drive to the far end of switch 
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(45, .8, true, 1));
//    	addSequential(new ActivateButterfly());  //omni on
//    	addSequential(new TurnToDegrees(1.0, 38));
//
//    	addSequential(new ActivateButterfly());//omni off
//
//
////		addparallel(new MoveArmTeleToScaleCG());
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(40, .7, 1));
//
//    	// drop 1st cube in scale
////    	addSequential(new SqueezeCube());
//
//        addSequential(new Send("In autoLeft_RR -- OTHER SWITCH"));
//        // move arm&tele down backup and turn towards 2nd cube
////		addparallel(new MoveArmTeleToGroundCG());
//    	addSequential(new DriveCurvatureForTime(-1.0, .9, false, .5));
////    	addSequential(new DriveCurvatureToEncoderOrTime(-1.0, .9, false, 20, .5));
//    	
//    	addSequential(new TurnToDegrees(1.0, 90));
//
//    	addSequential(new FindCube());
//
//    	// run intake and drive to 2nd cube until intake sensor triggers
//// 		addParallel(new AutoIntakeIn(1);    	
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(80, .6, 1));
//	
//    	// drop off 2nd cube in switch
//    	//addparallel MoveArmTeleToSwitchCG()
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(20, .6, 1));
//    	//deploy addSequential(new AutoIntakeOut(.1));
//    	
//        addSequential(new Send("In autoLeft_RR -- OTHER SWITCH 2"));
//        // move arm&tele down backup and turn towards cube
////		addparallel(new MoveArmTeleToGroundCG());
//    	addSequential(new DriveCurvatureForTime(-1.0, .9, false, .5));
////    	addSequential(new DriveCurvatureToEncoderOrTime(-1.0, .9, false, 20, .5));
//    	addSequential(new TurnToDegrees(1.0, 90));
//
//    	addSequential(new FindCube());
//
//    	// run intake and drive to 3rd cube until intake sensor triggers
//// 		addParallel(new AutoIntakeIn(1);    	
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(80, .6, 1));
//	
//    	// drop off 3rd cube in switch
//    	//addparallel MoveArmTeleToSwitchCG()
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(20, .6, 1));
//    	//deploy addSequential(new AutoIntakeOut(.1));
//

        
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
