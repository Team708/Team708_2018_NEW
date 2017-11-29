package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
//import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.FeederOff;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Up;
//import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JustTenBalls extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  JustTenBalls() { 
    	addSequential(new Send("In Just 10 Ball"));

// go to baseline
    	addSequential(new DriveStraightToEncoderDistance(85, .4, false));

// target Boiler
    	addSequential(new WaitCommand(1.0));
    	addSequential(new SetLED(Constants.SET_TARGETING));
    	addSequential(new RotateAndDriveToBoiler(111), 3);

// unload balls
    	addParallel(new SpinShooter(8));

    	addSequential(new DriveStraightToEncoderDistanceOrTime(90, .5, true, 4));
    	addSequential(new WaitCommand(1.0));
    	addSequential(new SpinFeeder(6));  
    	addSequential(new StopShooter());
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
