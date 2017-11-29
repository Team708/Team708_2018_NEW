package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
//import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToGear;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Up;
//import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
//import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
//import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_In;
//import org.usfirst.frc.team708.robot.commands.intake_gear.AquireGear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OneGearCenter extends CommandGroup {
   
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();
    }
	
    public  OneGearCenter() {
    	addSequential(new Send("In OneGearCenter"));

//  target lift
    	addSequential(new Send("running drive to lift"));

    	addSequential(new SetLED(Constants.SET_HAS_GEAR_TARGETING));
    	addSequential(new WaitCommand(.5)); //was working at 1.0
    	addSequential(new Intake_Gear_Up());

    	addSequential(new RotateAndDriveToLift());
    	
//  place gear on lever and back away  
    	addSequential(new Send("running Gear out"));

    	addSequential(new Intake_Gear_Out());
    	addParallel(new Intake_Gear_Down());

// get off lever and go for some balls
    	addSequential(new DriveStraightToEncoderDistance(15, .4, true));  //put this back in!!!!    	

// turn toward boiler
    	addSequential(new Send("running turn to boiler"));

    	addSequential(new TurnToDegreesAlliance(.5, 47, Constants.COUNTERCLOCKWISE));

// target Boiler
    	addSequential(new Send("running target boiler"));

    	addSequential(new WaitCommand(.5)); //was working at 1.0
    	addSequential(new SetLED(Constants.SET_TARGETING));
    	addSequential(new RotateAndDriveToBoiler(111), 3);

// unload balls
    	addSequential(new Send("running spin shooter"));

		addParallel(new SpinShooter(12));
		
    	addSequential(new DriveStraightToEncoderDistanceOrTime(110, .5, true, 5));
    	
//    	addSequential(new WaitCommand(.5)); //commented out - check to see if we need to pause a bit
    	addSequential(new Send("running spin feeder "));
		addSequential(new SpinFeeder(6));     //then shoot
		addSequential(new StopShooter());	
    	
    	addSequential(new Send("finished One Gear"));
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
