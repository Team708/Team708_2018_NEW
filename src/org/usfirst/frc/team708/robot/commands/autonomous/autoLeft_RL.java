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
 * SwitchLocation 									= RIGHT
 * ScaleLocation 									= LEFT
 */
public class autoLeft_RL extends CommandGroup {

    public autoLeft_RL() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new Send("In autoLeft_RL -- SCALE"));
    	addSequential(new GearShift1());
    	
       	//drive to the scale
//    	addSequential(new DriveCurvatureForTime(1.0, .05, false, 1.8));  //.2 front of switch
    	addSequential(new DriveCurvatureToEncoderOrTime(1.0, .03, false, 250, 1.6));

    	// move arm and tele up as stopping at the white line - continue to the scale    	
    	addSequential(new MoveArmTeleToScaleCG());
//    	addSequential(new DriveCurvatureToWhiteOrTime(.4, .02, false, 1.0));
//    	addSequential(new DriveCurvatureToEncoderOrTime(.4, .05, false, 20, 1.0));
    	
    	addSequential(new DriveStraightToEncoderDistanceOrTime(50, .6, true, 1));
    	
//      drop 1st cube in scale 
		addSequential(new SqueezeCube());
//    	addSequential(new WaitCommand(2.0));

//        addSequential(new Send("In autoLeft_RL -- OTHER SWITCH"));    	
        // move arm&tele down backup and turn towards alley
        //	addparallel(new MoveArmTeleToGroundCG());
//    	addSequential(new DriveCurvatureToDegreesOrTime(-1.0, .5, false, 50, 2));


    	// drive through alley over the bump to opposite field and stop
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(230, .8, true, 4));
    	
    	// drive to the far end of switch to find the 2nd cube
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(45, .8, true, 1));
//    	addSequential(new ActivateButterfly());  //omni on
//    	addSequential(new TurnToDegrees(1.0, 38));

//    	addSequential(new ActivateButterfly());//omni off

//       	addSequential(new FindCube());

    	// run intake and drive to 2nd cube until intake sensor triggers
// 		addParallel(new AutoIntakeIn(1); 
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(40, .6, 1));
    	
    	// drop off 2nd cube in switch
    	//addparallel MoveArmTeleToSwitchCG()
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(20, .6, 1));
    	//deploy addSequential(new AutoIntakeOut(.1));
//    	addSequential(new Send("finished"));
        
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
