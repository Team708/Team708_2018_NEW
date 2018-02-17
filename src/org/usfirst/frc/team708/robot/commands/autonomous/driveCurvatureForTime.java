package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToWhiteLineorTime;
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

public class driveCurvatureForTime extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();
    	
    }
	
    public  driveCurvatureForTime() {

    	addSequential(new Send("In CurvatureForTime"));

    	addSequential(new WaitCommand(1.0));
    	
    	//                         (xSpeed, zRotation, isQuickTurn, time);
    	addSequential(new DriveCurvatureForTime(.5, .5, false, 3));
//    	Curvature drive method for differential drive platform.
//    	The rotation argument controls the curvature of the robot's path rather than its rate of heading 
//    	change. This makes the robot more controllable at high speeds. 
//    	Also handles the robot's quick turn functionality - "quick turn" overrides 
//    	constant-curvature turning for turn-in-place maneuvers.

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
