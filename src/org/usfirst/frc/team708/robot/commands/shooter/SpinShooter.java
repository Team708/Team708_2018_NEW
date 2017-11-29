package org.usfirst.frc.team708.robot.commands.shooter;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.OI;

//import org.team708.robot.OI;
//import org.team708.robot.subsystems.Loader;
//import org.team708.robot.util.Gamepad;
//import org.team708.robot.commands.shooter.Fire;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class SpinShooter extends Command {

	private double maxTime;

    public SpinShooter(double maxTime) {
    	requires(Robot.shooter);
//    	requires(Robot.drivetrain);
    	this.setTimeout(maxTime);
    }
    
// Called just before this Command runs the first time
    protected void initialize() {
// 	    Robot.shooter.setFgain(Constants.SHOOTER_F);
//    	if (Robot.drivetrain.getSonarDistance() > 60)
//    	{
//    		Robot.shooter.moveHood(Constants.HOOD_LEVER);
//    		Robot.shooter.manualRPM(Constants.SHOOTER_MOTOR_SPEED_LEVER);
//    	}
//    	else
//    	{
//    		Robot.shooter.manualRPM(Constants.SHOOTER_MOTOR_SPEED_BOILER);
//    		Robot.shooter.moveHood(Constants.HOOD_BOILER);
//    	}
    }

    // Called repeatedly 50 times/sec when this Command is scheduled to run
    protected void execute() {   	
//    	if (Robot.drivetrain.getSonarDistance() > 60)
//    	{
//    		Robot.shooter.moveHood(Constants.HOOD_LEVER);
//    		Robot.shooter.manualRPM(Constants.SHOOTER_MOTOR_SPEED_LEVER);
//    	}
//    	else
//    	{
    		Robot.shooter.manualRPM(Constants.SHOOTER_MOTOR_SPEED_BOILER);
    		Robot.shooter.moveHood(Constants.HOOD_BOILER);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (isTimedOut());
    }

    protected void end() {
    	Robot.shooter.stop();
    }

    protected void interrupted() {
    	end();
    }
}
