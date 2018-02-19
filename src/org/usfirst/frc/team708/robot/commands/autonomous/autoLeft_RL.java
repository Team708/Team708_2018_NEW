package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.ActivateButterfly;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
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
 * SwitchLocation 									= RIGHT
 * ScaleLocation 									= LEFT
 */
public class autoLeft_RL extends CommandGroup {

    public autoLeft_RL() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new Send("In autoLeft_RL"));
    	addSequential(new Send("In Left Drive R L"));
    	addSequential(new GearShift1());
    	
       	//this goes to scale
    	addSequential(new DriveCurvatureForTime(1.0, .05, false, 1.8));  //.2 front of switch

//		addparallel(new MoveArmTeleToScale());
    	addSequential(new DriveCurvatureToWhiteOrTime(.4, .05, false, 1.0));
    		
    	addSequential(new DriveStraightToEncoderDistanceOrTime(24, .6, true, 1));
    	
//      deploy cube addSequential(new SqueezeCube());
    	addSequential(new WaitCommand(2.0));

//		addparallel(new MoveArmTeleToGround());
    	addSequential(new DriveCurvatureToDegreesOrTime(-1.0, .5, false, 50, 2));


    	addSequential(new DriveStraightToEncoderDistanceOrTime(230, .8, true, 4));
    	
    	
    	addSequential(new DriveStraightToEncoderDistanceOrTime(45, .8, true, 1));
    	addSequential(new ActivateButterfly());
    	addSequential(new TurnToDegrees(1.0, 38));

    	addSequential(new ActivateButterfly());

       	addSequential(new FindCube());

    	addSequential(new DriveStraightToEncoderDistanceOrTime(40, .6, 1));
    	
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
