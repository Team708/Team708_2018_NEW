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
//    	Robot.pneumaticsCube.IntakeOn(); /*intake open grabber closed*/
    	
        addSequential(new Send("In autoLeft_LR - SWITCH"));
    	addSequential(new GearShift1());
    	
       	//drive to the switch
    	addSequential(new DriveCurvatureForTime(1.0, .6, false, 1));
//    	addSequential(new DriveCurvatureToEncoderOrTime(.5, .6, false, 30, 1));

    	
    	// drop 1st cube in switch
//    	addSequential(new SqueezeCube());
//    	addSequential(new WaitCommand(2.0));  
    	
        addSequential(new Send("In autoLeft_LR -- other SCALE"));
        // move arm&tele down backup towards the scale
//		addparallel(new MoveArmTeleToGroundCG());
    	addSequential(new DriveCurvatureForTime(-1.0, .9, false, .5));
//    	addSequential(new DriveCurvatureToEncoderOrTime(-1.0, .9, false, 40, .5));
    	
    	
       	addSequential(new FindCube());
       	    	
    	// run intake and drive to 2nd cube until intake sensor triggers
// 		addParallel(new AutoIntakeIn(1); 
    	addSequential(new DriveStraightToEncoderDistanceOrTime(40, .6, 3));
    	
    	// transfer cube from intake to grabber
    	//addSequential(new SqueezeCube());
 
    	// backup so that robot can drive through alley over the bump to opposite field and stop
    	addSequential(new DriveCurvatureForTime(-1.0, .9, false, .5));
//    	addSequential(new DriveCurvatureToEncoderOrTime(-1.0, .9, false, 40, .5));
    	
    	// drive to the far end of switch to find the 2nd cube
    	addSequential(new DriveStraightToEncoderDistanceOrTime(45, .8, true, 1));
    	addSequential(new ActivateButterfly());  //omni on
    	addSequential(new TurnToDegrees(1.0, 38));

    	addSequential(new ActivateButterfly());//omni off


//		addparallel(new MoveArmTeleToScaleCG());
    	addSequential(new DriveStraightToEncoderDistanceOrTime(40, .7, 1));

    	// drop 2nd cube in scale
//    	addSequential(new SqueezeCube());
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
