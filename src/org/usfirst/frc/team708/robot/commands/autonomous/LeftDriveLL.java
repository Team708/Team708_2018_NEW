package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToWhiteOrTime;
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
