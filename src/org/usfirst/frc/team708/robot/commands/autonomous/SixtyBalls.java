package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.FeederOff;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.intake_ball.Intake_Ball_In;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Up;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
//import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SixtyBalls extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  SixtyBalls() {  
    	addSequential(new Send("In Sixty Ball"));

// goto Hopper
//    	addSequential(new DriveStraightToEncoderDistance(100, .4, false)); //RED  to far hopper
    	addSequential(new DriveStraightToEncoderDistance(100, .4, false)); //BLUE to far hopper

    	addSequential(new Send("running turning to hopper"));

//    	addSequential(new TurnToDegreesAlliance(.5, 85, Constants.COUNTERCLOCKWISE)); //red
    	addSequential(new TurnToDegreesAlliance(.5, 80, Constants.COUNTERCLOCKWISE)); //blue

    	addSequential(new Send("running drive to hopper"));

    	addSequential(new DriveStraightToEncoderDistanceOrTime(45, .5, true, 2)); //25
    	addParallel(new Intake_Ball_In(7));

    	addSequential(new WaitCommand(.2)); //if we can start getting balls from hopper increase this

// back off hopper and turn toward boiler		    	
    	addSequential(new Send("running back away from hopper"));

    	addSequential(new DriveStraightToEncoderDistance(40, .4, false));   //30
    	addSequential(new WaitCommand(.5));

    	addSequential(new Send("running turn to boiler"));
    	addSequential(new TurnToDegreesAlliance(.5, 58, Constants.CLOCKWISE));  //50 bigboard in way

//    	addSequential(new DriveStraightToEncoderDistance(40, .4, true));

// target Boiler
    	addSequential(new Send("running target boiler"));

    	addSequential(new WaitCommand(.5));  //was 1.0
    	addSequential(new SetLED(Constants.SET_TARGETING));
    	addSequential(new RotateAndDriveToBoiler(111), 3);
    	
// unload balls
    	addSequential(new Send("running spin shooter"));

    	addParallel(new SpinShooter(12)); 

    	addSequential(new DriveStraightToEncoderDistanceOrTime(110, .5, true, 3)); //48

    	addSequential(new Send("running shoot"));

//    	addSequential(new WaitCommand(1.0)); 
    	addSequential(new SpinFeeder(8));
    	addSequential(new StopShooter());
 
    	addSequential(new Send("finished sitxy ball"));

// go to lever
//    	addSequential(new TurnToDegreesAlliance(.4, 20, Constants.COUNTERCLOCKWISE));
    	
//  target lever
//    	addSequential(new Intake_Gear_Up());
//    	addSequential(new SetLED(Constants.SET_HAS_GEAR_TARGETING));
//    	addSequential(new WaitCommand(.75));
//    	addSequential(new RotateAndDriveToLift());
//    	
////  place gear on lever and back away    	
//    	addSequential(new Intake_Gear_Out());
//    	addParallel(new Intake_Gear_Down());
//    	addSequential(new DriveStraightToEncoderDistance(15, .4, true));
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
