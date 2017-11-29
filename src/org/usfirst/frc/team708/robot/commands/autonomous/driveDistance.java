package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Up;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_In;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
import org.usfirst.frc.team708.robot.commands.autonomous.AutoFireBalls;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class driveDistance extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();
    	
    }
	
    public  driveDistance() {

    	addSequential(new Send("In DriveDistance"));

    	addSequential(new Send("Calling wait 2"));
    	addSequential(new WaitCommand(1.0));

    	addSequential(new RotateAndDriveToBoiler(111), 3);

//    	addSequential(new RotateAndDriveToBoiler(111));
    	
    	addSequential(new DriveStraightForTime(.3, 3));


//    	addSequential(new WaitCommand(2.0));    	
//    	addSequential(new Send("drive for 1 sec"));
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(10, .4, false, 1));

//    	addSequential(new SpinShooter(8));
//    	addSequential(new AutoFireBalls());
    	
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
