package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToWhiteLineorTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearShift1;
//import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.visionProcessor.FindCube;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
//import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
//import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
///*import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
//import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Up;
//import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
//import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_In;
//import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
//import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
//import org.usfirst.frc.team708.robot.commands.autonomous.AutoFireBalls;*/

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LeftDriveLL extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();
    	
    }
	
    public  LeftDriveLL() {

    	addSequential(new Send("In Left Drive L L"));
    	addSequential(new GearShift1());
    	
       	//this goes to scale
    	addSequential(new DriveCurvatureForTime(1.0, .05, false, 2.0));  //.2 front of switch
    	addParallel(new WaitCommand(1.0));
    	//raise to scale
    	
    	
    	//deploy cube
    	addSequential(new WaitCommand(1.0));
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(400, .6, 1));

    	//parallel
    	//to ground
       	//turn to cube
    	addSequential(new DriveCurvatureForTime(-.9, 1.0, false, .5));  //.2 front of switch
    	addSequential(new TurnToDegrees(.8, 100));

    	//find cube
    	//get cube
    	addSequential(new DriveStraightToEncoderDistanceOrTime(44, .6, 1));
    	
    	//parallel
    	//raise to switch
    	//forward
    	addSequential(new DriveStraightToEncoderDistanceOrTime(20, .6, 1));

    	//deploy
    	addSequential(new WaitCommand(1.0));

    	addSequential(new DriveCurvatureForTime(-.7, -1.0, false, .4));  //.2 front of switch

    	addSequential(new DriveStraightToEncoderDistanceOrTime(25, .6, 1));

    	addSequential(new DriveCurvatureForTime(-1.0, -1.0, false, 1.0));  //.2 front of switch

      	//parallel
    	//raise to switch
    	//forward
    	addSequential(new DriveStraightToEncoderDistanceOrTime(70, .7, 1));

//    	addSequential(new WaitCommand(1.0));
//    	
//    	addSequential(new DriveStraightForTime(.7, 3.5));
//    	addSequential(new DriveStraightToWhiteLineorTime(false));
//
//    	addSequential(new TurnToDegrees(.5, 55));
//    	
//    	//in parallel lift arm 
//    	
//    	addSequential(new DriveStraightForTime(.5, 1));
//    	
//    	//drop the block
//    	
//    	addSequential(new TurnToDegrees(.5, 90));
//    	addSequential(new FindCube(false));
//
//    	//in parallel intake cube
//    	addSequential(new DriveStraightForTime(.5, 3));
//    	
//    	//move arm up and extend telescope (if we can't shoot)
//    	addSequential(new DriveStraightForTime(-.5, 1));
//    	
//    	//shoot the cube or drop cube
//    	
//    	addSequential(new DriveStraightForTime(-.5, 1));
//    	
//    	addSequential(new TurnToDegrees(.5, -45));
//    	addSequential(new FindCube(false));
//    	
//    	//in parallel intake cube
//    	addSequential(new DriveStraightForTime(.5, 3));
//    	
//    	addSequential(new TurnToDegrees(.5, 160));
//
//    	//move arm up and extend telescope for scale
//    	addSequential(new DriveStraightForTime(.5, 3));
    	
    	//release cube
    	addSequential(new Send("finished"));
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
