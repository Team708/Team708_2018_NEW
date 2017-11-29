package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
//import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Up;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OneGearLeft extends CommandGroup {
   
    protected void initialize() {
    }
	
    public  OneGearLeft() {
    	addSequential(new Send("In OneGearLeft"));

// go to lever
    	addSequential(new DriveStraightToEncoderDistance(70, .4, false));
    	addSequential(new TurnToDegreesAlliance(.5, 45, Constants.CLOCKWISE));
    	
//  target lever
    	addSequential(new SetLED(Constants.SET_HAS_GEAR_TARGETING));
    	addSequential(new WaitCommand(1.0));  //was 1.0
    	addSequential(new Intake_Gear_Up());
    	addSequential(new RotateAndDriveToLift());
    	
//  place gear on lever and back away    	
    	addSequential(new Intake_Gear_Out());
    	addParallel(new Intake_Gear_Down());
    	
// get off lever and go for some balls
    	addSequential(new DriveStraightToEncoderDistance(42, .4, true));
    	
// turn toward boiler
//    	addSequential(new TurnToDegreesAlliance(.5, 85, Constants.COUNTERCLOCKWISE));
//    	addSequential(new DriveStraightToEncoderDistance(47, .5, true));

//    	addSequential(new TurnToDegreesAlliance(.5, 30, Constants.COUNTERCLOCKWISE));


// target Boiler
//    	addSequential(new WaitCommand(.75));
//    	addSequential(new SetLED(Constants.SET_TARGETING));
//    	addSequential(new RotateAndDriveToBoiler(AutoConstants.DISTANCE_TO_BOILER_LOCATION2));

// unload balls
//		addParallel(new SpinShooter(8));
		
//      addSequential(new AutoFireBalls());
//    	addSequential(new DriveStraightToEncoderDistanceOrTime(14, .5, true, 2));
//    	addSequential(new WaitCommand(1.0));
//    	addSequential(new SpinFeeder(6));   
//		addSequential(new StopShooter());    	
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
