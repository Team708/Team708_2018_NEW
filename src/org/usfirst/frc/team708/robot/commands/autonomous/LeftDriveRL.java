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

public class LeftDriveRL extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();
    	
    }
	
    public  LeftDriveRL() {

    	addSequential(new Send("In Left Drive R L"));
    	addSequential(new GearShift1());
    	
       	//this goes to scale
    	addSequential(new DriveCurvatureForTime(1.0, .05, false, 2.0));  //.2 front of switch
    	addParallel(new WaitCommand(1.0));
    	//raise to scale
    	
    	
    	//deploy cube
    	addSequential(new WaitCommand(1.0));
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(200, .6, 1));

    	//parallel
    	//to ground
       	//turn to cube
    	addSequential(new DriveCurvatureForTime(-1.0, .7, false, .9));  //.2 front of switch
    	addSequential(new DriveStraightToEncoderDistanceOrTime(230, .8, true, 4));
    	
    	
    	addSequential(new DriveCurvatureForTime(.7, .6, false, 2));  //.2 front of switch

//    	addSequential(new TurnToDegrees(.8, 50));
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(600, .6, 1));
//    	addSequential(new DriveCurvatureForTime(.7, .6, false, 2)); 
 
    	
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
