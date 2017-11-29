package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoFireBalls extends CommandGroup {

    protected void initialize() {
    	
    }
    public AutoFireBalls() {    	
    	addSequential(new DriveStraightToEncoderDistance(48, .4, true));

//    	addSequential(new DriveStraightToEncoderDistance(1, .3, false));
    	
		addSequential(new SpinFeeder(6));  
		addSequential(new StopShooter());
    }
	
	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
    
}
