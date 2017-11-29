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

public class TenBalls extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  TenBalls() {  
    	addSequential(new Send("In Ten Ball"));

// go to lever
    	addSequential(new DriveStraightToEncoderDistance(60, .4, false)); //was 73 moved bot over
    	
    	addSequential(new Send("running turn to lift"));

    	addSequential(new TurnToDegreesAlliance(.5, 43, Constants.COUNTERCLOCKWISE));

//  target lever
    	addSequential(new Send("running drive to lift"));

    	addSequential(new SetLED(Constants.SET_HAS_GEAR_TARGETING));
    	addSequential(new WaitCommand(1.0));  //was 1.0 do we need to shorten this?
    	addSequential(new Intake_Gear_Up());
    	
    	addSequential(new RotateAndDriveToLift());
    	
//  place gear on lever and back away    	
    	addSequential(new Send("running release gear"));

    	addSequential(new Intake_Gear_Out());
    	addParallel(new Intake_Gear_Down());
    	
// get off lever and go for some balls
    	addSequential(new DriveStraightToEncoderDistance(10, .4, true));
    	addSequential(new TurnToDegreesAlliance(.5, 15, Constants.CLOCKWISE));

// target Boiler
    	addSequential(new Send("running target boiler"));

    	addSequential(new WaitCommand(.5)); //was 1.0
    	addSequential(new SetLED(Constants.SET_TARGETING));
    	addSequential(new RotateAndDriveToBoiler(111), 3);

// unload balls
    	addSequential(new Send("running spin shooter"));

    	addParallel(new SpinShooter(12));

    	addSequential(new DriveStraightToEncoderDistanceOrTime(100, .5, true, 4));
//    	addSequential(new WaitCommand(1.0)); //do we need to add this back
    	
    	addSequential(new Send("running shoot"));
    	addSequential(new SpinFeeder(7));  //was 6
    	addSequential(new StopShooter());
    	
    	addSequential(new Send("finished 10 ball"));
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
