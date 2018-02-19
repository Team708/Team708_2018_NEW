package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToWhiteOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearShift1;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.visionProcessor.FindCube;

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
        addSequential(new Send("In autoLeft_LL"));
    	addSequential(new Send("In Left Drive L L"));
    	addSequential(new GearShift1());
    	
       	//this goes to scale
    	addSequential(new DriveCurvatureForTime(1.0, .05, false, 1.8));  //.2 front of switch

//		addparallel(new MoveArmTeleToScale());
    	addSequential(new DriveCurvatureToWhiteOrTime(.4, .05, false, 1.0));
    		
    	addSequential(new DriveStraightToEncoderDistanceOrTime(24, .6, true, 1));
    	
//      deploy cube addSequential(new SqueezeCube());

//		addparallel(new MoveArmTeleToGround());
    	addSequential(new DriveCurvatureForTime(-1.0, .9, false, .5));

    	addSequential(new TurnToDegrees(1.0, 90));

    	
    	addSequential(new FindCube());

    	addSequential(new DriveStraightToEncoderDistanceOrTime(80, .6, 1));
    	

    	//parallel
    	//raise to switch
    	//forward
    	addSequential(new DriveStraightToEncoderDistanceOrTime(20, .6, 1));

    	//deploy addSequential(new AutoIntakeOut());
    	
//		addparallel(new MoveArmTeleToGround());
    	addSequential(new DriveCurvatureForTime(-1.0, -.9, false, .4));
    	
    	
    	addSequential(new FindCube());


    	addSequential(new DriveStraightToEncoderDistanceOrTime(50, .6, 1));


    	addSequential(new TurnToDegrees(1.0, -100));
    	addSequential(new DriveStraightToEncoderDistanceOrTime(50, .7, 1));

//		addparallel(new MoveArmTeleToScale());
    	addSequential(new DriveStraightToEncoderDistanceOrTime(40, .7, 1));

    	//release cube
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
